import java.util.*;

class Node {
    Node prev = null;
    Node next = null;
    boolean isDelete = false;
    int idx = 0;
    
    public void delete() {
        this.isDelete = true;
        
        if(prev != null) {
            prev.next = next;
        }
        
        if(next != null) {
            next.prev = prev;
        }
    }
    
    public void restore() {
        this.isDelete = false;
        
        if(prev != null) {
            prev.next = this;
        }
        
        if(next != null) {
            next.prev = this;
        }
    }
}

class Cmd {
    char op;
    int num;
    public Cmd(char op, int num) {
        this.op = op;
        this.num = num;
    }
}

class Solution {
    
    String answer;
    List<Cmd> cmdList;
    int n;
    Node cur;
    Stack<Node> stk;
    Node[] chart;
    
    public String solution(int n, int k, String[] cmd) {
        init(n, k, cmd);
        solve();
        return answer;
    }
    
    public void solve() {
        for(Cmd cmd : cmdList) {
            if(cmd.op == 'U') {
                for(int i=0; i<cmd.num; i++) {
                    cur = cur.prev;
                }
            } else if(cmd.op == 'D') {
                for(int i=0; i<cmd.num; i++) {
                    cur = cur.next;
                }
            } else if(cmd.op == 'C') {
                if(cur.next == null) {
                    // 마지막 행
                    Node tmp = cur.prev;
                    stk.add(cur);
                    cur.delete();
                    cur = tmp;
                } else {
                    Node tmp = cur.next;
                    stk.add(cur);
                    cur.delete();
                    cur = tmp;
                }
            } else {
                Node node = stk.pop();
                node.restore();
            }
            // printChart();
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            if(chart[i].isDelete) {
                sb.append('X');
            } else {
                sb.append('O');
            }
        }
        answer = sb.toString();
    }
    
    private void printChart() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            if(chart[i].isDelete) {
                sb.append('X');
            } else {
                sb.append('O');
            }
        }
        sb.append(" , cur : " + cur.idx);
        System.out.println(sb);
    }
    
    public void init(int n, int k, String[] cmds) {
        this.cmdList = new ArrayList<>();
        this.stk = new Stack<>();
        this.n = n;
        this.chart = new Node[n];
        
        for(String cmd : cmds) {
            String[] tmp = cmd.split(" ");
            char c = tmp[0].charAt(0);
            if(tmp.length > 1) {
                int num = Integer.parseInt(tmp[1]);
                cmdList.add(new Cmd(c, num));
            } else {
                cmdList.add(new Cmd(c, 0));
            }
        }
        
        for(int i=0; i<n; i++) {
            chart[i] = new Node();
            chart[i].idx = i;
        }
        for(int i=0; i<n; i++) {
            if(i == 0) {
                chart[0].next = chart[i+1];
            } else if(i == n-1) {
                chart[i].prev = chart[i-1];
            } else {
                chart[i].prev = chart[i-1];
                chart[i].next = chart[i+1];
            }
        }
        this.cur = chart[k];

    }
    
}