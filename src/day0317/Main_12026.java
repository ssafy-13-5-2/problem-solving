package day0317;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_12026 {  //BOJ 거리
	
	static int N;
	static char[] arr;
	static int[] dp;
	static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new char[N];
		arr = br.readLine().toCharArray();
		
		dp = new int[N];
		dp[0] = 0;
		
		List<Integer> B = new ArrayList<Integer>();
		List<Integer> O = new ArrayList<Integer>();
		List<Integer> J = new ArrayList<Integer>();
		
		B.add(0);
		
		for(int i=1; i<N; i++) {
			if(arr[i] == 'B') { 
				int size = J.size();
				
				if(size > 0) {
					int min = Integer.MAX_VALUE;
					
					for(int j=0; j<size; j++) {
						int tmp = J.get(j);
						
						if(min > dp[tmp] + (i-tmp)*(i-tmp)) {
							min = dp[tmp] + (i-tmp)*(i-tmp);
						}
					}//for j
					
					dp[i] = min;
					B.add(i);
				}//if
				
			}//if
			
			else if(arr[i] == 'O') {
				int size = B.size();
				
				int min = Integer.MAX_VALUE;
				
				for(int j=0; j<size; j++) {
					int tmp = B.get(j);
					
					if(min > dp[tmp] + (i-tmp)*(i-tmp)) {
						min = dp[tmp] + (i-tmp)*(i-tmp);
					}
				}//for j
				
				dp[i] = min;
				O.add(i);
			}//else if
			
			else {
				int size = O.size();
				
				if(size > 0) {
					int min = Integer.MAX_VALUE;
					
					for(int j=0; j<size; j++) {
						int tmp = O.get(j);
						
						if(min > dp[tmp] + (i-tmp)*(i-tmp)) {
							min = dp[tmp] + (i-tmp)*(i-tmp);
						}
					}//for j
					
					dp[i] = min;
					J.add(i);
				}//if
			}//else
		}//for i
		
		if(N == 1)
			System.out.println(0);
		
		else if(dp[N-1] == 0)
			System.out.println("-1");
		else
			System.out.println(dp[N-1]);
	}//main

}
