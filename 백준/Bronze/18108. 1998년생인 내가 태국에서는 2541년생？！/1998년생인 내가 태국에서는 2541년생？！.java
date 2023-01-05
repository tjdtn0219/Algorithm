import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int byear = in.nextInt();
        int syear = byear - 543;
        
        System.out.println(syear);
    }
}