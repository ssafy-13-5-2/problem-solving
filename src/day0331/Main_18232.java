package day0331;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_18232 { //텔레포트 정거장

	static int N, M, S, E;
	static List<Integer>[] list;
	static boolean[] visited;
	
	static class Pos{
		int x, dist;
		
		public Pos(int x, int dist) {
			this.x = x;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new List[N+1];
		for(int i=0; i<N+1; i++)
			list[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());	
			list[x].add(y);
			list[y].add(x);
		}//for i
		
		visited = new boolean[N+1];
		
		BFS();
		
	}//main

	static void BFS() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(S, 0));
		visited[S] = true;
		
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			int x = pos.x;
			int dist = pos.dist;
			
			if(x == E) {
				System.out.println(dist);
				return;
			}//if
			
			if(x-1>=0 && !visited[x-1]) {
				q.add(new Pos(x-1, dist+1));
				visited[x-1] = true;
			}
			
			if(x+1<=N && !visited[x+1]) {
				q.add(new Pos(x+1, dist+1));
				visited[x+1] = true;
			}
			
			for(int i=0; i<list[x].size(); i++) {
				int y = list[x].get(i);
				if(!visited[y]) {
					visited[y] = true;
					q.add(new Pos(y, dist+1));
				}//if
			}//for i
			
		}//while
	}//BFS
	
}//END



