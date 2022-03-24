package day0324;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16987 { // 계란으로 계란치기
 
	static int N;
	static int[][] arr;
	static int ans = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			arr[i][0] = s; // 내구도
			arr[i][1] = w; // 무게
		} // for i

		DFS(0, 0);

		System.out.println(ans);
	}// main

	static void DFS(int idx, int cnt) {
		ans = Math.max(ans, cnt);
		
		if(idx == N) 
			return;

		if (arr[idx][0] <= 0) // 깨진 계란을 든 경우
			DFS(idx + 1, cnt);

		else { // 안깨진 계란을 든 경우
			for (int i = 0; i < N; i++) { 
				if (i != idx && arr[i][0] > 0) { // 충돌할 계란 선정
					int num = 0;
					int arr_idx = arr[idx][0];
					int arr_i = arr[i][0];

					arr[idx][0] -= arr[i][1];
					arr[i][0] -= arr[idx][1];

					if (arr[idx][0] <= 0)
						num++;
					if (arr[i][0] <= 0)
						num++;

					DFS(idx + 1, cnt + num);

					arr[idx][0] = arr_idx;
					arr[i][0] = arr_i;
				} // if
			} // for i
		}//else
	}// DFS

}// END
