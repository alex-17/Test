package bayesx;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class CommonsUtils {

    public static void addAll(Map<String, String> target,
            Iterator<String> keys, String value) {
        if (target != null) {
            String key = null;
            while (keys.hasNext()) {
                key = keys.next();
                if (target.get(key) == null) {
                    target.put(key, value);
                }
            }
        }
    }

    public static String toString(final Object obj, final String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }

    public static boolean isStopWord(String word) {
        if (StringUtils.isNotBlank(word)) {
            if (Cache.stopWords.get(word) != null || NumberUtils.isNumber(word)
                    || NumberUtils.isDigits(word)) {
                return true;
            }
        }
        return false;
    }
}
