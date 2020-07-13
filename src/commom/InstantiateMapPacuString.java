package commom;

import implementations.collections.JCLHashMapPacu;

public class InstantiateMapPacuString {
	public static void main(String[] args) {
		JCLHashMapPacu<Object, String> mapPacu = new JCLHashMapPacu<Object, String>("stringmap");
		
		for(Integer i = 0; i < Constants.N_VARS; i++) {
			System.out.println("string" + i);
			mapPacu.put("string" + i, i.toString());
		}
	}
}
