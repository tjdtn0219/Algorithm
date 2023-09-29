import java.util.*;

class Solution {
    
    ArrayList<ArrayList<String>> list = new ArrayList<>();
    HashMap<String, ArrayList<Integer>> hmap = new HashMap<>();
    ArrayList<String> queryList;
    String[] temp = new String[4];
    
    public int[] solution(String[] infos, String[] querys) {
        
        int n = querys.length;
        int[] ans = new int[n];
        
        for(int i=0; i<4; i++) list.add(new ArrayList<>());
        
        list.get(0).add("cpp");
        list.get(0).add("java");
        list.get(0).add("python");
        
        list.get(1).add("backend");
        list.get(1).add("frontend");
        
        list.get(2).add("junior");
        list.get(2).add("senior");
        
        list.get(3).add("chicken");
        list.get(3).add("pizza");
        
        makeInfoComb(0);
        
        for(String info : infos) {
            String[] sArr = info.split(" ");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<4; i++) {
                sb.append(sArr[i]);
            }
            ArrayList<Integer> tList = hmap.get(sb.toString());
            tList.add(Integer.parseInt(sArr[4]));
            hmap.put(sb.toString(), tList);
        }
        
        for(String key : hmap.keySet()) Collections.sort(hmap.get(key));
        
        int idx = 0;
        for(String query : querys) {
            String[] strings = query.split(" ");
            int score = Integer.parseInt(strings[7]);
            queryList = new ArrayList<>();
            makeQueryComb(strings, 0);
            ans[idx++] = getResultFromQuery(score);
        }
        
        
        return ans;
    }
    
    public int getResultFromQuery(int target) {
        int cnt = 0;
        for(String q : queryList) {
            ArrayList<Integer> scores = hmap.get(q);
            int lowIdx = lower_idx(target, scores);
            if(scores.size()==0) continue;
            if(scores.get(scores.size()-1) < target) continue;
            cnt += scores.size() - lowIdx;
        }
        return cnt;
    }
    
    public static int lower_idx (int tg, ArrayList<Integer> list) {
        int st = 0;
        int en = list.size();

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= list.get(mid)) en = mid;
            else st = mid + 1;          //tg > arr[mid]
        }
        return st;
    }
    
    public void makeQueryComb(String[] strings, int k) {
        if(k==4) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<4; i++) {
                sb.append(temp[i]);
            }
            queryList.add(sb.toString());
            return ;
        }
        
        if(strings[2*k].equals("-")) {
            for(String s : list.get(k)) {
                temp[k] = s;
                makeQueryComb(strings, k+1);
            }
        } else {
            temp[k] = strings[2*k];
            makeQueryComb(strings, k+1);
        }
    }
    
    public void makeInfoComb(int k) {
        if(k==4) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<4; i++) {
                sb.append(temp[i]);
            }
            String s = sb.toString().strip();
            // System.out.println(s);
            hmap.put(s, new ArrayList<>());
            return ;
        }
        
        for(String s : list.get(k)) {
            temp[k] = s;
            makeInfoComb(k+1);
        }
        
    }
}