import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 테스트 케이스의 개수 선언
		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			String input = sc.next();

			//String.valueOf() input.charAt(0)인 char타입을 String 데이터로 형변환
			// input이 가리키고 있는 문자열에서 0번째에 있는 문자를 char타입으로 변환
			String first = String.valueOf(input.charAt(0));
			String end = String.valueOf(input.charAt(input.length() - 1));
			System.out.println(first + end);
		}
		sc.close();
	}
}