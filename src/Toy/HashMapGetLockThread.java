package Toy;

import java.util.ArrayList;
import java.util.List;

import implementations.collections.JCLHashMapPacu;

public class HashMapGetLockThread {

	public static void main(String[] args) {
		JCLHashMapPacu<Object, Object> jclHashMap = new JCLHashMapPacu<>("mapGetLock41");
		List<Thread> hashMapGetLockThreads = new ArrayList<Thread>();
		String key = "sum";
		Object sum = 0;
		
		jclHashMap.put(key, sum);

		for(int i = 0; i < 10; i++) {
			hashMapGetLockThreads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					Object sum = jclHashMap.getLock(key);
					sum = Thread.currentThread().getId();
					jclHashMap.putUnlock(key, sum);
				}
			}));
		}
		
		long initGetTime = System.currentTimeMillis();
		hashMapGetLockThreads.forEach(thread -> {
			thread.start();
		});
		
		hashMapGetLockThreads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(System.currentTimeMillis() - initGetTime);
	}
}
