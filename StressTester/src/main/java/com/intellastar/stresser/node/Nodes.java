package com.intellastar.stresser.node;

//import com.intellastar.evrisko.TLV;
import com.intellastar.evrisko.TLVBuilder;

public class Nodes {

	public static Integer currentNode = 0;
	private static TLVBuilder[] nodeTLVs;
	
	public static void add(TLVBuilder tlv) {
		nodeTLVs[currentNode] = tlv;
		currentNode++;
	}
	
	public static TLVBuilder get(Integer i) {
		return nodeTLVs[i];
	}
	
	public static TLVBuilder getCurrent() {
		return nodeTLVs[currentNode];
	}
	
	public static TLVBuilder[] getAll() {
		return nodeTLVs;
	}
	
}
