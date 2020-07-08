package Toy;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;
 
public class ExecuteThread {
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();
		List<Thread> executeThreads = new ArrayList<Thread>();
		
		Boolean registered = jcl.register(UserServices.class, "UserServices");
		if(registered) {
			for(int i = 0; i < 10; i++) {
				executeThreads.add(new Thread(new Runnable() {
		            @Override
		            public void run() {
		            	Object[] args = {
		            		new Integer("1"), 
		            		new Integer("1000"), 
		            		new Integer(10)
		            	};
		            	
		        		Future<JCL_result> ticket = jcl.execute("UserServices", args);
		        		
		        		try {
		        			ticket.get().getCorrectResult();
		        		} catch (Exception e) {
		        			e.printStackTrace();
		        		}
					}
				}));
			}
		}
		
		long initGetTime = System.currentTimeMillis();
		executeThreads.forEach(thread -> {
			thread.start();
		});
		
		executeThreads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(System.currentTimeMillis() - initGetTime);
	}
}
