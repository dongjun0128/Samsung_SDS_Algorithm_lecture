package P1717_집합의_표현;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1717_집합의_표현/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];


        for (int i = 0; i <= N; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 0) { //union(a,b)
                int a, b;
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                union(Math.min(a, b), Math.max(a, b));
            } else { //find(a,b)
                int a, b;
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                int rootA = find(a);
                int rootB = find(b);

                if (rootA != rootB) System.out.println("NO");
                else System.out.println("YES");
            }
        }

    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB){
            arr[rootA] = rootB;
        }
    }

    static int find(int a) {
        if (arr[a] == a) return a;
        else return arr[a] = find(arr[a]);
    }
}
