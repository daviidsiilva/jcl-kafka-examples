package commom;

import java.util.ArrayList;
import java.util.List;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

public class InstantiateCollectionGlobalVars {
	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();
		List<String> collection = new ArrayList<String>();
		
		collection.add("André");
		collection.add("David");
		collection.add("Joubert");
		
		for(int i = 0; i < Constants.N_VARS; i++) {
			System.out.println("collection" + i);
			jcl.instantiateGlobalVar("collection" + i, collection);
		}
	}
}
