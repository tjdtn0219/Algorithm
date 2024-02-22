class Solution {
    
    int[] bookTime;
    
    public int solution(String[][] book_time) {
        int answer = 0;
        init();
        answer = solve(book_time);
        // printBookTime();
        return answer;
    }
    
    public void init() {
        bookTime = new int[26*60];
    }
    
    public int convertToInt(String time) {
        String[] tmp = time.split(":");
        int h = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        return 60*h + m;
    }
    
    public int solve(String[][] bookInfos) {
        for(String[] bookInfo : bookInfos) {
            int startTime = convertToInt(bookInfo[0]);
            int endTime = convertToInt(bookInfo[1]);
            int cleanTime = 10;
            // System.out.println("st : " + startTime + " en : " + endTime);
            fillBookTime(startTime, endTime + cleanTime);
        }
        return findMax();
    }
    
    public void printBookTime() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<bookTime.length; i++) {
            sb.append(bookTime[i] + " ");
        }
        System.out.println(sb);
    }
    
    public int findMax() {
        int max = 0;
        for(int i=0; i<bookTime.length; i++) {
            max = Math.max(max, bookTime[i]);
        }
        return max;
    }
    
    public void fillBookTime(int start, int end) {
        for(int i=start; i<end; i++) {
            bookTime[i]++;
        }
    }
    
    
}