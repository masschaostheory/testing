package com.intellastar.stresser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.intellastar.evrisko.MacAddress;
import com.intellastar.evrisko.TLVBuilder;
import com.intellastar.evrisko.enumeration.EnumNodeSubType;
import com.intellastar.evrisko.enumeration.EnumNodeType;
import com.intellastar.evrisko.enumeration.EnumHolder.BatteryType;
import com.intellastar.evrisko.enumeration.EnumHolder.BatteryValueType;
import com.intellastar.stresser.util.Util;

public class UdpServer {
	
	DatagramSocket socket;
	Timer timer;
	
	public void run() throws UnknownHostException, SocketException {
		System.out.println("> Creating Server Instance..");
		socket = new DatagramSocket(null);
		System.out.println("> Server ready to broadcast!");
	}
	
	public void send(byte[] buf) throws IOException {
		socket.send(new DatagramPacket(buf, buf.length));
	}
	
	public void broadcast() throws IOException, InterruptedException {
		System.out.println(">> Nodes: " + Stresser.nodes.size());
		for (int i = 0; i < Settings.duration * 60; i++) {
			System.out.println(">>> [Cycle " + (i + 1) + "]");
			for (TLVBuilder node : Stresser.nodes) {
				int j = Stresser.nodes.indexOf(node);
				System.out.println(">>> {" + Calendar.getInstance().getTime().toString() 
					+ "} Sending packet " + Stresser.nodes.indexOf(node) + "..");
				socket.send(new DatagramPacket(node.getTLV().Value(), node.getTLV().ValueLength(),
					Settings.target, Settings.port));
				Stresser.nodes.set(j, nodeRefresh(Util.generateMac(j), i));
				TimeUnit.MILLISECONDS.sleep((long)(Settings.frequency/Settings.nodeCount)*1000);
			}
		}
	}
	
	public void stop() {
		System.out.println("> Stopping Server Instance..");
		socket.close();
		System.out.println("> Server instance closed");
	}
	
	private TLVBuilder nodeRefresh(MacAddress mac, Integer i) {
		//TODO
		TLVBuilder builder = new TLVBuilder();
		builder.setUptime((i + 1) * 60);
		builder.setMacAddress(mac);
		builder.setNodeType(EnumNodeType.NODE_TYPE_VERISTH1011);
		builder.setNodeSubType(EnumNodeSubType.NODE_SUBTYPE_UNKNOWN);
		builder.setSoftwareVersion(1, 15);
		builder.addSensorBattery(BatteryType.INTERNAL, BatteryValueType.VOLTS_IN_THOUSANDTHS,
			0, ((Math.random())*0.5)+2.8, false, 0);
		builder.addSensorRSSI(211 + randInt(0, 7), false, 0);
		builder.addSensorTemp((double)(randInt(21, 26)));
		builder.addSensorHumidity((double)randInt(40, 70));
		
		return builder;
	}
	
	private static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
}
