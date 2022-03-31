package day0331;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_2023 { //신기한 소수

	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(7);
		
		int[] arr = {1, 3, 5, 7, 9}; //짝수는 소수 불가능
		
		for(int i=2; i<=N; i++) {
			int size = list.size();
					
			for(int j=0; j<size; j++) {
				int primeNumber = list.get(0);
				list.remove(0);
				
				for(int k=0; k<5; k++) {
					int n = primeNumber * 10 + arr[k];
					
					if(prime(n)) {
						list.add(n);
					}//if
				}//for k
			}//for j
		}//for i
		
		for(int i=0; i<list.size(); i++)
			System.out.println(list.get(i));

	}//main

	static boolean prime(int n) { //n이 소수인지 판단
		int cnt = 0;
		
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0)
				return false;
		}//for i
		
		return true;
	}//prime
}//END
