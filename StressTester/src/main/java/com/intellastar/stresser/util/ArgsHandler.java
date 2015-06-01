package com.intellastar.stresser.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.intellastar.stresser.Settings;

public class ArgsHandler {
	
	public static void parse(String[] args) throws UnknownHostException {
		if (args.length == 0) 
			throw new IllegalArgumentException("No arguments passed in, exiting.");
		
		if (args[0].equals("help")) {
			System.out.println("v1.0");
			System.out.println("StressTester.jar <InetAddress:String> <Port:Integer> "
				+ "<NodeCount:Integer> <Frequency:Integer> <Duration:Float>");
		}
		else {
			try {
				Settings.target = InetAddress.getByName(args[0]);
				Settings.port = Integer.valueOf(args[1]);
				Settings.nodeCount = Integer.valueOf(args[2]);
				Settings.frequency = Integer.valueOf(args[3]);
				Settings.duration = Float.valueOf(args[4]);
			}
			catch (Exception ex) {
				System.err.println("Invalid input parameters.. "
						+ "Expected: <InetAddress> <Port> <NodeCount> <Frequency> <Duration>");
				System.err.println(ex.getMessage());
			}
			
		}
	}

}
