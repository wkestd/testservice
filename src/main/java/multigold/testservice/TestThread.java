package multigold.testservice;

public class TestThread {
public static void main(String[] args) {
SyncStack ss = new SyncStack();
Producer p = new Producer(ss);
Consumer c = new Consumer(ss);
//new Thread(p).start();
//new Thread(p).start();
new Thread(p).start();
new Thread(c).start();
}
}


class WoTou {
int id;
WoTou(int id) {
this.id = id;
}
public String toString() {
return "WoTou : " + id+"号";
}
}


class SyncStack {
int index = 0;
WoTou[] arrWT = new WoTou[6];
public synchronized void push(WoTou wt) {
while(index == arrWT.length) {
try {
	System.out.println("生产者wait！");
this.wait();//锁(监听)在当前对象上的该线程停止住，失去当前对象锁，不归自己所有，直到被唤醒（拿到对象锁的线程遇到一个事件必需阻塞住，以便让吃的线程并行运行来吃快点）
} catch (InterruptedException e) {
e.printStackTrace();
}
}
//this.notify();//wait()完了后叫醒消费者快吃快吃去
arrWT[index] = wt;
index ++;
//叫醒一个正在wait在我这个对象上的线程
//System.out.println("push="+index);
}


public synchronized WoTou pop() {
while(index == 0) {
try {
	System.out.println("消费者wait！");
this.wait();
} catch (InterruptedException e) {
e.printStackTrace();
}
}
this.notify();
index--;

//System.out.println("pop="+index);
return arrWT[index];
}
}


class Producer implements Runnable {
SyncStack ss = null;
Producer(SyncStack ss) {
this.ss = ss;
}


public void run() {
for(int i=0; i<20; i++) {

WoTou wt = new WoTou(i);
ss.push(wt);
System.out.println("生产了：" + wt);
try {
Thread.sleep(100);
} catch (InterruptedException e) {
e.printStackTrace();
}
}
}
}


class Consumer implements Runnable {
SyncStack ss = null;
Consumer(SyncStack ss) {
this.ss = ss;
}


public void run() {
for(int i=0; i<20; i++) {
WoTou wt = ss.pop();
System.out.println("消费了: " + wt);
try {
Thread.sleep(1000);
} catch (InterruptedException e) {
e.printStackTrace();
}

}
}
}