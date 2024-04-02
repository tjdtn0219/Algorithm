import java.util.*;

class Solution {
    
    static final String[] LANGS = {"cpp", "java", "python"};
    static final String[] POSITIONS = {"backend", "frontend"};
    static final String[] EXPERIENCES = {"junior", "senior"};
    static final String[] FOODS = {"chicken", "pizza"};
    
    HashMap<String, List<Integer>> scoreMap;
    
    public int[] solution(String[] info, String[] query) {
        init(info);
        return solve(query);
    }
    
    public void init(String[] infos) {
        scoreMap = new HashMap<>();
        makeScoreMap();
        fillScoreMap(infos);
        sortScoreMap();
    }
    
    public void makeScoreMap() {
        for(String lang : LANGS) {
            for(String position : POSITIONS) {
                for(String experience : EXPERIENCES) {
                    for(String food : FOODS) {
                        scoreMap.put(lang + position + experience + food, new ArrayList<>());
                    }
                }
            }
        }
    }
    
    public void fillScoreMap(String[] infos) {
        for(String info : infos) {
            String[] tmp = info.split(" ");
            String key = tmp[0] + tmp[1] + tmp[2] + tmp[3];
            int score = Integer.parseInt(tmp[4]);
            List<Integer> scoreList = scoreMap.get(key);
            scoreList.add(score);
            // System.out.println("key : " + key);
            // System.out.println("size : " + scoreMap.get(key).size());
        }
    }
    
    public void sortScoreMap() {
        for(String key : scoreMap.keySet()) {
            Collections.sort(scoreMap.get(key));
        }
    }
    
    public int[] solve(String[] queries) {
        int[] answer = new int[queries.length];
        for(int i=0; i<queries.length; i++) {
            String query = queries[i];
            answer[i] = makeKeyComb(query);
        }
        return answer;
    }
    
    public int makeKeyComb(String query) {
        List<String> keyList = new ArrayList<>();
        List<String> infoList = new ArrayList<>();
        String[] tmp = query.split(" and ");
        String lang = tmp[0];
        String position = tmp[1];
        String experience = tmp[2];
        String[] tmp2 = tmp[3].split(" ");
        String food = tmp2[0];
        int score = Integer.parseInt(tmp2[1]);
        
        infoList.add(lang);
        infoList.add(position);
        infoList.add(experience);
        infoList.add(food);
        // System.out.println("S : " + lang + " " + position + " " + experience + " " + food);
        dfs(0, "", keyList, infoList);
        int cnt = 0;
        for(String key : keyList) {
            cnt += scoreMap.get(key).size() - lowerBound(key, score);
        }
        return cnt;
    }
    
    public int lowerBound(String key, int target) {
        List<Integer> scoreList = scoreMap.get(key);
        int st = 0;
        int en = scoreList.size();
        
        while(st < en) {
            int mid = (st + en) / 2;
            if(target <= scoreList.get(mid)) en = mid;
            else st = mid + 1;
        }
        return st;
    }
    
    public void dfs(int k, String key, List<String> keyList, List<String> infoList) {
        if(k == 4) {
            keyList.add(key);
            // System.out.println("key : " + key);
            return ;
        }
        
        if(!infoList.get(k).equals("-")) {
            dfs(k+1, key + infoList.get(k), keyList, infoList);
        } else {
            if(k==0) {
                for(String s : LANGS) {
                    dfs(k+1, key + s, keyList, infoList);
                }
            } else if(k==1) {
                for(String s : POSITIONS) {
                    dfs(k+1, key + s, keyList, infoList);
                }
            } else if(k==2) {
                for(String s : EXPERIENCES) {
                    dfs(k+1, key + s, keyList, infoList);
                }
            } else {
                for(String s : FOODS) {
                    dfs(k+1, key + s, keyList, infoList);
                }
            }
        }
    }
}