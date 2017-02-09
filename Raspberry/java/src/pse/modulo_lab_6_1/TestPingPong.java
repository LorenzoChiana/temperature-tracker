package pse.modulo_lab_6_1;

/**
 * Testing simple message passing.
 * 
 * To be used with an Arduino connected via Serial/BlueTooth
 * running modulo-lab-6.1/ping_pong
 * 
 * @author aricci
 *
 */
public class TestPingPong {

	public static void main(String[] args) throws Exception {
		SerialCommChannel channel = new SerialCommChannel(args[0],9600);		
		System.out.println("Ready to go.");				
		while (true){
			System.out.println("Sending ping");
			channel.sendMsg("ping");
			String msg = channel.receiveMsg();
			System.out.println("Received: "+msg);		
			Thread.sleep(500);
		}
	}

}
