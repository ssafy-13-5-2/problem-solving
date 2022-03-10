package d0307.n2665;

import java.io.*;
import java.util.*;

/*
BOJ2665
첫 줄에는 한 줄에 들어가는 방의 수 n(1 ≤ n ≤ 50)이 주어지고,
다음 n개의 줄의 각 줄마다 0과 1이 이루어진 길이가 n인 수열이 주어진다. 0은 검은 방, 1은 흰 방을 나타낸다.

첫 줄에 흰 방으로 바꾸어야 할 최소의 검은 방의 수를 출력한다.
 */

public class Main{
	
	static int N;
	static int map[][];
	static int d[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static int dist[][];
	
	static class Node{
		int r,c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static boolean isRange(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	
	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0)); // start point
		dist[0][0] = 0;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int i=0;i<4;i++) {
				int nr = now.r + d[i][0];
				int nc = now.c + d[i][1];
				
				if (!isRange(nr,nc)) continue;
				if (dist[now.r][now.c]>=dist[nr][nc]) continue;
				
				if(map[nr][nc]==1) dist[nr][nc] = dist[now.r][now.c]; //origin road
				else dist[nr][nc] = dist[now.r][now.c]+1; //del wall
				q.add(new Node(nr,nc));
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dist = new int[N][N];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = str.charAt(j)-'0';
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		bfs();
		System.out.println(dist[N-1][N-1]);
	}
}