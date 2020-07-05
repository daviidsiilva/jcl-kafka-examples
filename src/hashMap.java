
import java.util.Map;

import implementations.dm_kernel.user.JCL_FacadeImpl;

public class hashMap {
	public static void main(String[] args) {
		Map<String, Integer> jclHashMap = JCL_FacadeImpl.GetHashMap("mymap");

		for(int i=1; i<=3; i++){
			jclHashMap.put("key" + i, i);
		}

		for(int i=1; i<=3; i++){
			System.out.println("k: " + i + ", v: " + jclHashMap.get("key" + i));
		}

		System.out.println("mymap size: " + jclHashMap.size());
	}
}