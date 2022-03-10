package d0310.cp.BOJ10164_격자상의경로;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int mark = sc.nextInt();

        long cnt;
        if (mark == 0) {
            cnt = foo(0, 0, rows - 1, cols - 1);
        } else {
            int mr = mark / cols;
            int mc = mark % cols - 1;
            if (mark % cols == 0) {
                mr--;
                mc = cols - 1;
            }

            cnt = foo(0, 0, mr, mc) * foo(mr, mc, rows - 1, cols - 1);
        }

        System.out.println(cnt);
    }

    static long foo(int r1, int c1, int r2, int c2) {
        // (r1, c1)에서 (r2, c2)로 가는 경로의 개수
        // 고등학교 때의 경로 개수 구하는, DP 방식
        int dr = r2 - r1 + 1;
        int dc = c2 - c1 + 1;
        int[][] a = new int[dr + 1][dc + 1];
        a[1][1] = 1;
        for (int i = 1; i <= dr; i++) {
            for (int j = 1; j <= dc; j++) {
                if (i == 1 && j == 1) continue;
                a[i][j] = a[i][j - 1] + a[i - 1][j];
            }
        }
        return a[dr][dc];
    }
}
