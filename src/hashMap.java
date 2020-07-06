
import java.util.Map;

import implementations.dm_kernel.user.JCL_FacadeImpl;

public class hashMap {
	public static void main(String[] args) {
		Map<String, Integer> jclHashMap = JCL_FacadeImpl.GetHashMap("mymap5");
		String keyPrefix = "key";
		
		for(int i=1; i<=3; i++){
			jclHashMap.put(keyPrefix + i, i);
		}

		for(int i=1; i<=3; i++){
			System.out.println("k: " + keyPrefix + i + ", v: " + jclHashMap.get(keyPrefix + i));
		}

		System.out.println("mymap2 size: " + jclHashMap.size());
	}
}