import java.util.*;
import java.time.LocalDateTime;

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
    Stack<Task> stk;
    List<String> ansList;
    
    public String[] solution(String[][] plans) {
        init(plans);
        solve();
        return ansList.toArray(new String[0]);
    }
    
    public void solve() {
        Task cur = pq.poll();
        
        while(!pq.isEmpty()) {
            Task nxt = pq.poll();
            int finishTime = cur.start + cur.rest;
            // System.out.println("cur : " + cur.name + ", nxt : " + nxt.name + ", finishTime : " + finishTime);
            if(finishTime <= nxt.start) {
                // System.out.println("다음 과제 전에 현재 종료");
                // 다음 과제 전에 현재 종료
                ansList.add(cur.name);
                int idle = nxt.start - finishTime;
                // System.out.println("idle : " + idle);
                while(!stk.isEmpty()) {
                    //현재 끝났는데 시간 여유 있을 때
                    Task task = stk.pop();
                    if(task.rest <= idle) {
                        ansList.add(task.name);
                        idle -= task.rest;
                    } else {
                        task.rest -= idle;
                        stk.add(task);
                        break;
                    }
                }
            } else {
                // 다음 과제 실행
                // System.out.println("현재꺼 멈추고 다음과제 실행");
                cur.rest = finishTime - nxt.start;
                stk.add(cur);
            }
            cur = nxt;
            // break;
            if(pq.isEmpty()) {
                ansList.add(nxt.name);
            }
        }
        
        while(!stk.isEmpty()) {
            ansList.add(stk.pop().name);
        }
    }
    
    public void init(String[][] plans) {
        this.pq = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        this.stk = new Stack<>();
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