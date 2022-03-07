package d0307.BOJ16194_카드구매하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] p = new long[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            System.out.println(p[1] * n);
            return;
        }

        long[][] dp = new long[1001][1001];
        // dp[r][i] = i개짜리 묶음까지 고려했을 때, r장을 구입하는 경우 최소 비용

        for (int i = 1; i <= n; i++) dp[i][1] = p[1] * i; // 1개짜리 묶음이 있으므로 못 사는 경우는 없음
        for (int c = 2; c <= n; c++) {
            for (int r = 1; r <= n; r++) {
                dp[r][c] = dp[r][c - 1]; // 기본적으로 c개짜리 묶음을 고려하지 않았을 때가 최소비용
                if (r % c == 0) {
                    dp[r][c] = min(dp[r][c], p[c] * (r / c)); // c개짜리 묶음만으로 구매가 가능할 때 더 싸다면 갱신
                }
            }
        }

        // dp[r][c] = min(
        //                  c-1개짜리 묶음까지 고려했을 때 r개를 구입하는 최소비용,
        //                  c개짜리 묶음까지 고려했을 때 r-c개를 구입하는 최소비용
        //            )
        for (int c = 2; c <= n; c++) {
            for (int r = 1; r <= n; r++) {
                if (r - c <= 0) {
                    dp[r][c] = min(dp[r][c], dp[r][c - 1]);
                } else {
                    dp[r][c] = min(dp[r][c], dp[r][c - 1], dp[r - c][c] + p[c]);
                }
            }
        }

        System.out.println(dp[n][n]);
    }

    static long min(long a, long b) {
        return a < b ? a : b;
    }

    static long min(long a, long b, long c) {
        return min(a, min(b, c));
    }
}
