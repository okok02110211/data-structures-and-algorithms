package com.ohgiraffers.section03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

/* 동전 문제 (그리디 알고리즘)
 * 매 단계에서 현재 남은 금액을 줄이기 위해
 * 가장 큰 가치의 동전을 선택하여 사용하는 과정을 반복함으로써
 * 전체 동전 개수를 최소화하는 전략을 취한다.
 * 이 전략은 동전의 가치가 서로 배수 관계에 있을 때 항상 최적해를 보장한다.
 */
public class B_CoinChange {
    public static int solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 동전의 종류 개수
        int K = Integer.parseInt(st.nextToken());   // 계산할 금액
        int[] coins = new int[N];                   // 동전 종류 저장
        for(int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;  // 필요한 동전의 갯수

        /* 큰 동전부터 사용해 볼 수 있도록 배열을 반복한다. */
        for(int i = N - 1; i >= 0; i--) {
            /* 해당 동전을 사용할 가치가 있는 경우 */
            if(coins[i] <= K) {
                /* 현재 동전으로 최대 지불할 수 있는 금액 (동전의 수) */
                count += K / coins[i];
                /* 방금 지불하고 남은 금액을 다음 동전 확인을 위해 K에 대입 */
                K %= coins[i];
            }
        }

        return count;
    }
}