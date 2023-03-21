package Webtoon;

import java.util.Scanner;

public class WT_Function_delete {
	static Scanner sc = new Scanner(System.in);
	static Scanner sc1 = new Scanner(System.in);

	static void f_delete() {
		DataDAO dao = DataDAO.newInstance();

		System.out.println("DELETE 기능을 실행하였습니다.");
		System.out.println("삭제할 웹툰을 검색합니다.");
		System.out.print(">>");
		String input = sc.nextLine();

		String sql = "SELECT * FROM NCOMICS WHERE TITLE LIKE " + "'%" + input + "%'ORDER BY NO_COUNT";
		System.out.println(input + "의 정보입니다.");
		dao.rs_executeQuery(sql);

		System.out.println("작품의 번호를 입력하세요");
		System.out.print(">>");
		int input1 = sc.nextInt();


		sql = "SELECT * FROM NCOMICS WHERE NO_COUNT = "+ input1 ;
		dao.rs_executeQuery(sql);
		System.out.println("위 작품을 삭제 하시겠습니까? 1.네 2.아니오");
		System.out.print(">>");
		int input2 = sc.nextInt();

		if(input2==1) {
			sql = "DELETE FROM NCOMICS WHERE NO_COUNT ="+input1;
			dao.executeUpdate(sql);
		}else if(input2==2)return;
		else System.out.println("다시입력하세요");
	}

}
