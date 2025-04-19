class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        // logs는 누적합으로 정리
        // 99:59:59는 359999 => 360000 
        
        int[] time_log = new int[360001];
        
        //누적합 진행 먼저, 스타팅과 끝나는지점+1에 포인트찍기
        for(String log:logs){
            String[] l = log.split("-");
            int start= ToInt(l[0]);
            int end = ToInt(l[1]);
            
            time_log[start]++;
            time_log[end]--;
        }
        
        int end= ToInt(play_time);
        int adv=ToInt(adv_time);
        int max_start= end-adv;
        
        //포인트를 기반으로 누적합 진행
        for(int i=1;i<time_log.length;i++){
            time_log[i]+=time_log[i-1];
        }
        
        
        long max=0;  
        long current=max;
        int start_point=0;
        
        for(int i=adv;i<=end;i++){
 
           
            current+=time_log[i]-time_log[i-adv];
            
            if(current>max){
                //광고 종료타이밍이 개구간이라 +1해야한다..
                start_point=i-adv+1;
                max=current;       
            }
            
        }   
        answer=ToTime(start_point);
      
        return answer;
    }
    
    public int ToInt(String time){
        
        String[] t = time.split(":");
        
        return Integer.parseInt(t[2])+
            Integer.parseInt(t[1])*60+
            Integer.parseInt(t[0])*60*60;
    }
    
    public String ToTime(int time){
        
        int hour= time/3600;
        String sh=String.valueOf(hour);
        if(hour<10) sh="0"+sh;
        
        
        time-=hour*3600;
        
        int min= time/60;
        time-=min*60;
        String mh=String.valueOf(min);
        
        if(min<10) mh="0"+mh;
        
        String ch=String.valueOf(time);
        if(time<10) ch="0"+ch;
        
        return sh+":"+mh+":"+ch;
        
    }
  
}
