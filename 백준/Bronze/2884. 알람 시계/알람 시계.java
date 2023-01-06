import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int hour = in.nextInt();
        int min = in.nextInt();
        
        int minus = 45;
        
        int new_hour;
        int new_min;
        
        if(min - minus < 0){
            new_hour = hour - 1;
            new_min = min - minus + 60;
        } else{
            new_hour = hour;
            new_min = min - minus;
        }
        if(new_hour < 0){
            new_hour += 24;
        }
        System.out.println(new_hour + " " + new_min);
    }
}