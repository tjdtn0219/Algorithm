import java.util.*;

class Task {
    String name;
    int start;
    int rest;
    public Task(String name, int start, int rest) {
        this.name = name;
        this.start = start;
        this.rest = rest;
    }
}

class Solution {
    
    int n;
    PriorityQueue<Task> pq;
    Deque<Task> stoppedQ;
    List<String> ansList;
    
    public String[] solution(String[][] plans) {
        init(plans);
        solve();
        return ansList.toArray(new String[0]);
    }
    
    public void solve() {
        Task cur = pq.poll();
        int curT = cur.start;
        
        while(!pq.isEmpty()) {
            Task nxt = pq.poll();
            // System.out.println("cur : " + cur.name + ", nxt : " + nxt.name + ", curT : " + curT);
            if(curT + cur.rest <= nxt.start) {
                // System.out.println("TAG1 : " + cur.name);
                //현재 끝나자마자 다음꺼 바로 시작
                ansList.add(cur.name);
                
                int availT = nxt.start - (curT + cur.rest);
                while(!stoppedQ.isEmpty()) {
                    //현재 끝났는데 시간 여유 있을 때
                    Task task = stoppedQ.pollLast();
                    if(task.rest <= availT) {
                        ansList.add(task.name);
                        availT -= task.rest;
                    } else {
                        task.rest -= availT;
                        stoppedQ.addLast(task);
                        break;
                    }
                }
                cur = nxt;
                curT = nxt.start;
            } else {
                // System.out.println("TAG-stop : " + cur.name);
                //중간 멈춤
                cur.rest = curT + cur.rest - nxt.start;
                stoppedQ.addLast(cur);
                cur = nxt;
                curT = nxt.start;
            }
            if(pq.isEmpty()) {
                // System.out.println("last : " + nxt.name);
                ansList.add(nxt.name);
            }
            // printAns();
        }
        
        System.out.println(stoppedQ.size());
        
        while(!stoppedQ.isEmpty()) {
            ansList.add(stoppedQ.pollLast().name);
        }
    }
    
    private void printAns() {
        StringBuilder sb = new StringBuilder();
        sb.append("ans : ");
        for(String s : ansList) {
            sb.append(s).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(String[][] plans) {
        this.pq = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        this.stoppedQ = new LinkedList<>();
        for(String[] plan : plans) {
            pq.add(new Task(plan[0], toInt(plan[1]), Integer.parseInt(plan[2])));
        }
        ansList = new ArrayList<>();
    }
    
    public int toInt(String time) {
        String[] splits = time.split(":");
        int h = Integer.parseInt(splits[0]);
        int m = Integer.parseInt(splits[1]);
        return h*60 + m;
    }
}