package pse.modulo_lab_6_1.bridge;

import java.io.*;
import java.net.Socket;

/**
 * Testing simple message passing.
 * 
 * To be used with an Arduino connected via Serial Port
 * running modulo-lab-6.1/ping_pong_serial
 * 
 * @author aricci
 *
 */
public class TestPingPongBridge {

	public static void main(String[] args) throws Exception {
		/* attesa necessaria per fare in modo che Arduino completi il reboot */
		Socket channel = new Socket("localhost", 8080);
		OutputStream output = channel.getOutputStream();
		InputStream input = channel.getInputStream();
		
		String msg = "ping";
		byte[] outbuf = new byte[msg.length()+1];
		outbuf[0] = (byte) msg.length();
		for (int i = 0; i < msg.length(); i++){
			outbuf[i+1] = msg.getBytes()[i];
		}
		while (true){
			System.out.println("Sending ping");
			output.write(outbuf);
			output.flush();
			System.out.println("sent.");
			
			int size = input.read();
			System.out.println("Receiving msg - size: "+size);
			byte[] inbuf = new byte[size];
			for (int i = 0; i < size; i++){
				inbuf[i] = (byte)input.read();
			}
			System.out.println("Received: "+new String(inbuf));		
			Thread.sleep(500);
		}
	}

}
