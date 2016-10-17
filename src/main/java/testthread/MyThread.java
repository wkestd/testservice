package testthread;

public class MyThread implements Runnable{
	private int count = 5;
	String b  = "";
	@Override
	public void run() {
		synchronized (b) {
			count--;
		}
		
		System.out.println("count：：："+count);
	}
}
