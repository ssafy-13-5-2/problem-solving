package d0310.cp.BOJ11568_민균이의계략;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            if (list.isEmpty() || last() < t) { // DP 배열의 마지막 원소보다 더 큰 숫자라면 마지막에 추가
                list.add(t);
            } else { // 그렇지 않다면 lb를 찾아서 교체
                int lb = lb(t);
                list.set(lb, t);
            }
        }

        System.out.println(list.size());
    }

    static int lb(int t) { // find idx of lower bound for key = t
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (left == right) {
                if (list.get(mid) >= t) {
                    return mid;
                } else {
                    return mid + 1;
                }
            }
            if (list.get(mid) < t) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }

    static int last() {
        return list.get(list.size() - 1);
    }
}
