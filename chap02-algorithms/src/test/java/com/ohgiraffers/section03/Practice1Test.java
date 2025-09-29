package com.ohgiraffers.section03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Practice1Test {
    private static String input1,input2;
    private static int output1,output2;


    @BeforeAll
    public static void set(){

        input1 = "4\n" +
                "2 3 1\n" +
                "5 2 4 1\n";

        output1 = 18;

        input2 = "4\n" +
                "3 3 4\n" +
                "1 1 1 1\n";

        output2 = 10;
    }
    public static Stream<Arguments> provideSource(){
        return Stream.of(
                arguments(input1,output1),
                arguments(input2,output2));
    }

    @DisplayName("GasStation")
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    @ParameterizedTest
    @MethodSource("provideSource")
    public void Practice1Test(String input,int output) throws IOException {
        int result = Practice1.solution(input);
        Assertions.assertEquals(output,result);
    }
}
