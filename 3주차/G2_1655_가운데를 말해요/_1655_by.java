package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minHp = new PriorityQueue<>();
        PriorityQueue<Integer> maxHp = new PriorityQueue<>(Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(br.readLine());
            if(minHp.size() == maxHp.size()){
                maxHp.add(num);
            }else {
                minHp.add(num);
            }

            if ((!minHp.isEmpty()) && (maxHp.peek() > minHp.peek())){
                int min = minHp.poll();
                int max = maxHp.poll();
                maxHp.add(min);
                minHp.add(max);
            }

            sb.append(maxHp.peek());
            if(i < n-1) sb.append("\n");

        }
        System.out.println(sb.toString());
    }
}
