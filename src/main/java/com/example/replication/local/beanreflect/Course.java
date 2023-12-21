package com.example.replication.local.beanreflect;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Course {
    private String name;
    private List<String> codes;
    private Map<String, Student> enrolledStudent = new HashMap<>();

    //  standard getters/setters
}

