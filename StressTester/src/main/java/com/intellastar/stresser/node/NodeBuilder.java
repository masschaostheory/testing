package com.intellastar.stresser.node;

import com.intellastar.evrisko.MacAddress;
import com.intellastar.evrisko.TLVBuilder;
import com.intellastar.evrisko.enumeration.EnumHolder.BatteryType;
import com.intellastar.evrisko.enumeration.EnumHolder.BatteryValueType;
import com.intellastar.evrisko.enumeration.EnumNodeSubType;
import com.intellastar.evrisko.enumeration.EnumNodeType;
import com.intellastar.stresser.Stresser;
import com.intellastar.stresser.util.Util;

public class NodeBuilder {
	
	public static void create(Integer count) {
		for (int i = 0; i < count; i++) {
			MacAddress mac = Util.generateMac(i);
			
			TLVBuilder builder = new TLVBuilder();
			builder.setUptime(0);
			builder.setMacAddress(mac);
			builder.setNodeType(EnumNodeType.NODE_TYPE_VERISTH1011);
			builder.setNodeSubType(EnumNodeSubType.NODE_SUBTYPE_UNKNOWN);
			builder.setSoftwareVersion(1, 15);
			builder.addSensorBattery(BatteryType.INTERNAL, BatteryValueType.VOLTS_IN_THOUSANDTHS,
				0, ((Math.random())*0.5)+2.8, false, 0);
			builder.addSensorRSSI(211 + Util.randInt(0, 7), false, 0);
			builder.addSensorTemp((double)(Util.randInt(21, 26)));
			builder.addSensorHumidity((double)Util.randInt(40, 70));
			
//			TLV tlv = builder.getTLV();
			Stresser.nodes.add(builder);
			System.out.println(">> Created node " + mac.getAddr());
		}
	}
	
}
