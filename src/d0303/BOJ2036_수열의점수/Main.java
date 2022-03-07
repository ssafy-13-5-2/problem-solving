package d0303.BOJ2036_수열의점수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> neg = new ArrayList<>(); // 음수 리스트
        List<Integer> pos = new ArrayList<>(); // 양수 리스트
        int nz = 0; // 0의 개수

        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            if (t == 0) nz++;
            else if (t > 0) pos.add(t);
            else if (t < 0) neg.add(t);
        }

        Collections.sort(neg);
        Collections.sort(pos);
        // 음수 리스트와 양수 리스트를 모두 오름차순으로 정렬

        long sum = 0;

        /*
        -10 -6 -3 -2 ... 을 예로 들면,
        곱셈의 성질에 의해 절댓값이 큰 수끼리 곱한 결과가 가장 크고 부호를 신경쓰지 않아도 되므로
        -10 * -6 + -3 * -2 + ...가 수열의 점수가 된다.

        여기서 음수의 개수가 홀수 개일 때를 고려해야 한다.
        마지막에 있는 음수는, 0이 하나라도 있으면 0이랑 곱해서 0으로 만들어주는 게 좋고
        0으로 만들기가 불가능하다면 어쩔 수 없이 그냥 더해줘야 한다. -> 53-55라인

        양수도 마찬가지로 진행한다. 다만 양수의 경우에는 별개로 고려할 것이 있다.
        대부분의 경우에 곱셈의 결과가 덧셈의 결과보다 큰 것이 자명하지만
        1, 2와 같은 경우를 고려하면 1 + 2 > 1 * 2이므로 덧셈과 곱셈의 결과 중 큰 것을 더해줘야 한다. -> 62라인
         */
        int l = 0;
        while (true) {
            if (l + 1 >= neg.size()) break;
            long l1 = neg.get(l);
            long l2 = neg.get(l + 1);
            sum += max(l1 * l2, l1 + l2);
            l += 2;
        }
        if (l < neg.size() && nz == 0) {
            sum += neg.get(neg.size() - 1);
        }

        int r = pos.size() - 1;
        while (true) {
            if (r - 1 < 0) break;
            long r1 = pos.get(r - 1);
            long r2 = pos.get(r);
            sum += max(r1 * r2, r1 + r2);
            r -= 2;
        }
        if (r >= 0) sum += pos.get(0);

        System.out.println(sum);
    }

    static long max(long... a) {
        long max = Long.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }
}
