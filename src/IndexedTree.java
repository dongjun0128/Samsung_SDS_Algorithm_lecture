public class IndexedTree {

    static int N, M, K;
    static long[] nums;

    static long[] tree;

    static int S;

    public static void main(String[] args) {
        N = 5;
        nums = new long[]{1, 2, 3, 4, 5};

        S = 1;
        // 외부노트(leaf) 개수를 찾는 방법
        while (S < N) {
            S *= 2;
        }

        tree = new long[S * 2];

    }

    static void init() {
        //leaf는 data로
        for (int i = 0; i < N; i++) {
            tree[S + i] = nums[i];
        }

        // 내부노드는 자식의 합
        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        // 1. 연관 없음
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        // 2. 판단 가능 (쏙 들어감)
        else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        // 3. 판단 불가 (걸쳐 있음)
        else {
            int mid = (left + right) / 2;
            long leftResult = query(left, mid - 1, node * 2, queryLeft, queryRight);
            long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return leftResult + rightResult;
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        // 1. 연관 없음
        if (target < left || right < target) return;
            // 2. 연관 있음
        else {
            tree[node] += diff;
            if (left != right) { // 외부노드(leaf)가 아닌 경우 자식노드에게도 연산 수행
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }

    static long queryBottomUp(int queryLeft, int queryRight) {
        long sum = 0;
        int left = S + queryLeft - 1;
        int right = S + queryRight - 1;

        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }

            if (right % 2 == 0) {
                sum += tree[right--];
            }
            left /= 2;
            right /= 2;
        }

        return sum;
    }

    static void updateBottomUp(int target, long value) {
        int node = S + target - 1;
        tree[node] = value;
        node /= 2;
        while (node > 0) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }
}
