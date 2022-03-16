package day0310;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_2665 { //미로만들기
	
	static int n;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ans;
	
	static class Grid implements Comparable<Grid>{
		int x, y, change;  //현재 좌표까지 오는 동안 몇개의 검은 방을 바꿨는지
		
		public Grid(int x, int y, int change) {
			this.x = x;
			this.y = y;
			this.change = change;
		}

		@Override
		public int compareTo(Grid o) {  //검은 방을 적게 바꾼 순서대로 BFS 진행
			return this.change - o.change;
		}
	}//grid
	
	public static void main(String[] args) throws Exception { //미로만들기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map = new char[n][n];
		for(int i=0; i<n; i++)
			map[i] = br.readLine().toCharArray();  //0 검정, 1 하양
	
		visited = new boolean[n][n];
		
		BFS();
		
		System.out.println(ans);
	}//main
	
	static void BFS() {
		List<Grid> list = new ArrayList<Grid>(); 
		list.add(new Grid(0, 0, 0)); //시작점
		visited[0][0] = true;
		
		while(!list.isEmpty()) {
			Grid grid = list.remove(0);
			int x = grid.x;
			int y = grid.y;
			int change = grid.change;
			
			if(x == n-1 && y == n-1) { //끝방 도착
				ans = change;
				break;
			}//if			
			
			for(int i=0; i<4; i++) {
				int X = x + dx[i];
				int Y = y + dy[i];
				
				if(X>=0 && X<n && Y>=0 && Y<n && !visited[X][Y]) {
					if(map[X][Y] == '0')  //검은방
						list.add(new Grid(X, Y, change+1));
					
					else  //하얀방
						list.add(new Grid(X, Y, change));
					
					visited[X][Y] = true;
				}//if
			}//for i
			
			Collections.sort(list);
		}//while
		
	}//BFS

}//END
