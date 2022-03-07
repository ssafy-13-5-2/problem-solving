package d0303.BOJ2583_영역구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
    int r;
    int c;

    Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    static int rows, cols;
    static int k;
    static int[][] board = new int[100][100];
    static boolean[][] visit = new boolean[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    board[x][y] = 1;
                }
            }
        }

        List<Integer> sizes = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visit[i][j] || board[i][j] == 1) continue;
                int size = bfs(i, j);
                sizes.add(size);
                cnt++;
            }
        }

        System.out.println(cnt);
        Collections.sort(sizes);
        StringBuilder sb = new StringBuilder();
        for (int i : sizes) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
    }

    static int bfs(int i, int j) {
        int size = 0;
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        visit[i][j] = true;
        while (!q.isEmpty()) {
            Pos c = q.poll();
            size++;
            for (int d = 0; d < 4; d++) {
                int nr = c.r + dr[d];
                int nc = c.c + dc[d];
                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
                if (visit[nr][nc]) continue;
                if (board[nr][nc] == 1) continue;
                q.offer(new Pos(nr, nc));
                visit[nr][nc] = true;
            }
        }
        return size;
    }
}
