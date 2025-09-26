package com.ohgiraffers.section03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 회의실 배정(그리디)
 * 가장 빨리 끝나는 회의를 계속 선택하면 전체 선택 가능한 회의 수가 최대가 된다.
 * 구간은 [시작, 종료) 이므로 종료와 동시에 다음 회의를 시작할 수 있다.
 * 종료 시간이 같다면, 시작 시간이 빠른 회의를 먼저 보도록 정렬한다.
 * (0 길이 회의가 있을 때의 최대 선택 수 보장을 위함)
 * */
public class C_MeetingRoomScheduler {
    public static int solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        int N = Integer.parseInt(br.readLine());    // 회의 개수
        int[][] time = new int[N][2];  // 회의 개수 * (시간시간, 종료시간)
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());  // 시작
            time[i][1] = Integer.parseInt(st.nextToken());  // 종료
        }

        /* 기본 정렬 조건 : 종료 시간이 빠른 순
         * 종료 시간 같을 경우 : 시작 시간이 빠른 순 */
        Arrays.sort(time, (a, b) -> {
            if(a[1] != b[1]) return a[1] - b[1];    // 종료시간이 다르면 종료시간 오름차순
            return a[0] - b[0]; // 종료시간이 같다면 시작시간 오름차순
        });

        int endTime = 0;    // 직전 회의가 끝난 시간을 저장할 변수
        int count = 0;      // 회의가 배정 된 갯수

        /* time 배열 안에 있는 회의를 반복하며 회의 시간표에 넣을지 고민 */
        for(int i = 0; i < N; i++) {
            /* 직전 회의가 끝나는 시간과 일치하거나 그 이후에 시작 되는지 확인 */
            if(time[i][0] >= endTime) {
                count++;
                endTime = time[i][1];   // 이후 회의 확인을 위해 종료 시간 업데이트
            }
        }

        return count;
    }
}