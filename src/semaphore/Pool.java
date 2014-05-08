package semaphore;

import java.util.concurrent.Semaphore;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class Pool {
	private static final int MAX_AVAILABLE = 3;
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

	public String getItem() throws InterruptedException {
		available.acquire();
		return getNextAvailableItem();
	}

	public void putItem(String x) {
		if (markAsUnused(x))
			available.release();
	}

	// Not a particularly efficient data structure; just for demo

	protected String[] items = new String[MAX_AVAILABLE];
	protected boolean[] used = new boolean[MAX_AVAILABLE];

	private synchronized String getNextAvailableItem() {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (!used[i]) {
				used[i] = true;
				if( StringUtils.isBlank(items[i])){
					items[i] = productItem();
				}
				return items[i];
			}
		}
		return null; // not reached
	}

	private synchronized boolean markAsUnused(String item) {
		/*
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (item == items[i]) {
				if (used[i]) {
					used[i] = false;
					return true;
				} else
					return false;
			}
		}
		return false;
		*/
		return true;
	}

	private String productItem(){
		return RandomStringUtils.randomAlphanumeric(3);
	}
}
