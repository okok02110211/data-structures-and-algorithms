package com.ohgiraffers.section03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Practice1 {
    public static int solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int CityTotal = Integer.parseInt(st.nextToken());
        int[] RoadLength = new int[CityTotal-1];
        int[] GasPrice = new int[CityTotal];
        int PriceSum = GasPrice[0] * RoadLength[0];
        int Min_Price = GasPrice[0];// 첫 값을 가장 작은 값으로 설정

        for(int i = 0 ; i < CityTotal -1 ; i++){
            RoadLength[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < CityTotal ; i++){
            GasPrice[i] = Integer.parseInt(st.nextToken());
        }
        // 가장 가까운 가스값이 작은 스테이션 찾기
        for(int i = 1; i < CityTotal; i++){
            if(Min_Price > GasPrice[i]){

            }
        }

        PriceSum = GasPrice[0] * RoadLength[0];

        return 0;
    }
}
