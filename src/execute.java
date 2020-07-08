
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

public class execute {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();

		Boolean registered = jcl.register(UserServices.class, "UserServices");
		if(registered) {
			long initGetTime = System.currentTimeMillis();
			Object[] args1 = {
					new Integer("1"), 
					new Integer("1000"), 
					new Integer(10)
			};
			
			for(int i = 0; i < 10; i++) {
				Future<JCL_result> ticket = jcl.execute("UserServices", args1);

				try {
					ticket.get().getCorrectResult();
//					System.out.println("thread " + Thread.currentThread().getId() + ": jcl.execute(\"UserServices\", args)=" + ticket.get().getCorrectResult());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("execute.java * 100 spent " + (System.currentTimeMillis() - initGetTime));
		}
	}
}
