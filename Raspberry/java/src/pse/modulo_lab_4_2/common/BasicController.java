package pse.modulo_lab_4_2.common;

public abstract class BasicController extends Thread {

	protected void waitFor(long ms) throws InterruptedException{
		Thread.sleep(ms);
	}
}
