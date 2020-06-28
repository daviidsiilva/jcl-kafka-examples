
import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

public class getValueLockingSumThread {
	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();

		Integer sum = 0;
		
		jcl.instantiateGlobalVar("sum3", sum);
		
		for(int i = 0; i < 50; i++) {
			sum = (Integer) jcl.getValueLocking("sum3").getCorrectResult();
			System.out.println("sum=" + sum);
			sum = sum + 1;
			jcl.setValueUnlocking("sum3", sum);
		}
	}
}
