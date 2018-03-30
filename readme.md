# 生产者-消费者模型
生产者是一堆线程，消费者是另一堆线程，内存缓冲区可以使用List数组队列，数据类型只需要定义一个简单的类就好。关键是如何处理多线程之间的协作。

在这个模型中，最关键就是内存缓冲区为空的时候消费者必须等待，而内存缓冲区满的时候，生产者必须等待。其他时候可以是个动态平衡。值得注意的是多线程对临界区资源的操作时候必须保证在读写中只能存在一个线程，所以需要设计锁的策略。

下面的例子中，使用notify/wait()和await()/signal()方法来设计锁

Main类中的方法启动多个生产者进程和消费者继承，在启动生产者进程时指定使用的Buffer和Buffer最大可以存放的阈值。当Buffer中存放的数目大于这个阈值时，生产者进程拥塞，消费者进程开始消费；消费者进程消费完所有的产品之后，消费者进程拥塞，生产者进程开始生产；

即同一时刻只可能有一种进程（生产者线程 或者是 消费者线程）在运行
```java
/**
 * 基本数据类型。生产和消费的产品
 *
 * @author 张中俊
 * @create 2018-03-29 20:34
 **/
public class PCData {
    private long value;
    public void set(long value){
        this.value = value;

    }
    public long get(){
        return value;
    }
}
```
```java
/**
 * 生产者
 *
 * @author 张中俊
 * @create 2018-03-29 20:33
 **/
public class Producer implements Runnable {
    private List<PCData> queue;
    private int length;

    public Producer(List<PCData> queue, int length) {
        this.queue = queue;
        this.length = length;
    }

    public void run() {
        try {
            while (true) {

                if (Thread.currentThread().isInterrupted())
                    break;
                Random r = new Random();
                long temp = r.nextInt(100);
                System.out.println(Thread.currentThread().getName() + " 生产了：" + temp);
                PCData data = new PCData();
                data.set(temp);
                synchronized (queue) {
                    if (queue.size() >= length) {
                        queue.notifyAll();
                        queue.wait();
                    } else
                        queue.add(data);
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
```java
/**
 * @author 张中俊
 * @create 2018-03-29 20:35
 **/
public class Consumer implements Runnable {
    private List<PCData> queue;

    public Consumer(List<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                PCData data = null;
                synchronized (queue) {
                    if (queue.size() == 0) {
                        queue.wait();
                        queue.notifyAll();
                    }
                    data = queue.remove(0);
                }
                System.out.println(
                        Thread.currentThread().getName() + " 消费了:" + data.get() + " result:" + (data.get() * data.get()));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
```java
/**
 * @author 张中俊
 * @create 2018-03-29 20:35
 **/
public class Main {
    public static void main(String[] args) {
        List<PCData> queue = new ArrayList<PCData>();
        int length = 10;
        Producer p1 = new Producer(queue,length);
        Producer p2 = new Producer(queue,length);
        Producer p3 = new Producer(queue,length);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
    }
}
```