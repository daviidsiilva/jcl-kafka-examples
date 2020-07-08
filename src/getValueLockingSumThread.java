
import java.util.ArrayList;
import java.util.List;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

public class getValueLockingSumThread {
	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();
		List<Thread> getSetValueLockingThreads = new ArrayList<Thread>();
		Integer sum = 0;
		String key = "sum204";
		
		jcl.instantiateGlobalVar(key, sum);

		for(int i = 0; i < 10; i++) {
			getSetValueLockingThreads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					Integer sum = (Integer) jcl.getValueLocking(key).getCorrectResult();
					sum = sum + 1;
					jcl.setValueUnlocking(key, sum);
				}
			}));
		}
		
		long initGetTime = System.currentTimeMillis();
		getSetValueLockingThreads.forEach(thread -> {
			thread.start();
		});
		getSetValueLockingThreads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(System.currentTimeMillis() - initGetTime);
	}
}
