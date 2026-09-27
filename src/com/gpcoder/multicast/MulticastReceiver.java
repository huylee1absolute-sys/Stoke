package com.gpcoder.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Nhan cac thong diep quang ba (multicast) sau khi join vao mot nhom dia chi.
 */
public class MulticastReceiver {

	public static final byte[] BUFFER = new byte[4096];

	public static void main(String[] args) {
		MulticastSocket socket = null;
		DatagramPacket inPacket = null;
		try {
			// Lay dia chi nhom ma chung ta se ket noi den
			InetAddress address = InetAddress.getByName(MulticastSender.GROUP_ADDRESS);

			// Tao mot Multicast Socket
			socket = new MulticastSocket(MulticastSender.PORT);

			// Gia nhap nhom Multicast
			socket.joinGroup(address);

			while (true) {
				// Nhan thong tin va in ra
				inPacket = new DatagramPacket(BUFFER, BUFFER.length);
				socket.receive(inPacket);
				String msg = new String(BUFFER, 0, inPacket.getLength());
				System.out.println("From " + inPacket.getAddress() + " Msg : " + msg);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
}
