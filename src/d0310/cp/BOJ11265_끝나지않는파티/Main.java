package d0310.cp.BOJ11265_끝나지않는파티;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n; // num of parties
    static int m; // num of guests
    static long[][] cost = new long[500][500];

    public static void main(String[] args) throws IOException {
        input();

        // 각각 다른 출발점에 대해서 모든 최단거리가 필요하므로
        // 무지성 플로이드워셜 알고리즘 적용
        for (int m = 0; m < n; m++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (cost[s][e] > cost[s][m] + cost[m][e]) {
                        cost[s][e] = cost[s][m] + cost[m][e];
                    }
                }
            }
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = parseInt(st.nextToken()) - 1;
            int e = parseInt(st.nextToken()) - 1;
            int lim = parseInt(st.nextToken());
            sb.append(cost[s][e] <= lim ? "Enjoy other party" : "Stay here").append('\n');
        }

        System.out.print(sb);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                cost[i][j] = parseInt(st.nextToken());
            }
        }
    }
}
