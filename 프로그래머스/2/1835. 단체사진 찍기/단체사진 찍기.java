import java.util.*;

class Data {
    char c1;
    char c2;
    char op;
    int val;
    public Data(char c1, char c2, char op, int val) {
        this.c1 = c1;
        this.c2 = c2;
        this.op = op;
        this.val = val;
    }
}

class Solution {
    
    static final int N = 8;
    static final char[] FRIENDS = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    
    int answer;
    int n;
    List<Data> dataList;
    char[] comb;
    boolean[] vis;
    
    public int solution(int n, String[] data) {
        
        init(n, data);
        for(Data d : dataList) {
            System.out.println(d.c1 + " " + d.c2 + " " + d.op + " " + d.val);
        }
        solve();
        return answer;
    }
    
    public void solve() {
        makeComb(0);
    }
    
    public void makeComb(int k) {
        if(k == N) {
            // printComb();
            if(isOkay()) {
                answer++;
            }
            return ;
        }
        
        for(int i=0; i<N; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            comb[k] = FRIENDS[i];
            makeComb(k+1);
            vis[i] = false;
        }
    }
    
    public boolean isOkay() {
        HashMap<Character, Integer> map = toMap(comb);
        for(Data data : dataList) {
            int dis = Math.abs(map.get(data.c1) - map.get(data.c2)) - 1;
            if(data.op == '=') {
                if(dis != data.val) return false;
            } else if(data.op == '>') {
                if(dis <= data.val) return false;
            } else if(data.op == '<') {
                if(dis >= data.val) return false;
            }
        }
        return true;
    }
    
    public HashMap<Character, Integer> toMap(char[] arr) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            map.put(arr[i], i);
        }
        return map;
    }
    
    private void printComb() {
        StringBuilder sb = new StringBuilder();
        for(char c : comb) {
            sb.append(c).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(int n, String[] datas) {
        this.n = n;
        this.dataList = new ArrayList<>();
        for(String data : datas) {
            dataList.add(parseStr(data));
        }
        this.comb = new char[N];
        this.vis = new boolean[N];
    }
    
    public Data parseStr(String str) {
        char c1 = str.charAt(0);
        char c2 = str.charAt(2);
        char op = str.charAt(3);
        int val = str.charAt(4) - '0';
        return new Data(c1, c2, op, val);
    }
}