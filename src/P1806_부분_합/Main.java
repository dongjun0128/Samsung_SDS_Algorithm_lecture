package P1806_부분_합;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1806_부분_합/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int L = 0, R = 0;
        int length = N;

        while (true) {
            if (R >= N) {
                break;
            }

            int sum = 0;

            for (int i = L; i <= R; i++) {
                sum += A[i];
            }

            if (sum >= M) {
                length = Math.min(length, R - L + 1);
                L++;
            } else {
                R++;
            }
        }

        if(length == N) System.out.println(0);
        else System.out.println(length);
    }
}
