package d0310.cp.assignment.BOJ16724_피리부는사나이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    final static int[] dr = {-1, 0, 1, 0};
    final static int[] dc = {0, 1, 0, -1};
    final static int U = 0;
    final static int R = 1;
    final static int D = 2;
    final static int L = 3;

    static int rows, cols;
    static char[][] board = new char[1000][1000];
    static boolean[][] visit = new boolean[1000][1000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        for (int i = 0; i < rows; i++) {
            String l = br.readLine();
            for (int j = 0; j < cols; j++) {
                board[i][j] = l.charAt(j);
            }
        }

        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visit[i][j]) continue;
                visit[i][j] = true;
                dfs(i, j);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void dfs(int r, int c) {
        int nr, nc;
        int d;

        // FW
        d = -1;
        switch (board[r][c]) {
            case 'U': d = U; break;
            case 'R': d = R; break;
            case 'D': d = D; break;
            case 'L': d = L; break;
        }

        nr = r + dr[d];
        nc = c + dc[d];
        if (!(nr < 0 || nc < 0 || nr >= rows || nc >= cols)
        && !visit[nr][nc]) {
            visit[nr][nc] = true;
            dfs(nr, nc);
        }

        // BW
        for (d = 0; d < 4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
            if (visit[nr][nc]) continue;
            if (d == U && board[nr][nc] == 'D'
             || d == R && board[nr][nc] == 'L'
             || d == D && board[nr][nc] == 'U'
             || d == L && board[nr][nc] == 'R') {
                visit[nr][nc] = true;
                dfs(nr, nc);
            }
        }
    }
}
