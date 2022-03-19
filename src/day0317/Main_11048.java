package day0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11048 {  //이동하기

	static int N, M;
	static int[][] map;
	static int[][] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}//for i
		
		result = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int max = 0;
				
				if(i-1>=0) 
					max = Math.max(max, result[i-1][j]);
				
				if(j-1>=0)
					max = Math.max(max, result[i][j-1]);
				
				if(i-1>=0 && j-1>=0)
					max = Math.max(max, result[i-1][j-1]);
				
				result[i][j] = max + map[i][j];
			}//for j
		}//for i
		
		System.out.println(result[N-1][M-1]);
	}//main
	

}//END

