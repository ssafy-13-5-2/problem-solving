package d0303.BOJ17179_케이크자르기;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, l;
    static int[] ps;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        l = parseInt(st.nextToken());
        ps = new int[m];
        for (int i = 0; i < m; i++) {
            ps[i] = parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            int t = parseInt(br.readLine());
            sb.append(calc(t)).append('\n');
        }
        System.out.print(sb);
    }

    static int calc(int t) {

        // 자를 수 있으면 0, 자를 수 없으면 1
        // 000111...의 upper bound - 1을 찾으면 됨

        int left = 0;
        int right = l;
        int ub = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;

            if (left == right) {
                if (chk(mid, t) == 0) {
                    ub = mid + 1;
                } else {
                    ub = mid;
                }
                break;
            }

            if (chk(mid, t) == 1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return ub - 1;
    }

    static int chk(int x, int t) {
        // x: 가장 작은 조각의 길이, t: 자르는 횟수
        // 결정문제: 가장 작은 조각의 길이를 x라고 할 때, t회 이상 자를 수 있는가?
        int cnt = 0;
        int last = 0;
        for (int i = 0; i < m; i++) {
            int p = ps[i];
            if (p - last < x) continue;
            cnt++;
            last = p;
        }
        if (cnt == t && l - last < x) {
            // 딱 t회 잘랐는데 마지막 조각이 x보다 작다면
            // 이는 사실 1회는 자르지 않은 것이므로
            // 결국 t회 미만으로 자른 셈이 된다.
            // 이 때에는 자를 수 없다고 판단한다.
            // cnt--이 더 직관적일 수도?
            cnt = 0;
        }

        if (cnt >=  t) {
            return 0;
        } else {
            return 1;
        }
    }
}
