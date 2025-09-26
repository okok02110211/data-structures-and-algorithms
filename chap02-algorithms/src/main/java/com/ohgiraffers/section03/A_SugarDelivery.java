package com.ohgiraffers.section03;

/* 설탕 배달 문제
 * 매 단계마다 즉각적으로 최선의 선택이라고 생각되는 선택을 반복해서
 * 전체 문제의 해답(최소 봉지의 수)를 구하는 문항 */
public class A_SugarDelivery {

    public static int solution(int n) { // 배달해야 하는 설탕의 무게
        int count = 0;  // 봉지의 개수 반환

        while(true){
            if( n / 5 == 0 ){
                return n/5 + count;
            }else if(n / 3 == 0){
                return n/3 + count;
            }else if(n == 0){
                return count;
            }
            n -= 3;
            count ++;
        }
    }



}