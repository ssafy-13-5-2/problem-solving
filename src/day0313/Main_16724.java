package day0313; //과제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16724 { //피리 부는 사나이
	
	static int N, M;
	static char[][] map;
	static int[][] cnt;
	static int ans = 1;
	
	public static void main(String[] args) throws IOException { //피리 부는 사나이
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}//for i
		
		cnt = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(cnt[i][j] == 0) {
					DFS(i, j);		
				}
			}//for j
		}//for i
		
		System.out.println(ans-1);
	}//main
	
	static boolean DFS(int x, int y) {
		if(cnt[x][y] == ans) {
			ans++;
			return true;
		}
		else if(cnt[x][y] > 0 && cnt[x][y] < ans) {
			return false;
		}
		
		cnt[x][y] = ans;
		
		if(map[x][y] == 'U') {
			if(DFS(x-1, y)) {
				return true;
			}
			else {
				cnt[x][y] = ans-1;
				return false;
			}
		}//if
		
		else if(map[x][y] == 'D') {
			if(DFS(x+1, y)) {
				return true;
			}
			else {
				cnt[x][y] = ans-1;
				return false;
			}
		}//else if
		
		else if(map[x][y] == 'L') {
			if(DFS(x, y-1)) {
				return true;
			}
			else {
				cnt[x][y] = ans-1;
				return false;
			}
		}//else if
		
		else {
			if(DFS(x, y+1)) {
				return true;
			}
			else {
				cnt[x][y] = ans-1;
				return false;
			}
		}//else if
		
	}//DFS
	
	
}//END


