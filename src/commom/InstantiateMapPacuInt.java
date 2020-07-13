package commom;

import implementations.collections.JCLHashMapPacu;

public class InstantiateMapPacuInt {
	public static void main(String[] args) {
		JCLHashMapPacu<Object, Integer> mapPacu = new JCLHashMapPacu<Object, Integer>("intmap");
		
		for(int i = 0; i < Constants.N_VARS; i++) {
			System.out.println("int" + i);
			mapPacu.put("int" + i, i);
		}
	}
}
