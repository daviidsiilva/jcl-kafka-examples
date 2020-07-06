
import implementations.collections.JCLHashMapPacu;
import implementations.dm_kernel.user.JCL_FacadeImpl;

public class hashMapGetLock {

	public static void main(String[] args) {
		JCLHashMapPacu<Object, Object> jclHashMap = (JCLHashMapPacu<Object, Object>) JCL_FacadeImpl.GetHashMap("mapGetLock10");
		String key = "sum";
		Object sum = 0;
		
		jclHashMap.put(key, sum);

		for(int i = 0; i < 10; i++) {
			sum = jclHashMap.getLock(key);
			System.out.println("thread " + Thread.currentThread().getId() + ": sum=" + sum);
			sum = i + 1;
			jclHashMap.putUnlock(key, sum);
		}
	}
}
