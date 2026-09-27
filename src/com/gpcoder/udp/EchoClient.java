package com.gpcoder.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Client o che do khong noi ket (UDP): nhap chuoi tu ban phim, gui sang
 * EchoServer, cho nhan va in du lieu server tra ve.
 */
public class EchoClient {

	public final static String SERVER_IP = "127.0.0.1";
	public final static int SERVER_PORT = 7777;
	public final static byte[] BUFFER = new byte[4096];

	public static void main(String[] args) {
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(); // Tao DatagramSocket
			System.out.println("Client started ");

			InetAddress server = InetAddress.getByName(SERVER_IP);
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			while (true) {
				System.out.println("Enter your message (or 'exit' to quit): ");
				String theString = br.readLine(); // Nhap 1 chuoi tu ban phim
				if (theString == null || theString.equalsIgnoreCase("exit")) {
					break;
				}
				byte[] data = theString.getBytes(); // Doi chuoi ra mang bytes

				// Tao goi tin gui
				DatagramPacket dp = new DatagramPacket(data, data.length, server, SERVER_PORT);
				ds.send(dp); // Gui goi tin sang Echo Server

				// Goi tin nhan
				DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
				ds.receive(incoming); // Cho nhan du lieu tu EchoServer gui ve

				// Doi du lieu nhan duoc dang mang bytes ra chuoi va in ra man hinh
				System.out.println("Received: " + new String(incoming.getData(), 0, incoming.getLength()));
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (ds != null) {
				ds.close();
			}
		}
	}
}
