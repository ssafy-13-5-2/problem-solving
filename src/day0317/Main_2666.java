package day0317;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2666 { //벽장문의 이동
	
	static int N;
	static int[] open;
	static int M;
	static int[] use;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		open = new int[2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		open[0] = Integer.parseInt(st.nextToken());
		open[1] = Integer.parseInt(st.nextToken());
	
		M = Integer.parseInt(br.readLine());
		use = new int[M];
		for(int i=0; i<M; i++)
			use[i] = Integer.parseInt(br.readLine());
		
		DFS(0, 0, open[0], open[1]);
		
		System.out.println(ans);
	}//main
	
	static void DFS(int depth, int move, int open1, int open2) {		
		if(move > ans)
			return;
		
		if(depth == M) {
			ans = Math.min(ans, move);
			return;
		}//if
		
		DFS(depth+1, move + Math.abs(use[depth]-open1), use[depth], open2);
		
		DFS(depth+1, move + Math.abs(use[depth]-open2), open1, use[depth]);
		
	}//DFS

}//END

