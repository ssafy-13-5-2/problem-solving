package day0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743_음식물피하기 {
	
	static int N, M;
	
	static int[][] map;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 세로 길이
		M = Integer.parseInt(st.nextToken());	// 가로 길이
		int K = Integer.parseInt(st.nextToken());	// 음식물 쓰레기 개수
		
		map = new int[N+1][M+1];
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());	
			int c = Integer.parseInt(st.nextToken());	
			
			map[r][c] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j]==1) {
					max = Math.max(max, bfs(i, j));
				}
			}
		}
		
		System.out.println(max);
	}

	public static int bfs(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {r, c});
		
		int count = 0;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int curr = cur[0];
			int curc = cur[1];
			
			
			for (int d = 0; d < 4; d++) {
				int nr = curr + dr[d];
				int nc = curc + dc[d];
				
				if(nr>=1 && nr<=N && nc>=1 && nc<=M) {
					if(map[nr][nc]==1) {
						map[nr][nc] = 0;
						count++;
						que.offer(new int[] {nr, nc});
					}
				}
				
			}
		}
		return count++;
	}
}
