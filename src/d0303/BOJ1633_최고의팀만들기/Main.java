package d0303.BOJ1633_최고의팀만들기;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] p = new int[1001][2]; // p[i][0]: i번째 사람의 흑팀 능력치, p[i][1]: i번째 사람의 백팀 능력치
    static int[][][] dp = new int[1001][16][16]; // dp[i][j][k] = i번째 사람까지만 고려하고, 흑팀 j명, 백팀 k명을 구성했을 때, 능력치의 합의 최댓값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            p[n + 1][0] = a;
            p[n + 1][1] = b;
            n++;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 15; j++) {
                for (int k = 0; k <= 15; k++) {
                    if (j == 0 && k == 0) {
                        continue;
                    }
                    if (j == 0) {
                        dp[i][j][k] = max(
                                dp[i - 1][j][k - 1] + p[i][1], // i번째 사람을 백팀으로 포함시킴
                                dp[i - 1][j][k] // i번째 사람을 배제
                        );
                        continue;
                    }
                    if (k == 0) {
                        dp[i][j][k] = max(
                                dp[i - 1][j - 1][k] + p[i][0], // i번째 사람을 흑팀으로 포함시킴
                                dp[i - 1][j][k] // i번째 사람을 배제
                        );
                        continue;
                    }

                    dp[i][j][k] = max(
                            dp[i - 1][j - 1][k] + p[i][0], // i번째 사람을 흑팀으로 포함시킴
                            dp[i - 1][j][k - 1] + p[i][1], // i번째 사람을 백팀으로 포함시킴
                            dp[i - 1][j][k] // i번째 사람을 배제
                    );
                }
            }
        }

        System.out.println(dp[n][15][15]);
    }

    static int max(int... a) {
        int max = 0;
        for (int i : a) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}
