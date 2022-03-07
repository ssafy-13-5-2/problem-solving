package d0307.BOJ14712_넴모넴모;

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

        foo(0, 0); // DFS로 완탐

        System.out.println(cnt);
    }

    static void foo(int r, int c) {
        if (c == cols) { // 열 범위를 넘었으면 행을 증가
            c = 0;
            r++;
        }
        if (r == rows) { // 행 범위를 넘었으면 base case
            cnt++;
            return;
        }

        // 이번 칸 채우지 않을 때
        board[r][c] = 0;
        foo(r, c + 1);

        // 이번 칸 채울 때
        int tmp = 0; // 채우려면 위, 왼쪽 위, 왼쪽 3칸이 모두 차있지는 않아야 함
        for (int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
            if (board[nr][nc] == 1) tmp++;
        }
        if (tmp < 3) { // 3칸이 차있는 것이 아니라면
            board[r][c] = 1; // 이번 칸 채우고
            foo(r, c + 1); // 다음 칸 호출
        }
    }
}
