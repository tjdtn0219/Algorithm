import java.util.*;

class Process {
    int idx;
    int priority;
    public Process(int idx, int priority) {
        this.idx = idx;
        this.priority = priority;
    }
}

class Solution {
    
    int answer;
    Queue<Process> q;
    PriorityQueue<Process> pq;
    int location;
    
    public int solution(int[] priorities, int location) {
        init(priorities, location);
        solve();
        return answer;
    }
    
    public void solve() {
        while(!q.isEmpty()) {
            Process prior = pq.poll();
            System.out.println("priorIdx : " + prior.idx);
            while(q.peek().priority != prior.priority) {
                q.add(q.poll());
            }
            // System.out.println("curIdx : " + q.peek().idx);
            // printQ();
            answer++;
            if(location == q.poll().idx) {
                return ;
            }
        }
    }
    
    public void printQ() {
        StringBuilder sb = new StringBuilder();
        sb.append("q : [");
        for(Process p : q) {
            sb.append(p.idx + ", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
    
    public void init(int[] priorities, int location) {
        this.q = new LinkedList<>();
        this.pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.priority == o2.priority) {
                return o1.idx - o2.idx;
            }
            return o2.priority - o1.priority;
        });
        this.location = location;
        for(int i=0; i<priorities.length; i++) {
            q.add(new Process(i, priorities[i]));
            pq.add(new Process(i, priorities[i]));
        }
    }
}