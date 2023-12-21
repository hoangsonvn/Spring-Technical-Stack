package com.example.replication.concurrentcy;

class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();
 //       mt.start();
//     for(int i =0;i<1000;i++){
//         System.out.println(i);
//     }

        MyThread thread1 = new MyThread("Thread1");
        MyThread thread2 = new MyThread("Thread2");
        MyThread thread3 = new MyThread("Thread3");
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        thread2.join();
        thread3.start();
        System.out.println("final");
      for(int i= 1 ;i<1000;i++){
          System.out.println(i);
      }

    }
}
//Waits for this thread to die.
class MyThread extends Thread {
    public MyThread(String threadName) {
        super(threadName);
    }
    public MyThread() {

    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " " + i);
        }

//        for (int count = 1, row = 1; row < 20; row++, count++) {
//            for (int i = 0; i < count; i++)
//                System.out.print('*');
//            System.out.print('\n');
//        }
    }
}