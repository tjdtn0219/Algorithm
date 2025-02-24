import java.util.*;

class Solution {
    
    static final String[] LANG = {"java", "python", "cpp"};
    static final String[] POS = {"backend", "frontend"};
    static final String[] EXP = {"junior", "senior"};
    static final String[] FOOD = {"chicken", "pizza"};
    
    HashMap<String, List<Integer>> scoreListMap;
    int[] answer;
    List<String> keyList;
    
    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];
        initScoreListMap(info);
        doQuery(query);
        return answer;
    }
    

    public void doQuery(String[] queries) {
        int idx = 0;
        for(String query : queries) {
            String[] tmp = query.split(" and ");
            int score = Integer.parseInt(tmp[3].split(" ")[1]);
            tmp[3] = tmp[3].split(" ")[0];
            // System.out.println("query : " + query);
            keyList = new ArrayList<>();
            dfs(tmp, 0, "");
            int cnt = 0;
            for(String key : keyList) {
                cnt += getCnt(key, score);
            }
            answer[idx++] = cnt;
        }
    }
    
    public int getCnt(String key, int target) {
        List<Integer> scoreList = scoreListMap.get(key);
        int st = 0;
        int en = scoreList.size();
        
        while(st < en) {
            int mid = (st + en) / 2;
            if(target <= scoreList.get(mid)) en = mid;
            else st = mid + 1;
        }
        return scoreList.size() - st;
    }
    
    public void dfs(String[] tmp, int k, String s) {
        if(k == 4) {
            keyList.add(s);
            // System.out.println("key : " + s);
            return ;
        }
        
        if(k==0 && tmp[k].equals("-")) {
            for(String lang : LANG) {
                dfs(tmp, k+1, s + lang);
            }
        } else if(k==1 && tmp[k].equals("-")) {
            for(String pos : POS) {
                dfs(tmp, k+1, s + pos);
            }
        } else if(k==2 && tmp[k].equals("-")) {
            for(String exp : EXP) {
                dfs(tmp, k+1, s + exp);
            }
        } else if(k==3 && tmp[k].equals("-")) {
            for(String food : FOOD) {
                dfs(tmp, k+1, s + food);
            }
        } else {
            dfs(tmp, k+1, s + tmp[k]);
        }
        
    }
    
    public void initScoreListMap(String[] infos) {
        scoreListMap = new HashMap<>();
        for(String lang : LANG) {
            for(String pos : POS) {
                for(String exp : EXP) {
                    for(String food : FOOD) {
                        String key = lang + pos + exp + food;
                        scoreListMap.put(key, new ArrayList<>());
                    }
                }
            }
        }
        setScoreListMap(infos);
    }
    
    public void setScoreListMap(String[] infos) {
        for(String info : infos) {
            String[] tmp = info.split(" ");
            String lang = tmp[0];
            String pos = tmp[1];
            String exp = tmp[2];
            String food = tmp[3];
            int score = Integer.parseInt(tmp[4]);
            String key = lang + pos + exp + food;
            List<Integer> list = scoreListMap.get(key);
            list.add(score);
        }
        for(String key : scoreListMap.keySet()) {
            Collections.sort(scoreListMap.get(key));
        }
    }
    
}