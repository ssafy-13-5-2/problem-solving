package day0307;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1446 { //지름길  -> dp / 다익스트라
	
	static int N, D;
	static List<List<Integer>> graph = new ArrayList<>();
	static List<Integer> list = new ArrayList<Integer>(); //지름길이 시작하는 지점
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
	
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			List<Integer> tmp = new ArrayList<>();
			tmp.add(start);
			tmp.add(end);
			tmp.add(len);
			graph.add(tmp);
			
			if(end > D)
				graph.remove(graph.size()-1);
			
			if(!list.contains(start))
				list.add(start);
		}//for i
		
		DFS(0, 0);
		
		System.out.println(ans);
	}//main

	static void DFS(int start, int len) {
		ans = Math.min(ans, len + D - start);
		
		for(int i=0; i<graph.size(); i++) {
			if(graph.get(i).get(0) == start) {
				DFS(graph.get(i).get(1), len + graph.get(i).get(2));
			}//if
		}//for i
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i) > start)
				DFS(list.get(i), len + list.get(i) - start);
		}//for i
		
	}//DFS
}
