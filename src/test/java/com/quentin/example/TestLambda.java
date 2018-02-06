package com.quentin.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 10:35 2018/2/1
 * @Version 1.0
 */
public class TestLambda {

    @Test
    public void testlambda() {
        //In Java 8:遍历List集合
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        /*features.forEach(n -> {
                    System.out.println(n);
                    System.out.println(n.toString().length());
                }
        );*/

        features.forEach(System.out::println);



    }

}
