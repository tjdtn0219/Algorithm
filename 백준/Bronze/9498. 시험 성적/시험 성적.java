import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int score = in.nextInt();
        String grade = "";
        
        if(90<=score && score<=100) {
            grade = "A";
        } else if(80<=score && score<=89) {
            grade = "B";
        } else if(70<=score && score<=79) {
            grade = "C";
        } else if(60<=score && score<=69) {
            grade = "D";
        } else {
            grade = "F";
        }
        System.out.println(grade);
    }
}