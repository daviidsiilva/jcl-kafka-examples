package Toy;

import implementations.collections.JCLHashMapPacu;

public class HashMapGetLock {

	public static void main(String[] args) {
		JCLHashMapPacu<Object, Object> jclHashMap = new JCLHashMapPacu<Object, Object>("mapGetLock33");
		String key = "sum";
		Object sum = 0;
		
		jclHashMap.put(key, sum);

		long initGetTime = System.currentTimeMillis();
		for(int i = 0; i < 10; i++) {
			jclHashMap.getLock(key);
			System.out.println("thread " + Thread.currentThread().getId() + ": sum=" + sum);
			sum = i + 1;
			jclHashMap.putUnlock(key, sum);
		}
		System.out.println(System.currentTimeMillis() - initGetTime);
	}
}
