package com.ohgiraffers.section03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 최소 신장 트리 (MST, Minimum Spanning Tree)
 * 주어진 그래프의 모든 정점을 연결하는 부분 그래프 중 가중치의 값이 최소인 트리
 *
 * 크루스칼 알고리즘
 * 간선을 오름차순으로 정렬하고, 사이클을 형성하지 않도록 최소 신장 트리를 구성하는 방법
 * (union & find 자료구조를 통해 사이클 여부 확인)
 * 크루스칼 알고리즘은 매 순간 가장 가중치가 작은 간선을 선택하는 탐욕적 방법을 사용
 */

public class F_KruskalAlgorithm {
    static int[] parent;
    static int[] size;
    static int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }
    // 간선 정보 클래스
    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;     // 정점 1
            this.v = v;     // 정점 2
            this.w = w;     // 가중치
        }

        @Override
        public int compareTo(Edge o) {  // 간선 정렬 시 가중치 오름차순
            return Integer.compare(this.w, o.w);
        }
    }
    public static Long solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[E];
        for(int i = 0; i < edges.length; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(u, v, w);
        }
        return mstKruskal(V, edges);
    }

    static long mstKruskal(int V, Edge[] edges) {
        /* union and find를 위한 세팅 */
        parent = new int[V + 1];
        size = new int[V + 1];
        for(int i = 1; i <= V; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // 가중치 오름차순 정렬
        Arrays.sort(edges);

        long totalWeight = 0L;

        // 가중치 작은 간선부터 선택해나가는 작업
        for(Edge edge : edges) {
            // 각각의 정점이 연결 되어 있는지 확인
            if(find(edge.u) != find(edge.v)) {
                // 연결되어 있지 않은 경우 선택한다
                union(edge.u, edge.v);
                totalWeight += edge.w;
            }
        }

        return totalWeight;
    }


}