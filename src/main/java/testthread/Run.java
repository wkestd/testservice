package testthread;

public class Run {
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		Thread a = new Thread(myThread, "a");
		Thread b = new Thread(myThread, "b");		
		Thread c = new Thread(myThread, "c");
		Thread d = new Thread(myThread, "d");
		a.start();
		b.start();
		c.start();
		d.start();
	}
}
