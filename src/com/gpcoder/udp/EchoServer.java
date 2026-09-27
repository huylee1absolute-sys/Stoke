package com.gpcoder.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Echo Server o che do khong noi ket (UDP): nhan tung goi tin, tra nguoc
 * du lieu do ve cho client vua gui.
 */
public class EchoServer {

	public final static int SERVER_PORT = 7777; // Cong cua Echo Server
	public final static byte[] BUFFER = new byte[4096]; // Vung dem chua du lieu cho goi tin nhan

	public static void main(String[] args) {
		DatagramSocket ds = null;
		try {
			System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
			ds = new DatagramSocket(SERVER_PORT); // Tao Socket voi cong SERVER_PORT
			System.out.println("Server started ");
			System.out.println("Waiting for messages from Client ... ");

			while (true) { // Tao goi tin nhan
				DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
				ds.receive(incoming); // Cho nhan goi tin gui den

				// Lay du lieu khoi goi tin nhan
				String message = new String(incoming.getData(), 0, incoming.getLength());
				System.out.println("Received: " + message);

				// Tao goi tin gui chua du lieu vua nhan duoc
				DatagramPacket outsending = new DatagramPacket(message.getBytes(), incoming.getLength(),
						incoming.getAddress(), incoming.getPort());
				ds.send(outsending);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ds != null) {
				ds.close();
			}
		}
	}
}
