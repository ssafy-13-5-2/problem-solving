package d0307.assignment.BOJ2665_미로만들기;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

class Pos {
    int r;
    int c;

    Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final static int INF = Integer.MAX_VALUE;
    final static int[] dr = {0, 0, 1, -1};
    final static int[] dc = {1, -1, 0, 0};

    static int n;
    static int[][] board = new int[50][50];
    static int[][] mem = new int[50][50];

    public static void main(String[] args) throws IOException {
        input();

        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(0, 0));
        mem[0][0] = 0;

        while (!queue.isEmpty()) {
            Pos curr = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                int cost = board[nr][nc];
                if (mem[curr.r][curr.c] + cost >= mem[nr][nc]) continue;
                // 비용 업데이트가 필요할 때에만 방문
                queue.offer(new Pos(nr, nc));
                mem[nr][nc] = mem[curr.r][curr.c] + cost;
            }
        }
        System.out.println(mem[n - 1][n - 1]);
    }

    static void input() throws IOException {
        n = parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String l = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = l.charAt(j) == '0' ? 1 : 0; // 헷갈려서 반대로 함. 흰색: 0, 검은색: 1
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mem[i][j] = INF;
            }
        }
    }
}
