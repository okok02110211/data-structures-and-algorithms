package com.ohgiraffers.section02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class C_DFSBFS {

    static int node, edge, start;
    static int[][] map;
    static boolean[] visit;
    static StringBuilder sb;

    public static String solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        map = new int[node + 1][node + 1];
        visit = new boolean[node + 1];

        for (int i = 1; i <= edge; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = map[b][a] = 1;
        }

        sb = new StringBuilder();

        dfs(start);
//        dfsWithStack(start);
        sb.append("\n");

        /* 위의 호출에서 사용 된 방문 배열을 reset */
        visit = new boolean[node + 1];

        bfs(start);

        return sb.toString();
    }

    static void dfs(int start) {
        visit[start] = true;
        sb.append(start).append(" ");

        for (int i = 1; i <= node; i++) {
            if(map[start][i] == 1 && !visit[i]) {
                dfs(i);
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visit[start] = true;

        while(!q.isEmpty()) {
            start = q.poll();
            sb.append(start).append(" ");

            for (int i = 1; i <= node; i++) {
                if(map[start][i] == 1 && !visit[i]) {
                    q.offer(i);
                    visit[i] = true;
                }
            }
        }
    }

    // stack은 후입 선출이기 때문에 현재 문제에서 그대로 사용하면 큰 번호가 먼저 나올 수 있어 권장 X
    // 재귀 DFS와 동일한 순서를 보장하는 스택 DFS를 만드려면 아래와 같이 복잡해진다.
    static void dfsWithStack(int start) {
        Stack<Integer> stack = new Stack<>();

        stack.push(start);
        visit[start] = true;
        sb.append(start).append(" ");

        while (!stack.isEmpty()) {
            int v = stack.peek();
            boolean advanced = false;

            // 작은 번호부터 첫 번째 미방문 이웃을 하나만 찾아서 내려간다
            for (int i = 1; i <= node; i++) {
                if (map[v][i] == 1 && !visit[i]) {
                    visit[i] = true;
                    sb.append(i).append(" ");
                    stack.push(i);
                    advanced = true;
                    break; // 한 이웃만 내려가서 재귀와 동일한 순서 보장
                }
            }

            // 더 내려갈 이웃이 없으면 backtrack
            if (!advanced) {
                stack.pop();
            }
        }
    }
}