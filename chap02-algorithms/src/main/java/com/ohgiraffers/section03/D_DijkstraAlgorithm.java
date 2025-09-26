package com.ohgiraffers.section03;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/* 다익스트라 알고리즘
 * 음의 가중치가 없는 그래프의 한 정점에서 모든 정점까지의 최단 거리를 구하는 알고리즘
 * 간선에 가중치가 없으면 BFS로도 가능하지만 가중치가 있다면 최단 경로 보장이 어렵다.
 *
 * 매 단계에서 아직 확정되지 않은 정점 중 최단 거리(가장 작은 값) 를 탐욕적으로(그리디) 선택해 확정
 * */
public class D_DijkstraAlgorithm {
    static int n, m, start;
    static long[] dis;
    static class Edge implements Comparable<Edge> {
        int to;         // 해당 간선이 연결 된 정점
        long w;         // 가중치 (거리)
        Edge(int to, long w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.w, o.w);
        }
    }

    public static String solution(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 정점 개수
        m = Integer.parseInt(st.nextToken());   // 간선 개수
        start = Integer.parseInt(st.nextToken());   // 시작 정점

        List<List<Edge>> graph = new ArrayList<>(n + 1);
        for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, c));
        }

        // 각 노드의 가중치 기록할 배열
        dis = new long[n + 1];
        // 아직 거리가 판단 되지 않은 경우에는 최대값으로 초기화
        Arrays.fill(dis, Long.MAX_VALUE);

        dijkstra(graph, start);

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i < dis.length; i++) {
            if(dis[i] != Long.MAX_VALUE) sb.append(dis[i]);
            else sb.append("impossible");
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    static void dijkstra(List<List<Edge>> g, int s) {
        /* 우선 순위 큐에 Edge가 담겼을 때 우선 순위는 가중치가 낮은 순서로 정해짐 */
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dis[start] = 0;

        while(!pq.isEmpty()) {
            /* 우선 순위 큐에서 먼저 꺼내는 간선은 가중치가 가장 작은(거리가 가장 짧은) 간선이다. */
            Edge cur = pq.poll();
            int v = cur.to;
            long d = cur.w;

            /* 거리 배열에 기록 된 값보다 현재 거리가 멀면 skip */
            if(d > dis[v]) continue;

            /* 기준 정점과 연결 된 이웃 정점을 큐에 추가하는 반복문 */
            for(Edge edge : g.get(v)) {
                long nd = d + edge.w;
                if(dis[edge.to] > nd) {
                    // 거리 배열에 저장 된 값이 현재 비용 + 간선 타고 가는 비용 보다 크면
                    // 새로운 루트로 업데이트 한다.
                    dis[edge.to] = nd;
                    pq.offer(new Edge(edge.to, nd));
                }
            }
        }
    }
}