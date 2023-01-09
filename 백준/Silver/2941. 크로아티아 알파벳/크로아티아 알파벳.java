import java.util.*;

public class Main{
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String str = in.nextLine();
        int len = str.length();

//        System.out.println(str.charAt(0));
//        if(str.charAt(0) == 'l'){
//            System.out.println("tt");
//        }

        int cnt = 0;
        for(int i=0; i<len; i++){

            cnt ++;
            if(str.charAt(i) == 'c' && (i+1) < str.length()){
                if(str.charAt(i+1) == '=' || str.charAt(i+1) == '-'){
                    i++;
                    continue;
                }
            }
            else if(str.charAt(i) == 'd' && (i+1) < len){
                if(str.charAt(i+1) == '-'){
                    i++;
                    continue;
                } else if(str.charAt(i+1) == 'z' && (i+2) < len){
                    if(str.charAt(i+2) == '='){
                        i += 2;
                        continue;
                    }
                }
            }
            else if(str.charAt(i) == 'l' && (i+1) < len){
                if(str.charAt(i+1) == 'j'){
                    i++;
                    continue;
                }
            }
            else if(str.charAt(i) == 'n' && (i+1) < len){
                if(str.charAt(i+1) == 'j'){
                    i++;
                    continue;
                }
            }
            else if(str.charAt(i) == 's' && (i+1) < len){
                if(str.charAt(i+1) == '='){
                    i++;
                    continue;
                }
            }
            else if(str.charAt(i) == 'z' && (i+1) < len){
                if(str.charAt(i+1) == '='){
                    i++;
                    continue;
                }
            }
        }
        System.out.println(cnt);

    }
}
