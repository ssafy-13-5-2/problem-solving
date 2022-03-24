package d0317.cp.BOJ2666_벽장문의이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long init = 0;
    static int[] order = new int[20];
    static int no;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        foo(0, 0, init);

        System.out.println(min);
    }

    static void foo(int idx, int cnt, long stat) {
        if (idx == no) {
            if (cnt < min) {
                min = cnt;
            }
            return;
        }

        int targetDoor = order[idx];

        // opened
        if ((stat & (1L << targetDoor)) != 0) {
            foo(idx + 1, cnt, stat);
        }
        // closed
        else {
            // left
            int leftDoor = getNearestLeftDoor(targetDoor, stat);
            if (leftDoor != -1) {
                long nstat = stat;
                for (int j = leftDoor; j < targetDoor; j++) {
                    nstat &= ~(1L << j);
                }
                nstat |= (1L << targetDoor);
                foo(idx + 1, cnt + (targetDoor - leftDoor), nstat);
            }

            // right
            int rightDoor = getNearestRightDoor(targetDoor, stat);
            if (rightDoor != -1) {
                long nstat = stat;
                for (int j = targetDoor + 1; j <= rightDoor; j++) {
                    nstat &= ~(1L << j);
                }
                nstat |= (1L << targetDoor);
                foo(idx + 1, cnt + (rightDoor - targetDoor), nstat);
            }
        }
    }

    static int getNearestLeftDoor(int targetDoor, long stat) {
        for (int j = targetDoor - 1; j >= 0; j--) {
            if ((stat & (1L << j)) != 0) {
                return j;
            }
        }
        return -1;
    }

    static int getNearestRightDoor(int targetDoor, long stat) {
        for (int j = targetDoor + 1; j < n; j++) {
            if ((stat & (1L << j)) != 0) {
                return j;
            }
        }
        return -1;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int o1 = Integer.parseInt(st.nextToken()) - 1;
        int o2 = Integer.parseInt(st.nextToken()) - 1;
        init |= (1L << o1);
        init |= (1L << o2);
        // 0: closed, 1: opened
        no = Integer.parseInt(br.readLine());
        for (int i = 0; i < no; i++) {
            order[i] = Integer.parseInt(br.readLine()) - 1;
        }
    }

    static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
