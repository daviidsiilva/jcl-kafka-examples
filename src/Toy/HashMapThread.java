package toy;

import java.util.Map;

import implementations.dm_kernel.user.JCL_FacadeImpl;

public class HashMapThread {
	public static void main(String[] args) {
		Map<String, Long> jclHashMap = JCL_FacadeImpl.GetHashMap("mymap7");
		
		// PUT
		new Thread(new Runnable() {
			@Override
			public void run() {
				long initGetTime = System.currentTimeMillis();
				Long threadId = Thread.currentThread().getId();
				jclHashMap.put("a", threadId);
				System.out.println(System.currentTimeMillis() - initGetTime);
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				long initGetTime = System.currentTimeMillis();
				Long threadId = Thread.currentThread().getId();
				jclHashMap.put("b", threadId);
				System.out.println(System.currentTimeMillis() - initGetTime);
			}
		}).start();

		// GET
		new Thread(new Runnable() {
			@Override
			public void run() {
				long initGetTime = System.currentTimeMillis();
				jclHashMap.get("a");
//				System.out.println("k: a" + ", v: " + jclHashMap.get("a"));
				System.out.println(System.currentTimeMillis() - initGetTime);
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				long initGetTime = System.currentTimeMillis();
				jclHashMap.get("b");
//				System.out.println("k: b" + ", v: " + jclHashMap.get("b"));
				System.out.println(System.currentTimeMillis() - initGetTime);
			}
		}).start();
	}
}