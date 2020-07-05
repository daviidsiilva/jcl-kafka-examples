
import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

public class getValue {
	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();
		
		jcl.instantiateGlobalVar("abacaxi", "Abacaxi");
		
		long initGetTime = System.currentTimeMillis();
		
		for(int i=0; i < 10000; i++) {
			jcl.getValue("abacaxi").getCorrectResult().toString();
		}
		
		System.out.println("thread " + Thread.currentThread().getId() + ": getTime() spent " + (System.currentTimeMillis() - initGetTime));
	}
}