package commom;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

public class InstantiateIntGlobalVars {
	public static void main(String[] args) {
		JCL_facade jcl = JCL_FacadeImpl.getInstance();
		
		for(int i = 0; i < Constants.N_VARS; i++) {
			System.out.println("int" + i);
			jcl.instantiateGlobalVar("int" + i, i);
		}
	}
}
