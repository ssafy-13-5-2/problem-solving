package day0324;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1743 { //음식물 피하기
	
	static int N, M, K;
	static boolean[][] map, visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ans = 0;
	
	static class Grid{
		int x, y;
		
		public Grid(int x, int y) {
			this.x = x;
			this.y = y;
		}//Grid
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[N+1][M+1];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[x][y] = true;
		}//for i
		
		visited = new boolean[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] && !visited[i][j])
					BFS(i, j);
			}//for j
		}//for i
		
		System.out.println(ans);
	}//main
	
	static void BFS(int i, int j) {
		Queue<Grid> q = new LinkedList<Grid>();
		q.add(new Grid(i, j));
		visited[i][j] = true;
		
		int cnt = 0;
		
		while(!q.isEmpty()){
			Grid grid = q.poll();
			int x = grid.x;
			int y = grid.y;
			cnt++;
			
			for(int d=0; d<4; d++) {
				int X = x + dx[d];
				int Y = y + dy[d];
				
				if(X>=1 && X<=N && Y>=1 && Y<=M && map[X][Y] && !visited[X][Y]) {
					q.add(new Grid(X, Y));
					visited[X][Y] = true;
				}//if
			}//for d
			
		}//while
			
		ans = Math.max(ans, cnt);
	}//BFS

}//END

