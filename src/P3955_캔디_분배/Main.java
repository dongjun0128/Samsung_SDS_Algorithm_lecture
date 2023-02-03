package P3955_캔디_분배;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, A, B;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P3955_캔디_분배/Main.java"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // X : 인당 나눠줄 사탕의 수
        // Y : 사탕 봉지의 수
        // A * X + 1 = B * Y
        // Ax + By = C 형태로 변환
        // -Ax + By = 1
        // Ax - By = -1 의 형태로 변환 -> 추후 k를 구할 떄 x의 범위가 반전된다.

        EGResult result = egcd(A, B);
        // Ax + By = C 일때 C % gcd(A, B)  == 0 이어야 해를 가질 수 있음 : 베주 항등식

        if (result.r != 1) {
            System.out.println("IMPOSSIBLE");
        } else {
            // As + Bt = r, Ax + By = C 두 식에서 C와 r을 일치시켜서 x0, y0를 구함 -> 초기해
            // x0 = s * C/r
            // y0 = t * C/r
            long x0 = result.s;
            long y0 = result.r;

            // 일반해 공식
            // x = x0 + B/gcd(1) * K
            // y = y0 - A/gcd(1) * K

            // x < 0 범위가 반전되어 있는 x
            // x0 + B * k < 0
            // k < - x0 / B

            // 0 < y <= 10^9(1e9)
            // 0 < y0 - A * K <= 1e9
            // -y0 < - A * K <= 1e9 - y0
            // (y0 - 1e9) / A <= K < y0 / A

            // (y0 - 1e9) / A <= K < y0 / A
            //                   K < - x0 / B

            long kFromY = (long) (Math.ceil((double) y0 / (double) A) - 1);
            long kFromX = (long) (Math.ceil((double) -x0 / (double) B) - 1);
            long k = Math.min(kFromX, kFromY);
            long kLimitFromY = (long) Math.ceil((double) (y0 - 1e9) / (double) A);

            if(kLimitFromY <= k){
                System.out.println(y0 - A * k);
            } else{
                System.out.println("IMPOSSIBLE");
            }

        }
    }

    static EGResult egcd(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long temp;
        while (r1 != 0) {
            long q = r0 / r1;

            temp = r0 - q * r1; // r0 % r1
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1;
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1;
            t0 = t1;
            t1 = temp;
        }

        return new EGResult(s0, t0, r0);
    }
}

class EGResult {
    long s;
    long t;
    long r;

    @Override
    public String toString() {
        return "EGResult{" +
                "s=" + s +
                ", t=" + t +
                ", r=" + r +
                '}';
    }

    public EGResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}