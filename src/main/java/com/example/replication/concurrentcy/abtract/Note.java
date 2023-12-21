package com.example.replication.concurrentcy.abtract;

public class Note extends AbtractNote{

    @Override
    public void runlen() {
        System.out.println(1);
    }

    public static void main(String[] args) {
        Note note = new Note();
        Thread thread = new Thread(new Note());
        thread.start();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println(123);
    }
}
