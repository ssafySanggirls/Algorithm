package silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1966 {
    static Queue<Printer> docs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case<=t; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()); // 문서의 개수
            int m = Integer.parseInt(st.nextToken()); // 찾아야할 인덱스
            //PriorityQueue<Printer> docs = new PriorityQueue<>();
            docs= new ArrayDeque<>();
            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0; i<n; i++){
                int x = Integer.parseInt(st.nextToken());
                docs.add(new Printer(i, x));
            }
            int cnt = 0; // 몇 번째에 인쇄되는지
            while(!docs.isEmpty()){
                Printer printer = docs.poll();
                if(isMax(printer.important)){
                    if(printer.index == m){
                        cnt++;
                        break;
                    }else{
                        cnt++;
                    }
                }else{
                    docs.add(printer);
                }
                //System.out.println(docs.peek().index+" "+docs.peek().important);
//                if(docs.peek().index == m){
//                    cnt++;
//                    break;
//                }else{
//                    docs.poll();
//                    cnt++;
//                }
            }
            System.out.println(cnt);
        }
    }

    private static boolean isMax(int important) {
        boolean isOkay = true;
        for(int i=0; i<docs.size(); i++){
            Printer printer = docs.poll();
            if(printer.important > important){
                isOkay = false;
            }
            docs.add(printer);
        }
        return isOkay;
    }

    static class Printer implements Comparable<Printer>{
        int index;
        int important;
        public Printer(int index, int important){
            this.index = index;
            this.important = important;
        }

        @Override
        public int compareTo(Printer o) {
            return o.important-this.important;
        }
    }
}
