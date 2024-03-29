import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int n, m;
    static int[] d, pre;
    static int start, end;
    static List<ArrayList<City>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        d = new int[n + 1];
        pre = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(from).add(new City(to, weight));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        sb.append(d[end]).append('\n');

        int cnt = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(end);
        while (pre[end] != 0) {
            cnt++;
            stack.push(pre[end]);
            end = pre[end];
        }

        sb.append(cnt).append('\n');
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            City nowCity = pq.poll();
            int now = nowCity.to;
            if (d[now] < nowCity.weight) {
                continue;
            }
            for (City next : graph.get(now)) {
                if (d[next.to] > d[now] + next.weight) {
                    d[next.to] = d[now] + next.weight;
                    pre[next.to] = now;
                    pq.offer(new City(next.to, d[next.to]));
                }
            }
        }
    }
}

class City implements Comparable<City> {
    int to;
    int weight;

    public City(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(City o) {
        return this.weight - o.weight;
    }
}
