package P2805_나무_자르기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] trees;
    static long temp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P2805_나무_자르기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];

        st = new StringTokenizer(br.readLine());
        int maxTree = 0;

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            maxTree = Math.max(maxTree, trees[i]);
        }

        System.out.println(binarySearch(0, maxTree));
    }

    public static long binarySearch(int start, int end) {
        if (start > end) return temp;

        int mid = (start + end) / 2;

        if (cutTrees(mid) == M) return mid;
        else if (cutTrees(mid) > M) {
            temp =Math.max(temp,mid);
            return binarySearch(mid + 1, end);
        } else {
            return binarySearch(start, mid - 1);
        }
    }

    public static long cutTrees(int height) {
        long sum = 0;

        for (int i = 0; i < N; i++) {
            if (trees[i] - height > 0) sum += trees[i] - height;
        }

        return sum;
    }
}
