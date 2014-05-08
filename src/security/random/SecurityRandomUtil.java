package security.random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class SecurityRandomUtil {

	private final static String RANDOM_ALGORITHM = "SHA1PRNG";
	
	private static SecureRandom secureRandom = null;
	
	private final static char[] CHAR_PASSWORD_SPECIALS = { '!', '$', '*', '-', '.', '=', '?', '@', '_' };
	
	private final static char[] CHAR_LOWERS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	
	private final static char[] CHAR_UPPERS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	private final static char[] CHAR_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	private final static char[] CHAR_SPECIALS = { '!', '$', '*', '+', '-', '.', '=', '?', '@', '^', '_', '|', '~' };
	
	private static char[] CHAR_ALL = null;
	
	static {
		CHAR_ALL = union(CHAR_PASSWORD_SPECIALS,CHAR_LOWERS,CHAR_UPPERS,CHAR_DIGITS,CHAR_SPECIALS);
		
		 try {
				secureRandom = SecureRandom.getInstance(RANDOM_ALGORITHM);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	}
	
	
	 /**
		 * {@inheritDoc}
		 */
	    public static String getRandomString(int length) {
	    	StringBuilder sb = new StringBuilder();
	        for (int loop = 0; loop < length; loop++) {
	            int index = secureRandom.nextInt(CHAR_ALL.length);
	            sb.append(CHAR_ALL[index]);
	        }
	        String nonce = sb.toString();
	        return nonce;
	    }
	
	
	/**
     * Union multiple character arrays.
     * 
     * @param list the char[]s to union
     * @return the union of the char[]s
     */
    private static char[] union(char[]... list) {
    	StringBuilder sb = new StringBuilder();
    	
    	for (char[] characters : list) {
	        for (int i = 0; i < list.length; i++) {
	            if (!contains(sb, characters[i]))
	                sb.append(list[i]);
	        }
    	}

        char[] toReturn = new char[sb.length()];
        sb.getChars(0, sb.length(), toReturn, 0);
        Arrays.sort(toReturn);
        return toReturn;
    }
    
    
    /**
     * Returns true if the character is contained in the provided StringBuilder.
     * @param input 	The input
     * @param c 		The character to check for to see if {@code input} contains.
     * @return			True if the specified character is contained; false otherwise.
     */
    private static boolean contains(StringBuilder input, char c) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == c)
                return true;
        }
        return false;
    }
}
