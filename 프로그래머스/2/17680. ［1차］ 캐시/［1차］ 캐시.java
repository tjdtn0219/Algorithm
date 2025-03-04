import java.util.*;

class City {
    int idx;
    String name;
    public City(int idx, String name) {
        this.idx = idx;
        this.name = name;
    }
}

class Solution {
    
    int max;
    String[] cities;
    int ans;
    
    public int solution(int cacheSize, String[] cities) {
        init(cacheSize, cities);
        solve();
        return ans;
    }
    
    private void printCache(HashMap<String, Integer> cache) {
        StringBuilder sb = new StringBuilder();
        sb.append("size : " +  cache.keySet().size());
        for(String city : cache.keySet()) {
            sb.append(" ").append(city);
        }
        System.out.println(sb);
    }
    
    public void solve() {
        if(max == 0) {
            ans = cities.length * 5;
            return ;
        }
        HashMap<String, Integer> cache = new HashMap<>();
        int idx = 0;
        for(String city : cities) {
            if(cache.keySet().contains(city)) {
                // cache Hit
                ans += 1;
                cache.put(city, idx);
            } else {
                // cache Miss
                if(cache.keySet().size() < max) {
                    cache.put(city, idx);
                    ans += 5;
                } else {
                    String removeCity = "";
                    int minIdx = Integer.MAX_VALUE;
                    for(String name : cache.keySet()) {
                        if(cache.get(name) < minIdx) {
                            minIdx = cache.get(name);
                            removeCity = name;
                        }
                    }
                    cache.remove(removeCity);
                    cache.put(city, idx);
                    ans += 5;
                }
                
            }
            // printCache(cache);
            // System.out.println("ans : " + ans);
            idx++;
        }
    }
    
    public void init(int cacheSize, String[] cities) {
        this.max = cacheSize;
        this.cities = cities;
        this.cities = new String[cities.length];
        for(int i=0; i<cities.length; i++) {
            this.cities[i] = cities[i].toUpperCase();
        }
    }
}