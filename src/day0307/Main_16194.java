package day0307;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16194 { //카드 구매하기2
	
	static int N;
	static int[] P;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		P = new int[N+1];
		dp = new int[N+1];  //N개까지 구매할 때의 최소비용
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			P[i] = Integer.parseInt(st.nextToken());
		
		dp[1] = P[1];

		for(int i=2; i<=N; i++) {
			int min = Integer.MAX_VALUE;
			
			for(int j=1; j<i; j++) {
				min = Math.min(min, dp[j] + dp[i-j]);
			}//for j
			
			dp[i] = Math.min(min, P[i]);
		}//for i
	
		System.out.println(dp[N]);
	}//main
}
