import java.util.*;

class Node {
    Node prev = null;
    Node next = null;
    boolean isDelete = false;
    
    public void delete() {
        this.isDelete = true;
        
        if(this.prev != null) {
            this.prev.next = this.next;
        }
        
        if(this.next != null) {
            this.next.prev = this.prev;
        }
    }
    
    public void restore() {
        this.isDelete = false;
        
        if(this.prev != null) {
            this.prev.next = this;
        }
        
        if(this.next != null) {
            this.next.prev = this;
        }
    }
    
}

class Solution {
    
    int n;
    Node cur;
    String[] cmds;
    Node[] chart;
    char[] states;
    String answer;
    Stack<Node> stk;
    
    public String solution(int n, int k, String[] cmd) {
        init(n, k, cmd);
        solve();
        return answer;
    }
    
    public void solve() {
        for(String cmd : cmds) {
            if(cmd.charAt(0) == 'U') {
                up(Integer.parseInt(cmd.split(" ")[1]));
            } else if(cmd.charAt(0) == 'D') {
                down(Integer.parseInt(cmd.split(" ")[1]));
            } else if(cmd.charAt(0) == 'C') {
                delete();
            } else {
                rollBack();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            // System.out.println(chart[i].isDelete);
            if(chart[i].isDelete) {
                sb.append('X');
            } else {
                sb.append('O');
            }
        }
        answer = sb.toString();
    }
    
    public void up(int x) {
        for(int i=0; i<x; i++) {
            cur = cur.prev;
        }
    }
    
    public void down(int x) {
        for(int i=0; i<x; i++) {
            cur = cur.next;
        }
    }
    
    public void delete() {
        stk.add(cur);
        Node tmp;
        if(cur.next != null) {
            tmp = cur.next;
        } else {
            tmp = cur.prev;
        }
        
        cur.delete();
        cur = tmp;
    }
    
    public void rollBack() {
        if(stk.isEmpty()) {
            return ;
        }
        Node popped = stk.pop();
        popped.restore();
    }
    
    public void init(int n, int k, String[] cmd) {
        this.n = n;
        this.cmds = cmd;
        this.chart = new Node[n];
        initChart();
        this.cur = chart[k];
        this.states = new char[n];
        Arrays.fill(states, 'O');
        this.stk = new Stack<>();
    }
    
    public void initChart() {
        for(int i=0; i<n; i++) {
            chart[i] = new Node();
        }
        chart[0].next = chart[1];
        chart[n-1].prev = chart[n-2];
        for(int i=1; i<n-1; i++) {
            chart[i].prev = chart[i-1];
            chart[i].next = chart[i+1];
        }
    }
}