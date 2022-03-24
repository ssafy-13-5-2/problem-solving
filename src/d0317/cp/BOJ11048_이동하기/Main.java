package d0317.cp.BOJ11048_이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
    int r, c;

    Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int[] dr = {0, 1, 1};
    static int[] dc = {1, 0, 1};

    static int rows, cols;
    static int[][] board = new int[1000][1000];
    static int[][] m = new int[1000][1000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 1000; i++) for (int j = 0; j < 1000; j++) m[i][j] = -1;
        for (int i = 0; i < rows; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < cols; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0, 0));
        m[0][0] = board[0][0];
        while (!q.isEmpty()) {
            Pos curr = q.poll();
            for (int d = 0; d < 2; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];
                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
                if (m[nr][nc] >= m[curr.r][curr.c] + board[nr][nc]) continue;
                q.offer(new Pos(nr, nc));
                m[nr][nc] = m[curr.r][curr.c] + board[nr][nc];
            }
        }

        System.out.println(m[rows - 1][cols - 1]);
    }
}
