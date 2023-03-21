package Webtoon;

import java.util.Scanner;

public class WT_Function_insert {
	static Scanner sc = new Scanner(System.in);
	static Scanner sc1 = new Scanner(System.in);

	static void f_update() {
		System.out.println("Update기능을 실행하였습니다.");
		System.out.println("======================================");
		System.out.println("1.추가기능");
		System.out.println("2.수정기능");
		System.out.println("3.메인으로 돌아가기");
		System.out.println("======================================");
		System.out.print(">>");
		int input = sc.nextInt();
		if (input == 1)
			f_insert();
		else if (input == 2) {f_Revise();
		} else if (input == 3)
			return;
		else {
			System.out.println("입력오류입니다. 다시 입력하세요");
			f_update();
		}
	}

	static void f_insert() {
		try {
			DataDAO dao = DataDAO.newInstance();

			System.out.println("추가 기능을 실행합니다.");
			System.out.println("추가하시려는 작품의 제목을 입력하세요");
			System.out.print(">>");
			String input1 = sc.next();
			System.out.println("추가하시려는 작품의 작가를 입력하세요");
			System.out.print(">>");
			String input2 = sc.next();
			System.out.println("추가하시려는 작품의 연재요일을 입력하세요");
			System.out.print(">>");
			String input3 = sc.next();
			System.out.println("추가하시려는 작품의 장르를 입력하세요");
			System.out.print(">>");
			String input4 = sc.next();
			System.out.println("추가하시려는 작품의 마지막화를 입력하세요");
			System.out.print(">>");
			String input5 = sc1.next();
			System.out.println("추가하시려는 작품의 스토리를 입력하세요");
			System.out.print(">>");
			String input6 = sc1.next();

			String sql = "INSERT INTO NCOMICS VALUES (seq_Ncomics.NEXTVAL, '" + input1 + "','" + input2 + "','" + input3
					+ "','" + input4 + "','" + input5 + "','" + input6 + "')";
			dao.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("다시입력하세요");
			e.printStackTrace();
		}
	}

	static void f_Revise() {
		DataDAO dao = DataDAO.newInstance();
		System.out.println("수정 기능을 실행합니다.");
		System.out.println("번호검색을 합니다. 수정하려는 웹툰의 제목을 입력하세요");
		System.out.print(">>");
		String input1 = sc.next();

		String sql = "SELECT * FROM NCOMICS WHERE TITLE LIKE " + "'%" + input1 + "%'";
		System.out.println(input1 + "의 정보입니다.");
		dao.rs_executeQuery(sql);

		System.out.println("작품의 번호를 입력하세요");
		System.out.print(">>");
		int input3 = sc.nextInt();

		System.out.println("수정하시려는 작품의 정보를 선택하세요");
		System.out.println("1. 작품의 제목");
		System.out.println("2. 작품의 작가");
		System.out.println("3. 작품의 연재요일");
		System.out.println("4. 작품의 장르");
		System.out.println("5. 작품의 마지막화");
		System.out.println("6. 작품의 스토리");
		System.out.print(">>");
		int input2 = sc.nextInt();
		String a = "";
		String b = "";
		if (input2 == 1) {a = "TITLE"; b= "제목";}
		else if (input2 == 2) {a = "WRITER"; b ="작가";}
		else if (input2 == 3) {a = "DINFO"; b="연재요일";}
		else if (input2 == 4) {a = "GENRE"; b="장르";}
		else if (input2 == 5) {a = "RECENT";b="마지막화";}
		else if (input2 == 6) {a = "INFO";b="스토리";}
		else {
			System.out.println("다시입력하세요");
			f_Revise();
		}
		System.out.println(b + "를 선택하셨습니다.");
		System.out.println("변경할 정보를 입력하세요");
		System.out.print(">>");
		String input4 = sc1.nextLine();

		String sql1 = "UPDATE NCOMICS SET "+a+" = '" + input4 +"'WHERE NO_COUNT ="+input3;
		dao.executeUpdate(sql1);
		System.out.println("수정되었습니다.");
	}

}
