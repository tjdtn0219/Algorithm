import java.util.*;
 
public class Main {    
    
    static int n, s, e;
    static ArrayList<Node> list[];
    static boolean[] visited;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        n = scan.nextInt();
        int m = scan.nextInt();
        
        list = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }
        
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int cost = scan.nextInt();    
            max = Math.max(cost, max);
            min = Math.min(cost, min);
            list[x].add(new Node(y, cost));
            list[y].add(new Node(x, cost));
        }
        
        s = scan.nextInt();
        e = scan.nextInt();
        int result = 0;
        while(min <= max) {
            int mid = (min + max) / 2;
            visited = new boolean[n + 1];
            
            if(bfs(mid)) { //s ~ e까지 mid의 중량이 건널 수 있는지 확인.
                min = mid + 1;
                result = mid;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(result);
    }
    
    public static boolean bfs(int mid) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;
        
        while(!q.isEmpty()) {
            int temp = q.poll();
            
            if(temp == e) return true;
            
            for(int i = 0; i < list[temp].size(); i++) {
                if(list[temp].get(i).cost >= mid && visited[list[temp].get(i).n] == false) {
                    visited[list[temp].get(i).n] = true;
                    q.offer(list[temp].get(i).n);
                }
            }
        }
        return false;
    }
    
    public static class Node{
        int n;
        int cost;
        
        public Node(int n, int cost) {
            this.n = n;
            this.cost = cost;
        }
    }
}