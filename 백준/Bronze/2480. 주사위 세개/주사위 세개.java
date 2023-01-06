import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        int num3 = in.nextInt();
        
        int money = 0;
        
        if(num1 == num2 && num2 == num3){
            money = 10000+num1*1000;
        } else if(num1==num2 && num2!=num3){
            money = 1000+100*num1;
        } else if(num1==num3 && num1!=num2){
            money = 1000+100*num1;
        } else if(num2==num3 && num1!=num3){
            money = 1000+100*num2;
        } else{
            int max = num1;
            if(max<num2){
                max = num2;
            }
            if(max<num3){
                max = num3;
            }
            money = max*100;
        }
        System.out.println(money);
    }
}