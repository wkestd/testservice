package multigold.testservice;

public class TextThread {

public static void main(String[] args) { 
   TxtThread tt1 = new TxtThread("1"); 
   TxtThread tt2 = new TxtThread("2");
   Thread t1 = new Thread(tt1, "t1");
   Thread t2 = new Thread(tt2, "t2");
   t1.start();
   t2.start();
//   new Thread(tt).start(); 
//   new Thread(tt).start(); 
//   new Thread(tt).start(); 
//   new Thread(tt).start(); 
} 
}

class TxtThread implements Runnable { 
int num = 100; 
String str = new String();
TxtThread(String str){
	this.str = str;
}
public void run() { 
   synchronized (str) { 
    while(num > 0) {
     try { 
    	 Thread.currentThread().sleep(1);
    	// Thread.sleep(1000);
     } catch (Exception e) { 
      e.getMessage(); 
     } 
     System.out.println(Thread.currentThread().getName() 
       + "this is " + num--); 
    } 
   } 
  
} 
}