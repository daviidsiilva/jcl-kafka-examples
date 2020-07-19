package com30;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import commom.Constants;
import commom.CreateInputVarsNames;
import commom.Producer;
import commom.Resource;
import implementations.collections.JCLHashMapPacu;

public class MapPacuGetLockPutUnlockInt {
	private static List<Thread> getSetCollectionThreads;
	private static AtomicInteger a = new AtomicInteger(0);

	public static void main(String[] args) {
		JCLHashMapPacu<Object, Integer> mapPacu = new JCLHashMapPacu<Object, Integer>("intmap");
		
		getSetCollectionThreads = new ArrayList<Thread>();
		
		Resource<String> resource = new Resource<String>();
		Producer<String> producer = new Producer<String>(Constants.FILE_PATH_NAME, resource);
		
		Writer writer = null;
		
		try {
			writer = new FileWriter(Constants.RESULTS_PATH_NAME + CreateInputVarsNames.class.getSimpleName() + ".txt", true);
			producer.start();
			producer.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
								System.out.println(Thread.currentThread().getId() + " " + a.get() + ": " + mapPacu.getLock("int" + varName));
								mapPacu.putUnlock("int" + varName, Integer.parseInt(varName));
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
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			writer.write(timestamp.toString() + ": " + (System.currentTimeMillis() - initGetTime) + System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
