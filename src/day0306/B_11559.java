package day0306;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class B_11559 {  //puyo puyo
	 
	static char[][] map = new char[12][6];
	static Queue<Integer> queueX = new LinkedList<Integer>();
	static Queue<Integer> queueY = new LinkedList<Integer>();
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<12; i++) 
			map[i] = br.readLine().toCharArray();
			
		while(BFS()) {  //연쇄가 발생하는 경우
			ans++;
			
			gravity();
		}//while
		
		System.out.println(ans);
	}//main
	
	static boolean BFS() {
		boolean chain = false;
		boolean[][] visited = new boolean[12][6];
		
		for(int i=0; i<12; i++) {
			for(int j=0; j<6; j++) {
				if(map[i][j] != '.' && !visited[i][j]) {
					char puyo = map[i][j];
					queueX.add(i);
					queueY.add(j);
					visited[i][j] = true;
					
					List<Integer> listX = new ArrayList<>();
					List<Integer> listY = new ArrayList<>();
					
					while(!queueX.isEmpty()) {
						int x = queueX.poll();
						int y = queueY.poll();
						
						listX.add(x);
						listY.add(y);
						
						for(int d=0; d<4; d++) {
							int X = x + dx[d];
							int Y = y + dy[d];
							
							if(X>=0 && X<12 && Y>=0 && Y<6 && map[X][Y]==puyo && !visited[X][Y]) {
								queueX.add(X);
								queueY.add(Y);
								visited[X][Y] = true;
							}//if
						}//for d
					}//while
					
					if(listX.size() >= 4) {  //연쇄 발생	
						chain = true;
						int size = listX.size();
						
						for(int k=0; k<size; k++)
							map[listX.get(k)][listY.get(k)] = '.';
					}//if
				}//if
			}//for j
		}//for i
		
		return chain;
	}//BFS
	
	static void gravity() {
		for(int i=10; i>=0; i--) {
			for(int j=0; j<6; j++) {
				int x = i;
				int y = j;
				
				while(x<11 && map[x][y] != '.' && map[x+1][y] == '.') {
					map[x+1][y] = map[x][y];
					map[x][y] = '.';
					x++;
				}//while
			}//for j
		}//for i

	}//gravity
}//END
