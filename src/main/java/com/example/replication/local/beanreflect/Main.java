package com.example.replication.local.beanreflect;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Course course = new Course();
        String name = "Computer Science";
        List<String> codes = Arrays.asList("CS", "CS01");

        PropertyUtils.setSimpleProperty(course, "name", name);
        PropertyUtils.setSimpleProperty(course, "codes", codes);
        course.getCodes();
        System.out.println(course);
    }
}
