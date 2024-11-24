import java.util.*;

class Solution {
    
    static final int N = 1_000_000;
    
    HashMap<Integer, Integer> in;
    HashMap<Integer, Integer> out;
    int createNode;
    int stickCnt;
    int donutCnt;
    int eightCnt;
    
    public int[] solution(int[][] edges) {
        init(edges);
        solve();
        return new int[]{createNode, donutCnt, stickCnt, eightCnt};
    }
    
    public void solve() {
        for(int key : out.keySet()) {
            if(out.get(key) > 1) {
                if(!in.containsKey(key)) {
                    createNode = key;
                } else {
                    eightCnt++;
                } 
            }
        }
        
        for(int key : in.keySet()) {
            if(!out.containsKey(key)) {
                stickCnt++;
            }
        }
        donutCnt = out.get(createNode) - stickCnt - eightCnt;
    }
    
    public void init(int[][] edges) {
        in = new HashMap<>();
        out = new HashMap<>();
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            out.put(a, out.getOrDefault(a, 0) + 1);
            in.put(b, in.getOrDefault(b, 0) + 1);
        }
    }
}