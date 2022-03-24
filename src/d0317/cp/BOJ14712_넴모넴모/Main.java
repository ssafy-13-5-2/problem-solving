package d0317.cp.BOJ14712_넴모넴모;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static int rows, cols;
    static int cnt = 0;
    static int[][] board = new int[25][25];

    static int[] dr = {-1, -1, 0};
    static int[] dc = {0, -1, -1};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        rows = sc.nextInt();
        cols = sc.nextInt();

        foo(0, 0);

        System.out.println(cnt);
    }

    static void foo(int r, int c) {
        if (c == cols) {
            c = 0;
            r++;
        }
        if (r == rows) {
            cnt++;
            return;
        }

        board[r][c] = 0;
        foo(r, c + 1);

        int tmp = 0;
        for (int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
            if (board[nr][nc] == 1) tmp++;
        }
        if (tmp < 3) {
            board[r][c] = 1;
            foo(r, c + 1);
        }
    }
}
