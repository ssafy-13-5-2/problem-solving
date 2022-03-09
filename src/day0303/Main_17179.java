package day0303;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17179 {  //케이크자르기

	static int N, M, L;
	static int[] cut;  //자를수 있는 지점
	static int[] num;  //자르는 횟수를 모두 저장
	
	public static void main(String[] args) throws IOException { //케이크 자르기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());  //자르는 횟수
		M = Integer.parseInt(st.nextToken());  //지점의 개수
		L = Integer.parseInt(st.nextToken());  //케이크 길이

		cut = new int[M+1];
		num = new int[N];
		
		for(int i=0; i<M; i++)
			cut[i] = Integer.parseInt(br.readLine());
		cut[M] = L;
		
		for(int i=0; i<N; i++){
			int cnt = Integer.parseInt(br.readLine());
			int ans = 0;
			
			int low = 0;
			int high = L;
			
			while(low <= high) {
				int mid = (low+high)/2;
				
				if(cutCake(mid, cnt)) {
					low = mid + 1;
					ans = Math.max(ans, mid);
				}//if
				else
					high = mid - 1;
			}//while
			
			System.out.println(ans);
		}//for i
		
	}//main

	static boolean cutCake(int mid, int cnt) {
		int start = 0;
		
		for(int i=0; i<M+1; i++) {
			if(cut[i] - start >= mid) {
				cnt--;
				start = cut[i];
			}//if
		}//for i
		
		if(cnt < 0)
			return true;
		
		return false;
	}//cutCake
	
}
