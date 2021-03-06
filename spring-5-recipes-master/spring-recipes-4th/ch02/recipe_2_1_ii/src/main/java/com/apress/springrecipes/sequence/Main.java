package com.apress.springrecipes.sequence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.apress.springrecipes.sequence");

        SequenceDao sequenceDao = context.getBean(SequenceDao.class);

        System.out.println("start");
        for (int i = 0; i <10; i++) {
            System.out.println(sequenceDao.getSequence("IT"));
            System.out.println(sequenceDao.getNextValue("IT"));
        }
        System.out.println("end");
    }
}
