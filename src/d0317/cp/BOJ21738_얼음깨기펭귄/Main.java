package d0317.cp.BOJ21738_얼음깨기펭귄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n; // num of ice
    static int s; // num of root ice
    static int p; // idx of ice under penguin
    static List<Integer>[] graph = new ArrayList[328001];
    static boolean[] visit = new boolean[328001];
    static int[] dist = new int[328001];

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 328000; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        bfs(p);

        Arrays.sort(dist, 1, s + 1);

        System.out.println(n - 1 - (dist[1] + dist[2]));
    }

    static void bfs(int u) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);
        visit[u] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int adj : graph[curr]) {
                if (visit[adj]) continue;
                queue.offer(adj);
                visit[adj] = true;
                dist[adj] = dist[curr] + 1; // 펭귄 위치로부터 각 루트 얼음까지의 최단 거리를 저장
            }
        }
    }
}
