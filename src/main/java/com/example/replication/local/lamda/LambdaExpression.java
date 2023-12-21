package com.example.replication.local.lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LambdaExpression {

    public void changObject(Student student) {
        student.setName("son");
        Student student1 = new Student();
        student1.setSubject("123");
        student = student1;
//        student.setSubject("!23");
        System.out.println(student);

    }

    //public static Integer i = 1;
    public static void main(String[] args) {
        Integer i = 1;

        Student studentNew = new Student();
        studentNew.setSubject("1");
        Student student1 = new Student();
        student1.setSubject("2");
        Student student2 = new Student();
        student2.setSubject("3");
        Student student = new Student();
        student.setName("nam");
        LambdaExpression main = new LambdaExpression();
    //    main.changObject(student);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentNew);
        studentList.add(student1);
        studentList.add(student2);
        for (Student student3 : studentList) {
//            System.out.println(student3);
//            CompletableFuture.runAsync(()->{
//               i=i+2;
//                System.out.println(i);
//            });
//            student = new Student();
//            main.changObject(student);
        }

/*        Consumer<Student> consumer = (s)->{
            student = new Student();
            main.changObject(student);
              };*/
        //    studentList.forEach(System.out::println);
        studentList.forEach(s -> {

//            student = new Student();   lỗi vì biến trong lamba phải final hoặc effect final
            //      main.changObject(student);
            CompletableFuture.runAsync(() -> System.out.println(1));
          //  System.out.println(123);
        });
//        System.out.println(student);
    }
    // function trong lamba cũng là method function nên vẫn áp dụng tính tham chiếu như thường
    // việc ta gán student = studentRepository.findByIp() thì student sẽ không phải tham chiều tới biến nào mới mà vẫn là object student cũ thay đổi giá trị bằng với studentRepositoryu.findByIp()
// Tức là studentRepository.findByIp() là giá trị chứ không phải 1 biến mới (" đã thử nghiệm trong lamba expression"
// https://luanvv.com/blog/pass-by-value-va-pass-by-reference-trong-java/
// NOte: pass refrence trong java khác C++ /c ở chỡ tạo ra 1 tham chiếu mới trong method đó và tham chiếu tới object chứ không truyền thẳng dịa chỉ ô nhớ
}
