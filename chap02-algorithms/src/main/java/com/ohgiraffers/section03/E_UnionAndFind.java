package com.ohgiraffers.section03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* Union & Find
 * 집합 간의 연산을 효율적으로 처리하기 위해 설계 된 데이터 구조
 * 특히 집합의 합집합(union)과 특정 원소가 속한 집합의 찾기(find) 연산을 빠르게 처리하는데 유용하다.
 * 집합은 서로 다른 원소들로 구성되며, 초기에는 각 원소가 독립적인 집합을 형성한다.
 *
 * 경로 압축(path compression)과 union by rank를 사용하면 거의 상수 시간에 집합 병합과 탐색이 가능하다.
 */
public class E_UnionAndFind {
    static int[] parent;

    /* 각 루트가 대표하는 집합의 크기를 저장한다.
     * 두 집합을 합칠 때, 더 작은 집합을 큰 집합에 붙여서 트리 높이를 낮춘다.*/
    static int[] size;

    /* 특정 원소가 속한 집합을 찾는 연산 */
    static int find(int x) {
        /* 집합의 대표 원소 (루트 노드) 를 찾고
         * 대표 원소를 알면 두 원소가 같은 집합에 속하는지 알 수 있다. */
        if (parent[x] != x) {
            parent[x] = find(parent[x]);   // 경로 압축(Path Compression)
        }
        return parent[x];
    }

    /* 두 개의 집합을 하나로 합치는 연산
     * 두 집합의 대표 원소를 비교하여 두 집합이 연결 되도록 한다. */
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        /* union by size
         * 더 작은 집합을 더 큰 집합에 붙인다.
         * 이렇게 하면 트리의 높이가 줄어들어 이후 find 연산이 더 빨라진다. */
        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;        // 작은 X집합을 큰 Y집합에 붙임
            size[rootY] += size[rootX];   // 새 루트 Y의 크기 업데이트
        } else {
            parent[rootY] = rootX;        // 작은 Y집합을 큰 X집합에 붙임
            size[rootX] += size[rootY];   // 새 루트 X의 크기 업데이트
        }
    }

    public static String solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st;

        // 학생 수와 관계 읽어오기
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 학생 수
        int M = Integer.parseInt(st.nextToken());   // 제공 된 학생 쌍

        // parent 배열 초기화 (처음에는 각각의 요소가 별도의 집합으로 구성)
        parent = new int[N + 1];
        size   = new int[N + 1];   // 집합 크기 배열 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;           // 처음 각 원소는 크기가 1인 개별 집합
        }

        // M개의 학생 쌍 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);    // a 학생과 b 학생을 같은 집합으로 만듦

            // 디버깅 출력
            System.out.println("union(" + a + "," + b + ")");
            System.out.println("parent : " + Arrays.toString(parent));
            System.out.println("size : " + Arrays.toString(size));
        }

        // 마지막 쌍의 친구 관계 확인
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        if (find(x) == find(y)) {
            return "YES";
        } else {
            return "NO";
        }
    }
}