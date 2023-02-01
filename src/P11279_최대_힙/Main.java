package P11279_최대_힙;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P11279_최대_힙/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        int pointer = 1;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if (num == 0) {
                if(pointer == 1){
                    System.out.println(0);
                    continue;
                }

                System.out.println(arr[1]);

                arr[1] = arr[pointer - 1];
                arr[--pointer] = 0;

                int tempPointer = 1;

                while(true){

                    int leftChild = findLeftChild(tempPointer);
                    int rightChild = findRightChild(tempPointer);

                    if(arr[leftChild] < arr[rightChild]){
                        swap(rightChild,tempPointer);
                        tempPointer = rightChild;
                    } else {
                        swap(leftChild,tempPointer);
                        tempPointer = leftChild;
                    }

                    if(findLeftChild(tempPointer) > pointer) break;
                    if(arr[tempPointer] > arr[findLeftChild(tempPointer)] && arr[tempPointer] > arr[findRightChild(tempPointer)]) break;

                }

            } else {
                arr[pointer++] = num;
                int tempPointer = pointer - 1;

                while(true){
                    if(tempPointer == 1) break;
                    if(arr[findParents(tempPointer)] > arr[tempPointer]) break;
                    swap(findParents(tempPointer), tempPointer);
                    tempPointer = findParents(tempPointer);
                }

            }
        }
    }

    public static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int findParents(int i) {
        return i / 2;
    }

    public static int findLeftChild(int i) {
        return i * 2;
    }

    public static int findRightChild(int i) {
        return i * 2 + 1;
    }
}
