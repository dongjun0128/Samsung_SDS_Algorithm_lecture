package P2143_두_배열의_합;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static long T;
    static int N;
    static int M;
    static long[] A;
    static long[] B;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P2143_두_배열의_합/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        B = new long[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Long> buA = new ArrayList<>();
        ArrayList<Long> buB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            long sum = 0;
            for (int j = i; j < N; j++) {
                sum += A[j];
                buA.add(sum);
            }
        }


        for (int i = 0; i < M; i++) {
            long sum = 0;
            for (int j = i; j < M; j++) {
                sum += B[j];
                buB.add(sum);
            }
        }

        Collections.sort(buA);
        Collections.sort(buB, Collections.reverseOrder());

        int pointA = 0;
        int pointB = 0;
        long cnt = 0;

        while (pointA < buA.size() && pointB < buB.size()) {
            long sum = buA.get(pointA) + buB.get(pointB);

            if (sum < T) {
                pointA++;
            } else if (sum > T) {
                pointB++;
            } else {
                long currentA = buA.get(pointA);
                long currentB = buB.get(pointB);
                long cntA = 0;
                long cntB = 0;

                while(pointA < buA.size() && currentA == buA.get(pointA)){
                    pointA++;
                    cntA++;
                }

                while(pointB < buB.size() && currentB == buB.get(pointB)){
                    pointB++;
                    cntB++;
                }

                cnt+= cntA * cntB;
            }
        }

        System.out.println(cnt);
    }
}
