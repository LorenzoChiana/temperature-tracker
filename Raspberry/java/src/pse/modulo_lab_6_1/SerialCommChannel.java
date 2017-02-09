package pse.modulo_lab_6_1;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.*;
import java.util.concurrent.*;

/**
 * Comm channel implementation based on serial port.
 * 
 * @author aricci
 *
 */
public class SerialCommChannel implements CommChannel, SerialPortEventListener {

	private SerialPort serialPort;
	private InputStream input;
	private OutputStream output;
	private BlockingQueue<String> queue;

	public SerialCommChannel(String port, int rate) throws Exception {
		queue = new ArrayBlockingQueue<String>(100);

		CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(port);
		// open serial port, and use class name for the appName.
		SerialPort serialPort = (SerialPort) portId.open(this.getClass().getName(), 2000);

		// set port parameters
		serialPort.setSerialPortParams(rate,
				SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE);

		// serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
		// open the streams
		input = serialPort.getInputStream();
		output = serialPort.getOutputStream();

		// add event listeners
		serialPort.addEventListener(this);
		serialPort.notifyOnDataAvailable(true);

	}

	@Override
	public void sendMsg(String msg) throws MsgTooBigException {
		if (msg.length() > 255){
			throw new MsgTooBigException();
		}
		char[] array = msg.toCharArray();
		byte[] bytes = new byte[array.length+1];
		bytes[0] = (byte) msg.length();
		for (int i = 0; i < array.length; i++){
			bytes[i+1] = (byte) array[i];
		}
		try {
			output.write(bytes);
			output.flush();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void sendRawMsg(byte[] bytes) throws MsgTooBigException {
		try {
			output.write(bytes);
			output.flush();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public String receiveMsg() throws InterruptedException {
		return queue.take();
	}

	@Override
	public boolean isMsgAvailable() {
		return !queue.isEmpty();
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			// log("receiving...");
			try {
				int size = input.read();
				// log("> rec "+size+" bytes");
				int i = 0;
				StringBuffer buf = new StringBuffer("");
				while (i < size){
					int data = input.read();
					buf.append((char)data);
					i++;
					// log("> rec "+(char)data);
				}
				// log("> rec done: "+buf);
				queue.put(buf.toString());
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	protected void log(String msg){
		System.out.println("[SerialComm] "+msg);
	}
}
