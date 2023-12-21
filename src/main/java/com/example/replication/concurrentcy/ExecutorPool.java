package com.example.replication.concurrentcy;

import com.example.replication.concurrentcy.completableFuture.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorPool {
    static int corePoolSize = 3;
    static int maximumPoolSize = 5;
    static int queueCapacity = 100;
//    static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, // Số corePoolSize
//            maximumPoolSize, // số maximumPoolSize
//            1000, // thời gian một thread được sống nếu không làm gì
//            TimeUnit.SECONDS,
//            new ArrayBlockingQueue<>(queueCapacity)); // Blocking queue để cho request đợi

    public static ExecutorService executor = Executors.newFixedThreadPool(5);
    public static ExecutorService executor1 = Executors.newFixedThreadPool(5);


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Student studentNew = new Student();
        studentNew.setSubject("1");
        Student student1 = new Student();
        student1.setSubject("2");
        Student student2 = new Student();
        student2.setSubject("3");
        Student student3 = new Student();
        student3.setName("nam");

        List<Student> studentList = new ArrayList<>();
        studentList.add(studentNew);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

//        executor1.execute(() -> System.out.println("execute"));


//        for (int i = 0; i <= 20; i++) {
//            MyRunnable myRunnable = new MyRunnable("Runnable " + i);
//            executor.execute(myRunnable);
//        }

        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            Future<Integer> future = executor1.submit(() -> {  // newFixedThreadPool kế thừa AbstractExecutorService
                System.out.println("submit");
                Thread.sleep(2000);
                return 2;
            });
            list.add(future);
        }

      /*  Integer numbers = 0;
        for (Future<Integer> future : list) {
            Integer number = future.get(); // lấy kq
            numbers += number;
        }
        System.out.println(numbers);*/

// OR

  /*      List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            Callable<Integer> callable = () -> {
                System.out.println(new Date().getTime() + "---" + Thread.currentThread().getId());
                Thread.sleep(2000);
                return 2;
            };
            callables.add(callable);
        }
        executor1.invokeAll(callables);*/


        for (Student student : studentList) {
//            CompletableFuture.supplyAsync(() -> new Date().getTime()).thenAccept((s) -> System.out.println(s));
//            CompletableFuture.runAsync(() -> new Date().getTime()).thenAccept((s) -> System.out.println(s));
            /* Tại sao runAsync nhận vào 1 Runable mà lại nagwns ngọn thành () ->  new Date().getTime(), vì Runable là 1 interface funccdameter nên ta dùng lamdbda expresssion luôn RunAble runabe = () ->  new Date().getTime() */

            //  System.out.println(123);
//        });
        }


        Thread.sleep(2000);
        System.out.println("done");
    }

}
// Supplier<U> không chứa tham số  có return kq
// consumer 1 hành động gì đó kiểu void
// runable: kieeur void k tra ve
// callable: tra ve 1 loi hua

// muốn chạy nhiều tác vụ và trả vè kết quả các tác vụ đó mà khogn chặn main thì ta dùng completableFuture (" nên dùng pool tự tạo thay folkjoin) qua allOf, join() , thực ra trong thư viện cũng
// e.execute(runable/callable) " tức cũng tạo ra thread rồi start để chạy phương thức run của thread đó"
// get() chặn main trả kq, join() trả kq

/* Execute() trong executorService() để run 1 runable / submit() thực thi runable hoặc callable return future, có thể future.get() để lấy ra kết quả.
 * */