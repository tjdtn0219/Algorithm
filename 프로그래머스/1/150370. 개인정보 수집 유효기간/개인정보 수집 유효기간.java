import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        List<Integer> ansList = new ArrayList<>();
        Date curDate = fromString(today);
      
        HashMap<Character, Integer> typeMap = termsToMap(terms);
        
        for(int i=0; i<privacies.length; i++) {
            String privacy = privacies[i];
            String dateStr = privacy.split(" ")[0];
            char type = privacy.split(" ")[1].charAt(0);
            // System.out.println(dateStr + " " + type);
            int addedMon = typeMap.get(type);
            
            Date date = fromString(dateStr);
            addMonth(date, addedMon);
            // System.out.println("date : " + date.y +"." + date.m + "." + date.d);
            // System.out.println("curDate : " + curDate.y +"." + curDate.m + "." + curDate.d);
            if(compareDate(date, curDate) < 0) {
                ansList.add(i+1);
            }
        }
        
        int[] ans = new int[ansList.size()];
        for(int i=0; i<ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        
        return ans;
    }
    
    public int compareDate(Date d1, Date d2) {
        if(d1.y == d2.y) {
            if(d1.m == d2.m) {
                if(d1.d == d2.d) {
                    return 0;
                } else {
                    return d1.d - d2.d;
                }
            } else {
                return d1.m - d2.m;
            } 
        } else {
            return d1.y - d2.y;
        }
    }
    
    public HashMap<Character, Integer> termsToMap(String[] terms) {
        HashMap<Character, Integer> hmap = new HashMap<>();
        for(String term : terms) {
            String[] strings = term.split(" ");
            char type = strings[0].charAt(0);
            int mon = Integer.parseInt(strings[1]);
            hmap.put(type, mon);
        }
        return hmap;
    }
    
    public void addMonth(Date date, int addedMon) {
        //day
        if(date.d==1) {
            date.d = 28; date.m--;
        } else {
            date.d--;
        }
        
        //23.5 + 20 -> 25년도.1월
        //month
        if(date.m + addedMon <= 12) {
            date.m += addedMon;
        } else {
            int addedYear = (date.m + addedMon) / 12;
            int mon = (date.m + addedMon) % 12;
            if(mon == 0) {  // <------- Point!!
                addedYear--;
                mon = 12;
            }
            date.y += addedYear;
            date.m = mon;
        }
    }
    
    public Date fromString(String s) {
        String[] strings = s.split("\\.");
        int y = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int d = Integer.parseInt(strings[2]);
        return new Date(y, m, d);
    } 
}

class Date {
    int y;
    int m;
    int d;
    public Date(int y, int m, int d) {
        this.y = y;
        this.m = m;
        this.d = d;
    }
}