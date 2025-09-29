package com.ohgiraffers.section03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Practice2 {
    public static void solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-- >0){
            int N = Integer.parseInt(br.readLine().trim());
            int[][] scores = new int[N][2];
            for(int i = 0; i<N ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int Interview = Integer.parseInt(st.nextToken());
                int Paper = Integer.parseInt(st.nextToken());
                scores[i][0] = Paper;
                scores[i][1] = Interview;
            }
            Arrays.sort(scores, Comparator.comparingInt(x -> x[0]));
            int Min_interview = Integer.MAX_VALUE;
            for(int i = 0 ; i < N ; i++){
                if(scores[i][1] < Min_interview){

                }
            }
        }
    }
}
