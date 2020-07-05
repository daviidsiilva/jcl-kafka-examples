
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
			for(int i = 0; i < 5; i++) {
				Object[] args1 = {
						new Integer("1"), 
						new Integer("1000"), 
						new Integer(10)
				};

				Future<JCL_result> ticket = jcl.execute("UserServices", args1);

				try {
					System.out.println("thread " + Thread.currentThread().getId() + ": jcl.execute(\"UserServices\", args)=" + ticket.get().getCorrectResult());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}
}
