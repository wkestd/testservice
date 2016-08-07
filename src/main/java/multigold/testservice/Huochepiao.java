package multigold.testservice;

public class Huochepiao
{
 public static void main(String[] args)
 {
  TestThread1 tt=new TestThread1();
  new Thread(tt).start();        //每一个售票点定义为一个独立的线程，共同卖出所有的票
  new Thread(tt).start();
  new Thread(tt).start();
  new Thread(tt).start();
 }
}

class TestThread1 implements Runnable
{
 int ticket=1000;                 //票数定义
 String str = "";
 public void run(){
	 while(true)
	 synchronized (str) {
		 if (ticket>0) {
		  {
		     System.out.println(Thread.currentThread().getName()+":ticket "+ticket+"is saling");
		     ticket--;
		     try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	}
		 }

 }

}