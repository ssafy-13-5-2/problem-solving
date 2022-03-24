package d0310.cp.assignment.BOJ1497_기타콘서트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static long[] guitars;
    static int max = 0;
    static int ans = 11;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        guitars = new long[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            st.nextToken();
            String l = st.nextToken();
            for (int j = 0; j < m; j++) {
                if (l.charAt(j) == 'Y') {
                    guitars[i] |= (1L << j);
                }
            }
        }

        foo(0, 0, 0L);

        System.out.println(max > 0 ? ans : -1);
    }

    static void foo(int idx, int cnt, long stat) {
        if (idx == n) {
            int songs = calc(stat);
            if (songs > max) {
                max = songs;
                ans = cnt;
            } else if (songs == max) {
                if (cnt < ans) {
                    ans = cnt;
                }
            }
            return;
        }

        foo(idx + 1, cnt, stat);

        foo(idx + 1, cnt + 1, stat | guitars[idx]);
    }

    static int calc(long stat) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if ((stat & (1L << i)) != 0) {
                cnt++;
            }
        }
        return cnt;
    }
}
