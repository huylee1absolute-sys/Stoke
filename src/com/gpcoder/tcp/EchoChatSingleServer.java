package com.gpcoder.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server TCP phuc vu tuan tu (single-thread): tai mot thoi diem chi xu ly 1 client.
 */
public class EchoChatSingleServer {

	public final static int SERVER_PORT = 7777;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server started: " + serverSocket);
			System.out.println("Waiting for a client ...");
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					System.out.println("Client accepted: " + socket);

					OutputStream os = socket.getOutputStream();
					InputStream is = socket.getInputStream();
					int ch = 0;
					while (true) {
						ch = is.read(); // Nhan du lieu tu client
						if (ch == -1) {
							break;
						}
						os.write(ch); // Gui ket qua ve client
					}
					socket.close();
					System.out.println("Client disconnected.");
				} catch (IOException e) {
					System.err.println(" Connection Error: " + e);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}

}
