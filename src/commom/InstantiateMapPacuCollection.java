package commom;

import java.util.ArrayList;
import java.util.List;

import implementations.collections.JCLHashMapPacu;

public class InstantiateMapPacuCollection {
	public static void main(String[] args) {
		JCLHashMapPacu<Object, List<String>> mapPacu = new JCLHashMapPacu<Object, List<String>>("collectionmap");
		List<String> collection = new ArrayList<String>();
		
		collection.add("André");
		collection.add("David");
		collection.add("Joubert");
		
		for(int i = 0; i < Constants.N_VARS; i++) {
			System.out.println("collection" + i);
			mapPacu.put("collection" + i, collection);
		}
	}
}
