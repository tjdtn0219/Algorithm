import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int C = in.nextInt();
        
        List<Float> percentList = new ArrayList<>();
        
        for(int i=0; i<C; i++){
            int N = in.nextInt();
            List<Integer> scoreList = new ArrayList<>();
            for(int j=0; j<N; j++){
                int score = in.nextInt();
                scoreList.add(score);
            }
            percentList.add(countPercent(scoreList));
        }
        for(float percent : percentList){
            System.out.printf("%.3f%%\n", percent);
        }
    }
    
    public static float countPercent(List<Integer> scoreList){
        int avg;
        int sum = 0;
        int over_avg_cnt = 0;
        for(int score : scoreList){
            sum += score;
        }
        avg = sum/(scoreList.size());
        
        for(int score : scoreList){
            if(score > avg) {
                over_avg_cnt++;
            }
        }
        float over_percent = (float) over_avg_cnt / scoreList.size();
        return 100*over_percent;
    }
}
