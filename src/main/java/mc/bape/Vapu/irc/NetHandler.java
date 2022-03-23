package mc.bape.Vapu.irc;

import mc.bape.Vapu.irc.packet.Packet;
import mc.bape.Vapu.irc.packet.Packet00KeepAlive;
import mc.bape.Vapu.irc.packet.Packet01Login;
import mc.bape.Vapu.irc.packet.Packet02Chat;
import mc.bape.Vapu.irc.packet.PacketFFDisconnect;

public class NetHandler {
	protected NetworkManager netMgr;
	
	protected void unhandlePacket(Packet packet) {
		
	}
	public void handleKeepAlive(Packet00KeepAlive packet) {
		netMgr.addToSendQueue(packet);
	}
	public void handleLogin(Packet01Login packet) {
		
	}
	public void handleChat(Packet02Chat packet) {
		unhandlePacket(packet);
	}
	public void handleDisconnect(PacketFFDisconnect packet) {
		unhandlePacket(packet);
		netMgr.terminate();
	}
	public void addToSendQueue(Packet packet) {
		netMgr.addToSendQueue(packet);
	}

}
