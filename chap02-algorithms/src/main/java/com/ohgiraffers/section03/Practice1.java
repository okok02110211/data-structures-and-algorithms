package com.ohgiraffers.section03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Practice1 {
    public static int solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        int CityTotal = Integer.parseInt(br.readLine().trim());
        int[] RoadLength = new int[CityTotal - 1];
        int[] GasPrice = new int[CityTotal];
        int curPo = 0;
        int nextPo = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < CityTotal - 1; i++) {
            RoadLength[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < CityTotal; i++) {
            GasPrice[i] = Integer.parseInt(st.nextToken());
        }
        int minPrice = GasPrice[0];
        int result = 0;
        for (int i = 0; i < CityTotal-1; i++) {
            if(GasPrice[i] < minPrice){
                minPrice = GasPrice[i];
            }
            result += RoadLength[i] * minPrice;
        }
        return result;
    }
}
