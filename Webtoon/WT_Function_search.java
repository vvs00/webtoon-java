package Webtoon;

import java.util.Scanner;

public class WT_Function_search {
	static Scanner sc = new Scanner(System.in);
	static Scanner sc1 = new Scanner(System.in);

	static void f_search() {
		System.out.println("검색기능을 실행 하였습니다.");
		System.out.println("======================================");
		System.out.println("1.요일별로 검색하기");
		System.out.println("2.제목으로 검색하기");
		System.out.println("3.작가이름 검색하기");
		System.out.println("4.장르로 검색하기");
		System.out.println("5.전체 웹툰 조회하기");
		System.out.println("9.메인으로 돌아가기");
		System.out.println("======================================");
		System.out.print(">>");

		int input = sc.nextInt();
		if (input == 1)
			f_DaySearch();
		else if (input == 2 || input == 3 || input == 4) {
			f_Searchb(input);
		} else if (input == 5) {
			f_Searchc(input);
		} else if (input == 9)
			return;
		else
			System.out.println("다시입력하세요!");
	}

	static void f_DaySearch() {

		DataDAO dao = DataDAO.newInstance();

		System.out.println("검색하고싶은 요일을 입력하세요");
		System.out.println("======================================");
		System.out.println("1.월요일");
		System.out.println("2.화요일");
		System.out.println("3.수요일");
		System.out.println("4.목요일");
		System.out.println("5.금요일");
		System.out.println("6.토요일");
		System.out.println("7.일요일");
		System.out.println("======================================");
		System.out.print(">>");

		int input = sc.nextInt();
		String sql_input = "";
		if (input == 1)
			sql_input = "월요일";
		else if (input == 2)
			sql_input = "화요일";
		else if (input == 3)
			sql_input = "수요일";
		else if (input == 4)
			sql_input = "목요일";
		else if (input == 5)
			sql_input = "금요일";
		else if (input == 6)
			sql_input = "토요일";
		else if (input == 7)
			sql_input = "일요일";
		else
			System.out.println("다시입력하세요");

		String sql = "SELECT * FROM NCOMICS WHERE DINFO=" + "'" + sql_input + "'  ORDER BY NO_COUNT";
//		String sql = "SELECT * FROM NCOMICS WHERE DINFO LIKE "+"'%"+input+"%'";

		// Statement 생성 후 실행할 쿼리정보 등록
		System.out.println(sql_input + "에 연재되는 웹툰을 검색하셨습니다.");
		dao.rs_executeQuery(sql);
	}

	static void f_Searchb(int select) {
		String a = "";
		String b = "";
		DataDAO dao = DataDAO.newInstance();
		if (select == 2) {
			a = "제목";
			b = "TITLE";
		} else if (select == 3) {
			a = "작가";
			b = "WRITER";
		} else if (select == 4) {
			a = "장르";
			b = "GENRE";
		} else {
			System.out.println("다시입력하세요");
			f_search();
		}
		System.out.println("검색하고싶은 " + a + "을 입력하세요 (1글자 이상)");
		System.out.println("======================================");
		System.out.print(">>");

		String input = sc.next();
		String sql = "SELECT * FROM NCOMICS WHERE " + b + " LIKE " + "'%" + input + "%'";

		System.out.println(input + "이 포함된 " + a + "제목을 검색합니다.");
		dao.rs_executeQuery(sql);
	}

	static void f_Searchc(int select) {
		DataDAO dao = DataDAO.newInstance();
		System.out.println("전체조회를 선택하셨습니다.");
		System.out.println("======================================");
		String sql = "SELECT * FROM NCOMICS ORDER BY NO_COUNT";
		dao.rs_executeQuery(sql);
	}

}
