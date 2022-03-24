package d0310.cp.assignment.BOJ2616_소형기관차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] car = new int[50000];
    static int[] cars = new int[50000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            car[i] = cars[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < n; i++) {
            cars[i] += cars[i - 1];
        }
        for (int i = 0; i < n; i++) {
        }
        int l = Integer.parseInt(br.readLine());

        if (l == 1) {
            Arrays.sort(car, 0, n);
            System.out.println(car[n - 1] + car[n - 2] + car[n - 3]);
            return;
        }

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        int[] dp3 = new int[n];

        dp1[l - 1] = sum(0, l - 1);
        for (int i = l; i < n; i++) {
            dp1[i] = max(sum(i - l + 1, i), dp1[i - 1]);
        }

        dp2[2 * l - 1] = sum(0, 2 * l - 1);
        for (int i = 2 * l; i < n; i++) {
            dp2[i] = max(dp1[i - l] + sum(i - l + 1, i), dp2[i - 1]);
        }

        for (int i = 3 * l - 1; i < n; i++) {
            dp3[i] = max(dp2[i - l] + sum(i - l + 1, i), dp3[i - 1]);
        }

        System.out.println(dp3[n - 1]);
    }

    static int sum(int l, int r) {
        if (l == 0) return cars[r];
        return cars[r] - cars[l - 1];
    }

    static int max(int a, int b) {
        return a > b ? a : b;
    }
}
