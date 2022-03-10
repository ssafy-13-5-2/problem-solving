package d0310.cp.BOJ9465_스티커;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    final static int NOT = 0;
    final static int TOP = 1;
    final static int BOT = 2;

    public static void main(String[] args) throws IOException {
        int test = parseInt(br.readLine());
        while (test-- > 0) {
            int n = parseInt(br.readLine());
            int[][] board = new int[2][n];
            long[][] dp = new long[n][3];
            // dp[i][NOT] = i번째 열에서 아무 것도 뜯지 않았을 때, 0~i번째 열까지 뜯은 스티커의 점수의 합
            // dp[i][TOP] = i번째 열에서 위의 것을 뜯을 때, 0~i번째 열까지 뜯은 스티커의 점수의 합
            // dp[i][BOT] = i번째 열에서 아래의 것을 뜯을 때, 0~i번째 열까지 뜯은 스티커의 점수의 합
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    board[i][j] = parseInt(st.nextToken());
                }
            }

            dp[0][NOT] = 0;
            dp[0][TOP] = board[0][0];
            dp[0][BOT] = board[1][0];
            for (int i = 1; i < n; i++) {
                dp[i][NOT] = max(dp[i - 1][NOT], dp[i - 1][TOP], dp[i - 1][BOT]);
                dp[i][TOP] = max(dp[i - 1][NOT], dp[i - 1][BOT]) + board[0][i];
                dp[i][BOT] = max(dp[i - 1][NOT], dp[i - 1][TOP]) + board[1][i];
            }

            sb.append(max(dp[n - 1][NOT], dp[n - 1][TOP], dp[n - 1][BOT])).append('\n');
        }

        System.out.print(sb);
    }

    static long max(long... a) {
        long mx = Long.MIN_VALUE;
        for (long i : a) {
            if (i > mx) {
                mx = i;
            }
        }
        return mx;
    }
}
