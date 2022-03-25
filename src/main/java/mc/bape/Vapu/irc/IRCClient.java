package mc.bape.Vapu.irc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

import mc.bape.Vapu.irc.packet.Packet;
import mc.bape.Vapu.irc.packet.Packet00KeepAlive;
import mc.bape.Vapu.irc.packet.Packet01Login;
import mc.bape.Vapu.irc.packet.Packet02Chat;
import mc.bape.Vapu.irc.packet.PacketFFDisconnect;

public class IRCClient implements Runnable {
	private Socket socket;
	private NetworkManager networkManager;
	private ArrayList<IPacketListener> listener = new ArrayList<>();
	public IRCClient(SocketAddress address) throws IOException {
		this.socket = new Socket();
		socket.connect(address);
		this.networkManager = new NetworkManager(socket, this, new NetHandler() {
			protected void unhandlePacket(Packet packet) {
				for (IPacketListener listener : listener) {
					listener.handlePacket(packet);
				}
			}
		});
		new Thread(this, "IRC Thread").start();
	}
	public NetworkManager getNetworkManager() {
		return networkManager;
	}
	public boolean addListener(IPacketListener l) {
		if(listener.contains(l))
			return false;
		return listener.add(l);
	}
	public boolean removeListener(IPacketListener l) {
		return listener.remove(l);
	}
	@Override
	public void run() {
		for(;;) {
			runTick();
			if(networkManager.isTerminated())
				return;
			try {
				Thread.sleep(50L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void runTick() {
		networkManager.processReadPacket();
		networkManager.flush();
	}
	public static void main(String[] args) {
		try {
			IRCClient client = new IRCClient(new InetSocketAddress("127.0.0.1", 54188));
			client.addListener(new IPacketListener() {

				@Override
				public void handlePacket(Packet packet) {
					if(packet instanceof Packet02Chat) {
						System.out.println(((Packet02Chat) packet).getMessage());
					}
					if(packet instanceof PacketFFDisconnect) {
						System.out.println("Disconnected: "+((PacketFFDisconnect) packet).getReason());
					}
				}
				
			});
			client.getNetworkManager().addToSendQueue(new Packet01Login("TestUser1"));
			client.getNetworkManager().addToSendQueue(new Packet02Chat("Hello World!"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
