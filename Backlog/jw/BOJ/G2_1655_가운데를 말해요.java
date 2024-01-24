import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (maxHeap.size() == minHeap.size()) {
                maxHeap.offer(n);
            } else {
                minHeap.offer(n);
            }

            if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                if (maxHeap.peek() > minHeap.peek()) {
                    int temp = maxHeap.poll();
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(temp);
                }
            }

            sb.append(maxHeap.peek()).append('\n');
        }
        System.out.println(sb);
    }
}
