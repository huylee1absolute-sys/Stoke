package com.gpcoder.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Client demo cho mo hinh TCP: gui lan luot cac so tu 0 den 9 sang Server,
 * Server se echo (gui nguoc lai) tung so nhan duoc.
 */
public class EchoChatClient {
	public final static String SERVER_IP = "127.0.0.1";
	public final static int SERVER_PORT = 7777; // dung port > 1024 de khong can quyen root

	public static void main(String[] args) throws IOException, InterruptedException {
		Socket socket = null;
		try {
			socket = new Socket(SERVER_IP, SERVER_PORT); // Ket noi den server
			System.out.println("Connected: " + socket);

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			for (int i = '0'; i <= '9'; i++) {
				os.write(i); // Gui tung so sang server
				int ch = is.read(); // Cho ket qua tra ve tu server
				System.out.print((char) ch + " "); // In ket qua nhan duoc tu server
				Thread.sleep(200);
			}
			System.out.println();
		} catch (IOException ie) {
			System.out.println("Can't connect to server");
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
}
