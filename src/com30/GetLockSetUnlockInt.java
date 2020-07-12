package com30;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

public class GetLockSetUnlockInt {
	private static List<Thread> getSetCollectionThreads;
	private static AtomicInteger a = new AtomicInteger(0);

	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();
		
		getSetCollectionThreads = new ArrayList<Thread>();
		
		Resource<String> resource = new Resource<String>();
		Producer<String> producer = new Producer<String>(Constants.FILE_PATH_NAME, resource);
		
		try {
			producer.start();
			producer.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
			getSetCollectionThreads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String varName = null;
						while((resource.isFinished()==false) && (resource.getNumOfRegisters()!=0)) {
							if((varName = resource.getRegister()) != null) {
								a.set(a.get() + 1);
								System.out.println(Thread.currentThread().getId() + " " + a.get() + ": " + jcl.getValueLocking("int" + varName).getCorrectResult());
								jcl.setValueUnlocking("int" + varName, Integer.parseInt(varName));
//								jcl.getValueLocking("int" + varName).getCorrectResult();
//								jcl.setValueUnlocking("int" + varName, Integer.parseInt(varName));
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
		
		long initGetTime = System.currentTimeMillis();
		getSetCollectionThreads.forEach(thread -> {
			thread.start();
		});
		
		getSetCollectionThreads.forEach(thread -> {
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
