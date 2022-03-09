package day0307;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14712 { //넴모넴모(Easy)
	
	static int N, M;
	static boolean[][] visited;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		
		DFS(0, 0);
				
		System.out.println(ans);
	}//main
	
	static void DFS(int row, int col) {
		if(row-1>=0 && col-1>=0 && visited[row-1][col-1] && visited[row-1][col] && visited[row][col-1])
			return;
		
		ans++;
		
		for(int j=col; j<M; j++) {  //같은 행
			if(!visited[row][j]) {
				visited[row][j] = true;
				DFS(row, j);
				visited[row][j] = false;
			}
		}//for j
		
		for(int i=row+1; i<N; i++) { //아래 행
			for(int j=0; j<M; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					DFS(i, j);
					visited[i][j] = false;
				}
			}//for j
		}//for i
	}//DFS
	
}//END
