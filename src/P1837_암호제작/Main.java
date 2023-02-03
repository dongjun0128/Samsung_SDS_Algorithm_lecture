package P1837_암호제작;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1837_암호제작/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String P = st.nextToken();
        int K = Integer.parseInt(st.nextToken());

        boolean[] prime = new boolean[K];

        prime[0] = true;
        prime[1] = true;

        for(int i=2; i*i<=K; i++){
            // prime[i]가 소수라면
            if(!prime[i]){
                int temp;
                int j = 2;

                // prime[j] 소수가 아닌 표시
                while(true){
                    temp = j * i;
                    j++;
                    if(temp >= K) break;
                    prime[temp] = true;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            if(!prime[i]) primes.add(i);
        }


        for (int i = 0; i < primes.size(); i++) {
            int num = primes.get(i);

            int temp = 0;

            for (int j = 0; j < P.length(); j++) {
                temp += Integer.parseInt(String.valueOf(P.charAt(j)));

                int divide = temp / num;

                if(divide > 0){
                    temp = temp - num * divide;
                }

                temp = temp * 10;
            }

            if(temp == 0) {
                System.out.println("BAD " + num);
                return;
            }
        }

        System.out.println("GOOD");
    }
}
