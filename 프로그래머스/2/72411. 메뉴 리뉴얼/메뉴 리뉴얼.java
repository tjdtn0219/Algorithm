import java.util.*;

class Solution {
    
    List<String> answer;
    List<Character> menuList;
    HashMap<Integer, HashSet<Character>> orderMap;
    int[] courses;
    char[] comb;
    HashMap<Integer, Pair> pairMap;
    int maxCnt;
    HashMap<Integer, List<String>> cntMap;
    
    public String[] solution(String[] orders, int[] course) {
        init(orders, course);
        solve();
        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }
    
    public void solve() {
        for(int cnt : courses) {
            comb = new char[cnt];
            maxCnt = 0;
            cntMap = new HashMap<>();
            makeComb(0, cnt, 0);
            // System.out.println("maxCnt : " + maxCnt);
            if(maxCnt == 0) {
                continue;
            }
            for(String s : cntMap.get(maxCnt)) {
                answer.add(s);
            }
        }
        // printAnswer();
        
    }
    
    public void printAnswer() {
        System.out.println("ANSWER : ");
        for(String s : answer) {
            System.out.println("s : " + s);
        }
    }
    
    public void makeComb(int k, int limit, int startIdx) {
        if(k == limit) {
            // printComb();
            Pair pair = getPair(comb);
            if(pair == null) {
                return ;
            }
            if(pair.cnt >= maxCnt) {
                List<String> list = cntMap.getOrDefault(pair.cnt, new ArrayList<>());
                list.add(pair.menu);
                cntMap.put(pair.cnt, list);
                maxCnt = pair.cnt;
            }
            return ;
        }
        
        for(int i=startIdx; i<menuList.size(); i++) {
            comb[k] = menuList.get(i);
            makeComb(k+1, limit, i+1);
        }
        
    }
    
    public Pair getPair(char[] comb) {
        int cnt = 0;
        for(int key : orderMap.keySet()) {
            if(isOrder(orderMap.get(key), comb)) {
                cnt++;
            }
        }
        if(cnt >= 2) {
            return new Pair(arrToStr(comb), cnt);
        } else {
            return null;
        }
    }
    
    public boolean isOrder(HashSet<Character> orderSet, char[] comb) {
        for(char c : comb) {
            if(!orderSet.contains(c)) {
                return false;
            }
        }
        return true;
    }
    
    public String arrToStr(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for(char c : arr) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    private void printComb() {
        StringBuilder sb = new StringBuilder();
        for(char c : comb) {
            sb.append(c + " ");
        }
        System.out.println(sb);
    }
    
    public void init(String[] orders, int[] courses) {
        this.answer = new ArrayList<>();
        this.orderMap = new HashMap<>();
        this.courses = courses;
        HashSet<Character> menuSet = new HashSet<>();
        int idx = 0;
        for(String order : orders) {
            HashSet<Character> tmpSet = new HashSet<>();
            for(char c : order.toCharArray()) {
                menuSet.add(c);
                tmpSet.add(c);
            }
            orderMap.put(idx++, tmpSet);
        }
        this.menuList = new ArrayList<>(menuSet);
        
    }
}

class Pair {
    String menu;
    int cnt;
    
    public Pair(String menu, int cnt) {
        this.menu = menu;
        this.cnt = cnt;
    }
}