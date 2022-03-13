package d0310.n16724;

import java.io.*;
import java.util.*;

/*
피리 부는 사나이
회원들이 어느 구역에 있더라도 ‘SAFE ZONE’에 들어갈 수 있게 하는 ‘SAFE ZONE’의 최소 개수를 출력
 */

public class Main_BOJ16724 {

	static int N, M, answer;
	static char[][] road;
	static int[][] v;
	
	static boolean dfs(int r, int c, int now) {
		v[r][c]=now;
		while(true) {
			char dir = road[r][c];
			if(dir == 'U') {
				if(v[r-1][c]==0) { // 아직 간 적 없는 길
					v[r-1][c] = now;
					r--;
				}
				else if(v[r-1][c]==v[r][c]) break; // 이미 내가 왔던 길
				else //다른 길로 이어지면
					return false;
			}
			else if(dir == 'D') {
				if(v[r+1][c]==0) {
					v[r+1][c]=now;
					r++;
				}
				else if(v[r+1][c]==v[r][c]) break;
				else return false;
			}
			else if(dir == 'L') {
				if(v[r][c-1]==0) {
					v[r][c-1]=now;
					c--;
				}
				else if(v[r][c-1]==v[r][c]) break;
				else return false;
			}
			else {// dir == 'R'
				if(v[r][c+1]==0) {
					v[r][c+1]=now;
					c++;
				}
				else if(v[r][c+1]==v[r][c]) break;
				else return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		road = new char[N][M];
		v = new int[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++)
				road[i][j]=str.charAt(j);
		}  //input
		
		int tmp=1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(v[i][j]==0)
					if(dfs(i,j,tmp++)) answer++;
			}
		}
		
		System.out.println(answer);
	}
}
