
import java.util.Map;

import implementations.dm_kernel.user.JCL_FacadeImpl;

public class hashMapThread {
	public static void main(String[] args) {
		Map<String, Long> jclHashMap = JCL_FacadeImpl.GetHashMap("mymap7");
		
		// PUT
		new Thread(new Runnable() {
			@Override
			public void run() {
				Long threadId = Thread.currentThread().getId();
				jclHashMap.put("a", threadId);
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Long threadId = Thread.currentThread().getId();
				jclHashMap.put("b", threadId);
			}
		}).start();

		// GET
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("k: a" + ", v: " + jclHashMap.get("a"));
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("k: b" + ", v: " + jclHashMap.get("b"));
			}
		}).start();
	}
}