package d0317.n1188;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M; // N=소세지 수, M=평론가 수
	
	static int GCD(int a, int b) { // N,M>=1
		//a를 b로 나눈 나머지 = r, GCD(a, b) = GCD(b, r)과 같고, r이 0이면 그때 b가 최대공약수이다.
		if (b==0) return a;
		return GCD(b, a%b);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int answer=0;
		
//		if(M%N==0) { //평론가 수 >= 소시지 수 / 조각조각 안나누어도 됨 -> 2 6
//			answer = (M/N-1)*N;
//			// answer = M-N
//		}
//		else if(N%M==0) { // 소시지 수 >= 사람 수 -> 6 2
//			answer = 0; // M-M
//		}
//		else{ // M%N!=0
////			if(N>M) { // 소시지 수 > 사람 수 -> 10 3
////				N=N%M;
////			}
////			// 소시지 수 < 사람 수 -> 1 3, 3 10
////			answer = M/N;
////			answer*=N;
////			
////			int piece = M%N;
////			if((M-answer)%piece!=0)
////				answer = piece+(M-piece-1); // -> answer = M-1
//			answer = M-1;
//		} 
		
		// 2 6 -> 2, 6 2 -> 2, 3 4 -> 1인 것 -> 최대공약수
		System.out.println(M-GCD(N,M));
	}
}
