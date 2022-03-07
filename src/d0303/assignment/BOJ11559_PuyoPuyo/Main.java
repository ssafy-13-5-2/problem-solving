package d0303.assignment.BOJ11559_PuyoPuyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    static int rows = 12;
    static int cols = 6;
    static char[][] board = new char[rows][cols];

    static boolean[][] v = new boolean[rows][cols];
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < rows; i++) {
            String l = br.readLine();
            for (int j = 0; j < cols; j++) {
                board[i][j] = l.charAt(j);
            }
        }

        int seq = 0;
        while (true) {
            boolean p = pop();
            if (!p) break;
            seq++;
            fall();
        }
        System.out.println(seq);
    }

    static boolean pop() {
        boolean ret = false;
        for (int k = 0; k < rows; k++) for (int l = 0; l < cols; l++) v[k][l] = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '.' || v[i][j]) continue;

                boolean[][] iv = new boolean[rows][cols];
                cnt = 0;

                // check
                for (int k = 0; k < rows; k++) for (int l = 0; l < cols; l++) iv[k][l] = false;
                dfs(i, j, board[i][j], 0, iv);
                if (cnt < 4) {
                    continue;
                }

                // remove
                for (int k = 0; k < rows; k++) for (int l = 0; l < cols; l++) iv[k][l] = false;
                dfs(i, j, board[i][j], 1, iv);
                ret = true;
            }
        }
        return ret;
    }

    static void dfs(int r, int c, char ch, int opt, boolean[][] iv) { // opt = 0 for count, opt = 1 for remove
        if (board[r][c] == '.' || board[r][c] != ch || iv[r][c]) {
            return;
        }

        if (opt == 1) {
            board[r][c] = '.';
        }
        v[r][c] = true;
        iv[r][c] = true;
        cnt++;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
            dfs(nr, nc, ch, opt, iv);
        }
    }

    static void fall() {
        for (int c = 0; c < 6; c++) {
            int cnt = 0;
            int[] temp = new int[12];
            for (int r = 11; r >= 0; r--) {
                if (board[r][c] == '.') {
                    temp[r] = -1;
                    cnt++;
                } else {
                    temp[r] = cnt;
                }
            }

            for (int r = 11; r >= 0; r--) {
                if (temp[r] == -1) continue;
                board[r + temp[r]][c] = board[r][c];
                if (temp[r] != 0) board[r][c] = '.';
            }
        }
    }
}
