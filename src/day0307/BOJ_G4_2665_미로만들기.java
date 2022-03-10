package day0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_G4_2665_미로만들기 {
	
	static int N;
	static char[][] map;
	static int[][] count;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static class Room {
		int x;
		int y;
		
		public Room(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	// 방의 수
		map = new char[N][N];
		count = new int[N][N];	// 현재 위치에 가장 최소 횟수를 저장.
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				count[i][j] = Integer.MAX_VALUE;
			}
		}

		bfs();
		System.out.println(count[N-1][N-1]);
	}

	// 검은색 방을 흰색방으로 바꾸는 최소 횟수를 누적하여 계산하며 탐색 ==> 다익스트라
	public static void bfs() {
		
		Queue<Room> queue = new LinkedList<>();
		queue.offer(new Room(0, 0));
		count[0][0] = 0;
		
		while(!queue.isEmpty()) {
			Room cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if(nx>=0 && nx<N && ny>=0 && ny<N && count[nx][ny] > count[cur.x][cur.y]) {
				// 현재 거리보다 더 적은 횟수로 이동할 수 있는 방법이 있다면 그 방향으로 이동.
					
					if(map[nx][ny]=='0') {	// 검은색 방이면 횟수를 누적해서 더함.
						count[nx][ny] = count[cur.x][cur.y] + 1;
					} else {	// 흰색 방이면 횟수를 더하지 않음.
						count[nx][ny] = count[cur.x][cur.y];
					}
					queue.offer(new Room(nx, ny));
				}
			}
			
		}
	}
	
}
