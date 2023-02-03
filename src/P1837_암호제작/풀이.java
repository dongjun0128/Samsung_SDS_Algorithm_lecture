package P1837_암호제작;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 풀이 {
    static int K;
    static char[] P;
    static boolean[] checked;
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        checked = new boolean[K + 1];

        for (int i = 2; i <= K; i++) {
            if (checked[i] = false) {
                primes.add(i);
                for (int j = i * 2; j <= K; j += i) {
                    checked[j] = true;
                }
            }
        }

        for (int i = 0; i < primes.size(); i++) {
            if(checkIsBad(primes.get(i))){
                System.out.println("BAD " + primes.get(i));
                return;
            }
        }

        System.out.println("GOOD");
    }

    static boolean checkIsBad(int x) {
        int r = 0;

        for (int i = 0; i < P.length; i++) {
            r = (r * 10 + P[i] - '0') % x;
        }

        if (r == 0) {
            return true;
        }
        return false;
    }
}
