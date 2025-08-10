import java.awt.Point;
import java.util.*;

class Solution {
    
    static final String[] LANG = {"cpp", "java", "python"};
    static final String[] POS = {"backend", "frontend"};
    static final String[] EXP = {"junior", "senior"};
    static final String[] FOOD = {"chicken", "pizza"};
    
    HashMap<String, List<Integer>> scoreListMap;
    String[] infos;
    String[] queries;
    int[] answer;
    
    public int[] solution(String[] info, String[] query) {
        init(info, query);
        solve();
        return answer;
    }
    
    public void solve() {
        int idx = 0;
        for(String q : queries) {
            List<Pair> pairList = getPairs(q);
            int sum = 0;
            for(Pair pair : pairList) {
                sum += doQuery(pair);
                // System.out.println("sum : " + sum);
            }
            // System.out.println("---------");
            answer[idx++] = sum;
        }
    }
    
    public int doQuery(Pair pair) {
        String key = pair.key;
        int score = pair.score;
        // System.out.println("key : " + key + ", score : " + score);
        List<Integer> scoreList = scoreListMap.get(key);
        // System.out.println("scoreList : " + scoreList);
        return getOverCnt(scoreList, score);
    }
    
    public int getOverCnt(List<Integer> list, int tg) {
        int st = 0;
        int en = list.size();
        
        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= list.get(mid)) en = mid;
            else st = mid + 1;
        }

        return list.size() - st;
    }
    
    public List<Pair> getPairs(String q) {
        String[] tmp = q.split(" ");
        String lang = tmp[0];
        String pos = tmp[2];
        String exp = tmp[4];
        String food = tmp[6];
        int score = Integer.parseInt(tmp[7]);
        List<String> langList = new ArrayList<>();
        List<String> posList = new ArrayList<>();
        List<String> expList = new ArrayList<>();
        List<String> foodList = new ArrayList<>();
        if(lang.equals("-")) {
            Collections.addAll(langList, LANG);
        } else {
            langList.add(lang);
        }
        if(pos.equals("-")) {
            Collections.addAll(posList, POS);
        } else {
            posList.add(pos);
        }
        if(exp.equals("-")) {
            Collections.addAll(expList, EXP);
        } else {
            expList.add(exp);
        }
        if(food.equals("-")) {
            Collections.addAll(foodList, FOOD);
        } else {
            foodList.add(food);
        }
        
        List<Pair> pairList = new ArrayList<>();
        for(String l : langList) {
            for(String p : posList) {
                for(String e : expList) {
                    for(String f : foodList) {
                        String key = l + p + e + f;
                        pairList.add(new Pair(key, score));
                    }
                }
            }
        }
        return pairList;
    }
    
    public void init(String[] infos, String[] queries) {
        this.queries = queries;
        this.answer = new int[queries.length];
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
        
        for(String info : infos) {
            Pair pair = parseInfo(info);
            // System.out.println("key : " + pair.key + " , score : " + pair.score);
            scoreListMap.get(pair.key).add(pair.score);
        }
        
        for(String key : scoreListMap.keySet()) {
            Collections.sort(scoreListMap.get(key));
        }
        
    }
    
    public Pair parseInfo(String info) {
        String[] tmp = info.split(" ");
        String lang = tmp[0];
        String pos = tmp[1];
        String exp = tmp[2];
        String food = tmp[3];
        int score = Integer.parseInt(tmp[4]);
        return new Pair(lang + pos + exp + food, score);
    }
}

class Pair {
    String key;
    Integer score;
    public Pair(String key, Integer score) {
        this.key = key;
        this.score = score;
    }
}
