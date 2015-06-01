package com.intellastar.stresser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.TimerTask;

import com.intellastar.evrisko.TLVBuilder;

public class BroadcastNode extends TimerTask {

	TLVBuilder node;
	DatagramSocket socket;
	
	public BroadcastNode(DatagramSocket socket, TLVBuilder node) {
		this.node = node;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		System.out.println("Broadcasting " + node.getMacData());
		try {
			socket.send(
				new DatagramPacket(node.getTLV().Value(), node.getTLV().Value().length));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
