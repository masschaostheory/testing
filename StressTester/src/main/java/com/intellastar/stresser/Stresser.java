package com.intellastar.stresser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.intellastar.evrisko.TLVBuilder;
import com.intellastar.stresser.node.NodeBuilder;
import com.intellastar.stresser.node.Nodes;
import com.intellastar.stresser.util.ArgsHandler;

public class Stresser {
	
	public static List<TLVBuilder> nodes = new ArrayList<TLVBuilder>();

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		UdpServer server = new UdpServer();
		
		ArgsHandler.parse(args);
		
		if (!args[0].equals("help")) {
			System.out.println("Executing with the following settings:");
			System.out.println("Target IP Address: " + Settings.target.getHostAddress());
			System.out.println("Target port: " + Settings.port);
			System.out.println("Nodes to create: " + Settings.nodeCount);
			System.out.println("Frequency of broadcast: " + Settings.frequency + " per hour");
			System.out.println("Duration of broadcast: " + Settings.duration + " hours");
			System.out.println("Starting Stress Tester..");
			//System.out.println("Targeting: " + Settings.target + ":" + Settings.port);
			System.out.println("Proceed? (Y/n): ");
			String proceed = scanner.next();
			
			if (proceed.equals("Y")) {
				System.out.println("Beginning Stress Test..");
				server.run();
				
				NodeBuilder.create(Settings.nodeCount);
				Nodes.currentNode = 0;
				
				//TODO Fire all nodes through server repeated over time
				server.broadcast();
				
				//End of application
				server.stop();
				scanner.close();
				System.out.println("Stress testing completed.");
			}
			else if (proceed.equals("n")) System.out.println("Exiting..");
			else System.err.println("Invalid Input, exiting..");
		}
		
	}
	
}
