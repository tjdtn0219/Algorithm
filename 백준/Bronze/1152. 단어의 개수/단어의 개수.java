import java.util.*;

public class Main{
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String str = in.nextLine();
        String stripStr = str.strip();

        if(stripStr.length() == 0) {
            System.out.println(0);
            return;
        }

        String[] arr = stripStr.split(" ");

        System.out.println(arr.length);
    }
}
