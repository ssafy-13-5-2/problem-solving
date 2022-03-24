package day0324;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1411 { //비슷한 단어

	static int N;
	static String[] word;
	static int len;
	
	public static void main(String[] args) throws NumberFormatException, IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		word = new String[N];
		for(int i=0; i<N; i++)
			word[i] = br.readLine();
		
		len = word[0].length(); //한 단어의 길이
		
		int ans = 0;
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				String a = word[i];  //단어 a, b 비교
				String b = word[j];
				
				int[] arr_a = new int[len];
				int[] arr_b = new int[len];
				boolean similar = true;
				
				for(int k=1; k<len; k++) {
					boolean same_a = false; 
					boolean same_b = false;
					
					for(int q=0; q<k; q++) {
						if(a.charAt(k) == a.charAt(q)) {
							arr_a[k] = arr_a[q];
							same_a = true;
							break;
						}
					}//for q
					
					for(int q=0; q<k; q++) {
						if(b.charAt(k) == b.charAt(q)) {
							arr_b[k] = arr_b[q];
							same_b = true;
							break;
						}
					}//for q
					
					if(!same_a)
						arr_a[k] = k;
					
					if(!same_b)
						arr_b[k] = k;
					
					if(arr_a[k] != arr_b[k]) {
						similar = false;
						break;
					}//if
				}//for k	
				
				if(similar)
					ans++;
			}//for j
		}//for i
		
		System.out.println(ans);
	}//main

}//END
