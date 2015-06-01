package com.intellastar.stresser;

import java.net.InetAddress;

public class Settings {
	
	//Arg1: Stresser target
	public static InetAddress target;
	
	//Arg2: Stresser port
	public static Integer port;
	
	//Arg3: Number of nodes to create
	public static Integer nodeCount;
	
	//Arg4: Node broadcast frequency per hour
	public static Integer frequency;
	
	//Arg5: Node broadcast duration in hours
	public static Float duration;
}
