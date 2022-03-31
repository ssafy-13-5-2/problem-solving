package day0331;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9019 {  //DSLR

	static int A, B;
	static StringBuilder sb = new StringBuilder();
	
	static class Info{
		int x;
		String str;
		
		Info(int x, String str){
			this.x = x;
			this.str = str;
		}
	}//Info
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 0; test_case < T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			BFS();
			
		}//for test_case
		
		System.out.println(sb.toString());

	}//main
	
	static void BFS() {
		Queue<Info> q = new LinkedList<>();
		boolean[] visited = new boolean[10001];
		q.add(new Info(A, ""));
		visited[A] = true;
		
		while(true) {
			Info info = q.poll();
			int x = info.x;
			String str = info.str;
			
			if(x == B) {
				sb.append(str).append("\n");
				break;
			}//if
			
			//D
			if(2*x <= 9999) {
				if(!visited[2*x]) {
					q.add(new Info((2*x), str.concat("D")));
					visited[2*x] = true;
				}
			}
			else {
				if(!visited[(2*x) % 10000]) {
					q.add(new Info((2*x) % 10000, str.concat("D")));
					visited[(2*x) % 10000] = true;
				}
			}
			
			//S
			if(x != 0) {
				if(!visited[x-1]) {
					q.add(new Info((x-1), str.concat("S")));
					visited[x-1] = true;
				}
			}
			else {
				if(!visited[9999]) {
					q.add(new Info(9999, str.concat("S")));
					visited[9999] = true;
				}
			}
			
			int a = x / 1000;
			int b = (x / 100) % 10;
			int c = (x / 10) % 10;
			int d = x % 10;
			
			//L
			if(str.length() == 0 || (str.length() > 0 && str.charAt(str.length()-1) != 'R')) {
				if(!visited[1000*b + 100*c + 10*d + a]) {
					q.add(new Info(1000*b + 100*c + 10*d + a, str.concat("L")));
					visited[1000*b + 100*c + 10*d + a] = true;
				}
			}
			
			//R
			if(str.length() == 0 || (str.length() > 0 && str.charAt(str.length()-1) != 'L')) {
				if(!visited[1000*d + 100*a + 10*b + c]) {
					q.add(new Info(1000*d + 100*a + 10*b + c, str.concat("R")));
					visited[1000*d + 100*a + 10*b + c] = true;
				}
			}
			
		}//while
		
	}//BFS

}//END
