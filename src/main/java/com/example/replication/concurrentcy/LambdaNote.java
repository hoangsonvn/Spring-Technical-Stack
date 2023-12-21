package com.example.replication.concurrentcy;

import com.example.replication.concurrentcy.completableFuture.Student;

import java.util.ArrayList;
import java.util.List;

public class LambdaNote {

    public static void changObject(Student student) {
        student.setName("son");

        Student student1 = new Student();
        student1.setSubject("subject changed");
        student = student1;
        System.out.println(student); //subject changed

    }

    public static Integer i = 1;

    public static void main(String[] args) {
//        Integer i = 1;
        Student studentNew = new Student();
        studentNew.setSubject("1");
        Student student1 = new Student();
        student1.setSubject("2");
        Student student2 = new Student();
        student2.setSubject("3");
        Student studentLamdba = new Student();
        studentLamdba.setName("lambda");

        List<Student> studentList = new ArrayList<>();
        studentList.add(studentNew);
        studentList.add(student1);
        studentList.add(student2);

        LambdaNote.changObject(studentLamdba);
        System.out.println(studentLamdba); //Son
// https://luanvv.com/blog/pass-by-value-va-pass-by-reference-trong-java/

/*        Consumer<completableFuture.Student> consumer = (s)->{
            student = new completableFuture.Student();
            main.changObject(student);
              };*/

        studentList.forEach(s -> {
            //          studentNew = new completableFuture.Student();  // lỗi vì biến trong lamba phải final hoặc effect final
            System.out.println(i); // static vẫn ok
            i++;
        });
    }
}
//NOTE:
// function trong lamba cũng là method function nên vẫn áp dụng tính tham chiếu như thường
// việc ta gán student = studentRepository.findByIp() thì student sẽ không phải tham chiều tới biến nào mới mà vẫn là object student cũ thay đổi giá trị bằng với studentRepositoryu.findByIp()
// Tức là studentRepository.findByIp() là giá trị chứ không phải 1 biến mới (" đã thử nghiệm trong lamba expression"
// NOte: pass refrence trong java khác C++ /c ở chỡ tạo ra 1 tham chiếu mới trong method đó và tham chiếu tới object chứ không truyền thẳng dịa chỉ ô nhớ