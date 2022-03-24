package day0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16987_계란으로계란치기 {
	
	static int N;
	static int result=Integer.MIN_VALUE;
	static Egg[] eggs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

	    N = Integer.parseInt(br.readLine());	// 계란의 수
	    eggs = new Egg[N];

	    for(int i=0; i<N; i++) {
	        st = new StringTokenizer(br.readLine());
	        eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, 0, false);
	    }

	    dfs(0,0);
	    
	    if(result == Integer.MIN_VALUE) {
	        System.out.println(0);
	    }else {
	        System.out.println(result);
	    }

	}
	
	private static void dfs(int idx, int cnt) {

	    if(idx == N) { 	//가장 오른쪽 계란일 경우
	        return;
	    }

	    Egg egg = eggs[idx]; 	//가장 왼쪽 계란 
	    
	    if(egg.isbreak) { 		// 계란이 깨져있을 경우
	        dfs(idx+1, cnt);	// 다음 계란으로 넘어감.
	        return;
	    }
	    
	    for(int i=0; i<N; i++) {
	        int temp = 0; // 계란 깨지면 값 증가 

	        if(idx == i) continue; // 같은 계란일 경우
	            
	        if(eggs[i].isbreak) continue; //깨진 계란일 경우
	            
	        egg.hard -= eggs[i].weight;
	        eggs[i].hard -=egg.weight;

	        if(egg.hard <=0) {
	            egg.isbreak = true;
	            temp++;
	        }
	        
	        if(eggs[i].hard <=0) {
	            eggs[i].isbreak = true;
	            temp++;
	        }

	        result = Math.max(result, cnt+temp);

	        dfs(idx+1, cnt+temp); 

	        //원상복구
	        if(egg.hard <=0) {
	            egg.isbreak = false;
	        }
	        if(eggs[i].hard <=0) {
	            eggs[i].isbreak = false;
	        }

	        egg.hard += eggs[i].weight;
	        eggs[i].hard += egg.weight; 

	    }

	}
}
	
class Egg{
	int hard, weight, current, cnt;
	boolean isbreak;
	public Egg(int hard, int weight, int current, int cnt, boolean isbreak) {
		this.hard = hard;		// 내구도
		this.weight = weight;	// 무게
		this.isbreak = isbreak;	// 깨졌는지 여부
		this.current = current;
		this.cnt = cnt;
	}
}


