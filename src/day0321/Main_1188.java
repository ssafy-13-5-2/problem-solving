package day0321; //과제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1188 {  //음식평론기
	
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());  //소시지 수
		M = Integer.parseInt(st.nextToken());  //평론가 수
		
		//소세지 하나를 M등분
		//N조각이 모이면 1인분 완성
		DFS(0, 0, M, 0);
		
	}//main
	
	static void DFS(int depth, int cnt, int remain, int cut) { 
		if(depth == M) {  //M인분 완성
			System.out.println(cut);
			System.exit(0);
		}//if
		
		if(remain > (N-cnt)) //현재 소시지의 남은 길이가 필요한 개수보다 클 때
			DFS(depth+1, 0, remain-(N-cnt), cut+1);
		
		else if(remain == N-cnt)  //현재 소시지의 남은 길이가 필요한 개수와 같을 때
			DFS(depth+1, 0, M, cut);
		
		else //현재 소시지의 남은 길이가 부족할 때
			DFS(depth, cnt + remain, M, cut);	
	}//DFS

}//END
