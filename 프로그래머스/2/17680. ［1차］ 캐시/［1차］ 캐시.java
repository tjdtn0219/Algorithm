import java.util.*;

class Solution {
    
    int size;
    String[] cities;
    int ans;
    
    public int solution(int cacheSize, String[] cities) {
        init(cacheSize, cities);
        solve();
        return ans;
    }
    
    public void solve() {
        if(size == 0) {
            ans = cities.length * 5;
            return ;
        }
        LinkedList<String> list = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        for(String city : cities) {
            if(set.contains(city)) {
                // Cache Hit
                ans += 1;
                int removeIdx = -1;
                for(int i=0; i<list.size(); i++) {
                    if(city.equals(list.get(i))) {
                        removeIdx = i;
                        break;
                    }
                }
                list.addFirst(list.remove(removeIdx));
            } else {
                // Cache Miss
                ans += 5;
                if(set.size() < size) {
                    // 포화 X
                    set.add(city);
                    list.addFirst(city);
                } else {
                    // 포화 O
                    String removedCity = list.pollLast();
                    set.remove(removedCity);
                    set.add(city);
                    list.addFirst(city);
                }
                
            }
        }
    }
    
    public void init(int cacheSize, String[] cities) {
        this.size = cacheSize;
        this.cities = new String[cities.length];
        for(int i=0; i<cities.length; i++) {
            this.cities[i] = cities[i].toLowerCase();
        }
    }
}