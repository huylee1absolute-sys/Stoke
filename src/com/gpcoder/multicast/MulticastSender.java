package com.gpcoder.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Gui thong diep quang ba (multicast) den mot nhom dia chi lop D.
 */
public class MulticastSender {

	public static final String GROUP_ADDRESS = "230.0.0.1";
	public static final int PORT = 8888;

	public static void main(String[] args) throws InterruptedException {
		DatagramSocket socket = null;
		try {
			// Lay dia chi ma chung ta se gui den
			InetAddress address = InetAddress.getByName(GROUP_ADDRESS);

			// Tao mot Datagram Socket
			socket = new DatagramSocket();

			DatagramPacket outPacket = null;
			long counter = 0;
			while (true) {
				String msg = "Sent message No. " + counter;
				counter++;
				outPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, PORT);
				socket.send(outPacket);
				System.out.println("Server sent packet with msg: " + msg);
				Thread.sleep(1000); // Ngu 1 giay truoc khi gui thong diep tiep theo
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
