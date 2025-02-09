import java.util.*;

class Solution {
    
    int[] fees;
    HashMap<String, Integer> parkMap;
    HashMap<String, Integer> timeMap;
    
    public int[] solution(int[] fees, String[] records) {
        init(fees, records);
        return solve();
    }
    
    public int[] solve() {
        HashMap<String, Integer> feeMap = new HashMap<>();
        for(String carNum : timeMap.keySet()) {
            int time = timeMap.get(carNum);
            int sum = fees[1];
            if(time > fees[0]) {
                time -= fees[0];
                sum += (time / fees[2]) * fees[3];
                if(time % fees[2] != 0) {
                    sum += fees[3];
                }
            }
            // System.out.println("car : " + carNum + ", sum : " + sum);
            feeMap.put(carNum, sum);
        }
        List<String> sortedList = new ArrayList<>(feeMap.keySet());
        List<Integer> ansList = new ArrayList<>();
        Collections.sort(sortedList);
        for(String carNum : sortedList) {
            ansList.add(feeMap.get(carNum));
        }
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }   
    
    public void init(int[] fees, String[] records) {
        this.fees = fees;
        initTimeMap(records);
    }
    
    public void initTimeMap(String[] records) {
        this.parkMap = new HashMap<>();
        this.timeMap = new HashMap<>();
        for(String record : records) {
            String[] tmp = record.split(" ");
            int time = getTimeInt(tmp[0]);
            String carNum = tmp[1];
            if(tmp[2].equals("IN")) {
                parkMap.put(carNum, time);
            } else {
                int inTime = parkMap.get(carNum);
                parkMap.remove(carNum);
                timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + (time - inTime));
            }
        }
        int lastTime = getTimeInt("23:59");
        for(String carNum : parkMap.keySet()) {
            int inTime = parkMap.get(carNum);
            timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + (lastTime - inTime));
        }
    }
    
    public int getTimeInt(String time) {
        String[] tmp = time.split(":");
        int h = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        return h*60 + m;
    }
}