package mc.bape.Vapu.irc;

import mc.bape.Vapu.irc.packet.Packet;

public interface IPacketListener {
	void handlePacket(Packet packet);
}
