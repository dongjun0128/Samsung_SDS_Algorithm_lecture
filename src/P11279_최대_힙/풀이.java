package P11279_최대_힙;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 풀이 {
    static int N;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P11279_최대_힙/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        MaxHeap maxHeap = new MaxHeap();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if(num == 0){
                if(maxHeap.list.size() == 1) {
                    System.out.println(0);
                } else {
                    maxHeap.delete();
                }
            } else{
                maxHeap.insert(num);
            }

        }
    }
}

class MaxHeap {
    List<Integer> list;

    public MaxHeap() {
        list = new ArrayList<>(100001);
        list.add(0);
    }

    public void insert(int val) {
        // 1. 마지막에 추가
        list.add(val);

        // 2. 부모랑 조건 비교, 교환
        int current = list.size() - 1;
        int parent = current / 2;

        while (true) {
            // 1. current가 root면 탈출
            // 2. 부모, 자식 조건을 만족하면 탈출
            if(parent == 0 || list.get(parent) >= list.get(current)){
                break;
            }

            // swap
            int temp = list.get(parent);
            list.set(parent, list.get(current));
            list.set(current, temp);

            current = parent;
            parent = current / 2;
        }

    }

    public int delete() {
        // 1. Root 제거
        int top = list.get(1);

        // 2. 마지막 노드를 Root로 이동
        list.set(1, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        // 3. 왼쪽, 오른쪽 중 조건이 안맞는 것을 선택 후 조건에 맞게 SWAP
        int currentNode = 1;
        while(true){
            int leftNode = currentNode * 2;
            int rightNode = currentNode * 2 + 1;

            if(leftNode >= list.size()){
                break;
            }

            int targetValue = list.get(leftNode);
            int targetNode = leftNode;

            if(rightNode < list.size() && targetValue < list.get(rightNode)){
                targetNode = rightNode;
                targetValue = list.get(rightNode);
            }

            if(list.get(currentNode) < targetValue){
                int temp = list.get(currentNode);
                list.set(currentNode, list.get(targetNode));
                list.set(targetNode, temp);
                currentNode = targetNode;
            } else{
                break;
            }
        }

        return top;
    }
}
