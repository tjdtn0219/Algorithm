import java.util.Scanner;



public class Main {
	//주어진 문자열이 회문인지 확인하는 함수
	public static boolean isPaindrome(char[] s, int l, int r) {
		while(l<=r) {
			if(s[l]!=s[r]) return false;
			l++;
			r--;
		}
		return true;
	}
    public static void main(String[] args)  {
    	
    	Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	
    	
    	while(n-->0) {
    		char[] s = sc.next().toCharArray();
    		//회문이면 0
    		int l=0,r=s.length-1;
    		int answer = 0;
    		while(l<=r) {
    			//유사회문 혹은 회문이 아니면
    			if(s[l]!=s[r]) {
                //현재 l이나 r이 가리키는 문자를 하나씩 제거했을때 회문이면 유사회문
    				if(isPaindrome(s, l, r-1) || isPaindrome(s, l+1,r)) answer =1;
    				else answer=2;//회문 아님
    				break;//결과가 나왔으니 빠져나가기
    			}
    			l++;
    			r--;
    		}
    		System.out.println(answer);
    		
    	}

     }
    
}