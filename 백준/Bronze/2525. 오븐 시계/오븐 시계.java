import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int hour = in.nextInt();
        int min = in.nextInt();
        int oven_min = in.nextInt();
        
        int num = min + oven_min;
        hour += num/60;
        min = num%60;
        
        if(hour>=24){
            hour %= 24;
        }
        
        System.out.println(hour + " " + min);
        
    }
}