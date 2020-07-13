package toy;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

public class GetValueLockingSum {
	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();

		Integer sum = 0;

		jcl.instantiateGlobalVar("sum103", sum);

		long initGetTime = System.currentTimeMillis();
		for(int i = 0; i < 10; i++) {
			sum = (Integer) jcl.getValueLocking("sum103").getCorrectResult();
			System.out.println(i);
//			System.out.println("thread " + Thread.currentThread().getId() + ": sum=" + sum);
			sum = sum + 1;
			jcl.setValueUnlocking("sum103", sum);
		}
		System.out.println(System.currentTimeMillis() - initGetTime);
	}
}
