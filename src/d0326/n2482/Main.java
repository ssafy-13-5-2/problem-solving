package d0326.n2482;

import java.io.*;

public class Main {
	
	static int N, K, dp[][];
	static final int DIV = 1_000_000_003; // 가독성 위해 _입력

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		K=Integer.parseInt(br.readLine());
		
		int answer=0;
		
		if(K<=N/2) {
			dp = new int[N+1][K+1];
			for(int i=1;i<=N;i++) {
				dp[i][0]=1; // i개 중에 0개 고르는 경우의 수 iC0=1
				dp[i][1]=i; // i개 중에 1개 고르는 경우의 수 iC2=i >> 둘 다 이웃하는 칸 신경쓸 필요 x
			}
			
			for(int n=3;n<=N;n++) { //dp[1][0], dp[1][1]이 최소니까
				for(int k=2;k<=K;k++) {
					dp[n][k]=(dp[n-2][k-1]+dp[n-1][k])%DIV;
				}
			}
			
			// N번째 선택시 (1번째 선택X, N-1번째 선택X -> N-3개 중에 K-1개 선택) + N번째 선택X시 (N-1개 중에 K개 선택)
			answer = (dp[N-3][K-1]+dp[N-1][K])%DIV;
		}
		
		System.out.println(answer);
	}
}
