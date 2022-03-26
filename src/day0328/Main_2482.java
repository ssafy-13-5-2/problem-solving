package day0328;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2482 { // 색상환

	static int N, K;
	static int mod = 1000000003;
	static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		if(K > N/2) {
			System.out.println(0);
			return;
		}
		
		dp = new int[N+1][K+1]; //일렬일 때 n개중 k개를 이웃하지 않게 뽑는 경우
		
		for(int n=1; n<=N; n++) {
			dp[n][1] = n;
			dp[n][0] = 1;
		}
		
		for(int k=2; k<=K; k++) {  //k개를 뽑을 때
			dp[2*k-1][k] = 1;
			
			for(int n=2*k; n<=N; n++) {  //n번째를 선택하지 않을 때와 n번째를 선택할 때
				dp[n][k] = (dp[n-1][k] + dp[n-2][k-1]) % mod;
			}//for n
		}//for k

		System.out.println((dp[N-1][K] + dp[N-3][K-1]) % mod);
	}// main

}// END
