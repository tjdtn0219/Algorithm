import java.util.*;

class Solution {
    
    List<HashSet<Character>> orderList;
    int[] course;
    List<Character> menuList;
    char[] comb;
    HashMap<Integer, List<String>> cntMap;
    int max;
    List<String> ansList;
    
    public String[] solution(String[] orders, int[] course) {
        init(orders, course);
        solve();
        Collections.sort(ansList);
        return ansList.toArray(new String[0]);
    }
    
    public void solve() {
        for(int cnt : course) {
            comb = new char[cnt];
            max = 0;
            cntMap = new HashMap<>();
            dfs(0, cnt, 0);
            if(max == 0) continue;
            for(String s : cntMap.get(max)) {
                ansList.add(s);
            }
        }   
    }
    
    public void dfs(int k, int cnt, int last) {
        if(k == cnt) {
            int orderCnt = getOrderCnt(comb);
            if(2 <= orderCnt && max <= orderCnt) {
                max = orderCnt;
                List<String> list = cntMap.getOrDefault(orderCnt, new ArrayList<>());
                String s = arrToStr(comb);
                list.add(s);
                cntMap.put(orderCnt, list);
            }
            return ;
        }
        
        for(int i=last; i<menuList.size(); i++) {
            comb[k] = menuList.get(i);
            dfs(k+1, cnt, i+1);
        }
    }
    
    public String arrToStr(char[] arr) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(arr);
        for(char c : arr) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public int getOrderCnt(char[] comb) {
        int cnt = 0;
        for(HashSet<Character> set : orderList) {
            boolean flag = true;
            for(char c : comb) {
                if(!set.contains(c)) {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }
        return cnt;
    }
    
    public void init(String[] orders, int[] course) {
        orderList = new ArrayList<>();
        this.course = course;
        HashSet<Character> menuSet = new HashSet<>();
        for(String order : orders) {
            HashSet<Character> orderSet = new HashSet<>();
            for(char c : order.toCharArray()) {
                menuSet.add(c);
                orderSet.add(c);
            }
            orderList.add(orderSet);
        }
        menuList = new ArrayList<>(menuSet);
        Collections.sort(menuList);
        ansList = new ArrayList<>();

    }
}