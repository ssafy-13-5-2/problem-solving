package d0307.BOJ1446_지름길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int w;
    int u;

    Edge(int w, int u) {
        this.w = w;
        this.u = u;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int INF = Integer.MAX_VALUE;

    static int n, d;
    static List<Edge>[] graph = new ArrayList[10001];
    static int[] dist = new int[10001];
    static boolean[] visit = new boolean[10001];

    public static void main(String[] args) throws IOException {
        input();

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        dist[0] = 0;
        pq.offer(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (visit[curr.u]) continue;
            visit[curr.u] = true;

            // 지름길이 없더라도 기본적으로 u와 u+1은 비용이 1인 간선으로 연결되어 있음
            if (curr.u + 1 <= d && dist[curr.u] + 1 < dist[curr.u + 1]) {
                dist[curr.u + 1] = dist[curr.u] + 1;
                pq.offer(new Edge(dist[curr.u + 1], curr.u + 1));
            }

            // 지름길은 별도로 고려
            for (Edge next : graph[curr.u]) {
                if (dist[curr.u] + next.w >= dist[next.u]) continue;
                dist[next.u] = dist[curr.u] + next.w;
                pq.offer(new Edge(dist[next.u], next.u));
            }
        }

        System.out.println(dist[d]);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 10001; i++) dist[i] = INF;

        for (int i = 0; i < 10001; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(w, e));
        }
    }
}
