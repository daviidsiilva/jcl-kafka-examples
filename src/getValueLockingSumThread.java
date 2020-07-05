
import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

public class getValueLockingSumThread {
	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();

		Integer sum = 0;

		jcl.instantiateGlobalVar("sum4", sum);

		for(int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Integer sum = (Integer) jcl.getValueLocking("sum4").getCorrectResult();
					System.out.println("thread " + Thread.currentThread().getId() + ": sum=" + sum);
					sum = sum + 1;
					jcl.setValueUnlocking("sum4", sum);
				}
			}).start();
		}
	}
}
