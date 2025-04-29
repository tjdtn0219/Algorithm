import java.util.*;

class Operator {
    char op;
    int prior;
    public Operator(char op, int prior) {
        this.op = op;
        this.prior = prior;
    }
}

class Solution {
    
    static final char[] OPS = {'*', '-', '+'};
    
    String s;
    List<Character> ops;
    List<Integer> nums;
    long answer;
    
    public long solution(String expression) {
        init(expression);
        solve();
        return answer;
    }
    
    public void solve() {
        makeComb(0, new int[3], new boolean[3]);
    }
    
    private void printMap(HashMap<Character, Integer> priorMap) {
        StringBuilder sb = new StringBuilder();
        for(char key : priorMap.keySet()) {
            sb.append(key).append(" : ").append(priorMap.get(key)).append("\n");
        }
        System.out.println(sb);
    }
    
    public void makeComb(int k, int[] comb, boolean[] vis) {
        if(k == 3) {
            HashMap<Character, Integer> priorMap = new HashMap<>();
            for(int i=0; i<3; i++) {
                priorMap.put(OPS[i], comb[i]);
            }
            // printMap(priorMap);
            int val = calculate(priorMap);
            answer = Math.max(answer, Math.abs(val));
            return ;
        }
        
        for(int i=0; i<3; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            comb[k] = i;
            makeComb(k+1, comb, vis);
            vis[i] = false;
        }
    }
    
    private void printList(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for(int num : list) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public int calculate(HashMap<Character, Integer> priorMap) {
        List<Integer> numList = new ArrayList<>(nums);
        List<Character> opList = new ArrayList<>(ops);
        for(int p=0; p<3; p++) {
            while(!opList.isEmpty()) {
                boolean flag = false;
                for(int i=0; i<opList.size(); i++) {
                    if(priorMap.get(opList.get(i)) == p) {
                        int n1 = numList.remove(i);
                        int n2 = numList.remove(i);
                        char op = opList.remove(i);
                        int val = operate(n1, n2, op);
                        numList.add(i, val);
                        // System.out.println("n1, n2, val : " + n1 + " " + n2 + " " + val);
                        flag = true;
                        break;
                    }
                }
                if(!flag) break;
            }
            // printList(numList);
        }
        return numList.get(0);
    }
    
    public int operate(int n1, int n2, char op) {
        if(op == '*') {
            return n1 * n2;
        } else if(op == '-') {
            return n1 - n2;
        } else {
            return n1 + n2;
        }
    }
    
    public void init(String s) {
        this.s = s;
        this.nums = new ArrayList<>();
        String[] splits = s.split("\\*|\\+|\\-");
        for(String tmp : splits) {
            nums.add(Integer.parseInt(tmp));
        }
        this.ops = new ArrayList<>();
        for(char c : s.toCharArray()) {
            if(c == '*' || c == '+' || c == '-') {
                ops.add(c);
            }
        }
        // printCharList(ops);
    }
    
    private void printCharList(List<Character> list) {
        StringBuilder sb = new StringBuilder();
        for(char c : list) {
            sb.append(c).append(" ");
        }
        System.out.println(sb);
    }
}