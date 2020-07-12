package com30;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import commom.Constants;
import commom.Producer;
import commom.Resource;
import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;
import toy.UserServices;

public class ExecuteAllCores {
	private static List<Thread> sendExecuteThreads = new ArrayList<Thread>();
	private static AtomicInteger a = new AtomicInteger(0);

	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();
		Resource<String> resource = new Resource<String>();
		Producer<String> producer = new Producer<String>(Constants.FILE_PATH_NAME, resource);
		Boolean registered = jcl.register(UserServices.class, "UserServices");
		Object[] executeArgs = {
				new Integer("1"), 
				new Integer("10"), 
				new Integer(10)
		};		
		
		try {
			producer.start();
			producer.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		if(registered) {
			for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
				sendExecuteThreads.add(new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							while((resource.isFinished()==false) && (resource.getNumOfRegisters()!=0)) {
								if(resource.getRegister() != null) {
									List<Future<JCL_result>> tickets = jcl.executeAllCores("UserServices", executeArgs);
					        		
					        		try {
					        			a.set(a.get() + 1);
					        			System.out.println(Thread.currentThread().getId() + " " + a.get() + ": " + jcl.getAllResultBlocking(tickets).get(0).getCorrectResult());
					        		} catch (Exception e) {
					        			e.printStackTrace();
					        		}
								}
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}));
			}
		}
		
		long initGetTime = System.currentTimeMillis();
		sendExecuteThreads.forEach(thread -> {
			thread.start();
		});
		
		sendExecuteThreads.forEach(thread -> {
			try {
				thread.join();
				System.out.println(thread.getId() + " milliseconds spent: " + (System.currentTimeMillis() - initGetTime));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println("milliseconds spent: " + (System.currentTimeMillis() - initGetTime));
	}
}
