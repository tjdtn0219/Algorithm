import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int year = in.nextInt();
        int is_yoon = 0;
        
        if(year%4==0){
            is_yoon = 1;
            if(year%100==0){
                is_yoon = 0;
            }
            if(year%400==0) {
                is_yoon = 1;
            }
        }
        System.out.println(is_yoon);
        
    }
}