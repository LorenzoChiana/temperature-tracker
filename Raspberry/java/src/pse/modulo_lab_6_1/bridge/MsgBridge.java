package pse.modulo_lab_6_1.bridge;

import java.net.*;

import pse.modulo_lab_6_1.SerialCommChannel;

import java.io.*;
/**
 * 
 * @author aricci
 *
 */
public class MsgBridge {
	
	private SerialCommChannel serialChannel;
	private ServerSocket socket;
	private Socket channel;
	
	public MsgBridge(String serialPort) throws Exception {
		serialChannel = new SerialCommChannel(serialPort,9600);		
	}

	public void connect() throws Exception {
		socket = new ServerSocket(8080);
		log("server socket created on 8080");
		log("Waiting for Android connection...");
		channel = socket.accept();
		log("new connection accepted, starting the agents..");
		new And2ArdAgent().start();
		new Ard2AndAgent().start();
		log("agents started.");
	}

	private void log(String msg){
		System.out.println("[MsgBridge] "+msg);
	}

	class And2ArdAgent extends Thread {
		
		public void run(){
			try {
					byte[] buffer = new byte[512];
					log("ready.");
						InputStream inputStream = channel.getInputStream();
						while (true){
							int size = inputStream.read();
							buffer[0] = (byte) size;
							StringBuffer sb = new StringBuffer();
							for (int i = 0; i < size; i++){
								int v = inputStream.read();
								if (v != -1){
									buffer[i+1] = (byte)v;
									sb.append((char)v);
								} else {
									throw new Exception("wrong data received");
								}
							}
							log("new msg from Android: "+sb.toString()+" - size "+size+" bytes");
							serialChannel.sendRawMsg(buffer);
							log("msg sent to Arduino.");
						}
					} catch (Exception ex){
						ex.printStackTrace();
					}
		}

		private void log(String msg){
			System.out.println("[AN2ARAgent] "+msg);
		}
		
	}
	
	class Ard2AndAgent extends Thread {
		
		public void run(){
			try {
				while (true){
					log("ready");
						String msg = serialChannel.receiveMsg();
						log("new msg from Arduino: "+msg+" - size "+msg.length());
						OutputStream outputStream = channel.getOutputStream();
						byte[] buffer = new byte[msg.length()+1];
						buffer[0] = (byte) msg.length();
						for (int i = 0; i < msg.length(); i++){
							buffer[i+1] = msg.getBytes()[i];
						}
						/*
						for (byte b: buffer){
							log("sending byte "+b);	
						}*/
						outputStream.write(buffer);
						outputStream.flush();
						log("msg sent to Android.");
				}
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}

		private void log(String msg){
			System.out.println("[AR2ANAgent] "+msg);
		}
	}		
	
	public static void main(String[] args) throws Exception {
		new MsgBridge(args[0]).connect();
	}
}
