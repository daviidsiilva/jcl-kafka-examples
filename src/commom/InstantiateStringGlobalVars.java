package commom;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

public class InstantiateStringGlobalVars {
	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();
		
		for(Integer i = 0; i < Constants.N_VARS; i++) {
			System.out.println("string" + i);
			jcl.instantiateGlobalVar("string" + i, i.toString());
		}
	}
}
