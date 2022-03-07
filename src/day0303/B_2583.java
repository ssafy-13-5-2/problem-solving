package day0303;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class B_2583 {

	static int M, N, K;
	static boolean[][] map, visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ans;
	static List<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[M][N];
		visited = new boolean[M][N];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
		
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
				
			for(int x=x1; x<x2; x++) {
				for(int y=y1; y<y2; y++)
					map[x][y] = true;  //map을 색칠
			}//for x
		}//for i
		
		BFS();
		
		Collections.sort(list);
		
		sb.append(ans).append("\n");
		for(int i=0; i<ans; i++)
			sb.append(list.get(i) + " ");
		
		System.out.println(sb);
	}//main
	
	static void BFS() {  //색칠이 안된 구역의 개수와 넓이 구하기
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && !map[i][j]) {
					int cnt = 1;
					ans++;
					
					Queue<Integer> queueX = new LinkedList<>();
					Queue<Integer> queueY = new LinkedList<>();
					queueX.add(i);
					queueY.add(j);
					visited[i][j] = true;
					
					while(!queueX.isEmpty()) {
						int x = queueX.poll();
						int y = queueY.poll();
						
						for(int d=0; d<4; d++) {
							int X = x + dx[d];
							int Y = y + dy[d];
							
							if(X>=0 && X<M && Y>=0 && Y<N && !map[X][Y] && !visited[X][Y]) {
								queueX.add(X);
								queueY.add(Y);
								visited[X][Y] = true;
								cnt++;
							}//if
						}//for d
					}//while
					list.add(cnt);
				}//if
			}//for j
		}//for i
	}//BFS

}