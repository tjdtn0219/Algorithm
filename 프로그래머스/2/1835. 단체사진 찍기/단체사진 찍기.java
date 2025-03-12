import java.util.*;

class Operation {
    char a;
    char b;
    char op;
    int num;
    
    public Operation(char a, char b, char op, int num) {
        this.a = a;
        this.b = b;
        this.op = op;
        this.num = num;
    }
}

class Solution {
    
    public static final char[] FRIENDS = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    public static final int N = 8;
    
    int n;
    List<Operation> opList;
    char[] comb;
    int ans;
    
    public int solution(int n, String[] data) {
        init(n, data);
        solve();
        return ans;
    }
    
    public void solve() {
        makeComb(0, new boolean[N]);
    }
    
    public void makeComb(int k, boolean[] vis) {
        if(k == N) {
            if(isOkay()) {
                ans++;
            }
            return ;
        }
        
        for(int i=0; i<N; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            comb[k] = FRIENDS[i];
            makeComb(k+1, vis);
            vis[i] = false;
        }
    }
    
    public boolean isOkay() {
        HashMap<Character, Integer> posMap = new HashMap<>();
        for(int i=0; i<N; i++) {
            posMap.put(comb[i], i);
        }
        for(Operation operation : opList) {
            char a = operation.a;
            char b = operation.b;
            char op = operation.op;
            int num = operation.num;
            
            int dis = Math.abs(posMap.get(a) - posMap.get(b)) - 1;
            if(op == '<' && dis >= num) {
                return false;
            } else if(op == '>' && dis <= num) {
                return false;
            } else if(op == '=' && dis != num) {
                return false;
            }
        }
        return true;
    }
    
    public void init(int n, String[] datas) {
        this.opList = new ArrayList<>();
        this.n = n;
        this.comb = new char[N];
        for(String data : datas) {
            char a = data.charAt(0);
            char b = data.charAt(2);
            char op = data.charAt(3);
            int num = data.charAt(4) - '0';
            opList.add(new Operation(a, b, op, num));
        }
    }
}