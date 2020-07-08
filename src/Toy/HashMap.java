package Toy;

import java.util.Map;

import implementations.dm_kernel.user.JCL_FacadeImpl;

public class HashMap {
	public static void main(String[] args) {
		Map<String, Integer> jclHashMap = JCL_FacadeImpl.GetHashMap("mymap5");
		String keyPrefix = "key";
		
		long initGetTime = System.currentTimeMillis();
		for(int i=0; i<10; i++){
			jclHashMap.put(keyPrefix + i, i);
		}
		for(int i=0; i<10; i++){
			System.out.println("k: " + keyPrefix + i + ", v: " + jclHashMap.get(keyPrefix + i));
		}
		System.out.println(System.currentTimeMillis() - initGetTime);
		
		System.out.println("mymap2 size: " + jclHashMap.size());
	}
}