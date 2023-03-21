package Webtoon;

import java.util.Scanner;

public class WT_Function_reset {
	static Scanner sc = new Scanner(System.in);

	static void reset() {

		try {
			DataDAO dao = DataDAO.newInstance();
			String Update = dao.upDateCheck();

			if (!Update.equals("0")) {
				System.out.println("마지막 업데이트 : " + Update);
				System.out.println("(기존 데이터가 있을경우 모두 삭제되고 새롭게 생성됩니다.)");
			} else {
				System.out.println("자료가 없어 업데이트가 꼭 필요합니다.");
			}
			System.out.println("DB 정보를 최신화 하시겠습니까? (1. 네 / 2. 아니오)");
			System.out.println(">>");

			int input = sc.nextInt();

			String sql1 = "Create table NCOMICS(no_count NUMBER(5) Primary key,"
					+ "title nvarchar2(50),writer nvarchar2(200),dinfo nvarchar2(100),"
					+ "genre nvarchar2(200),recent nvarchar2(100),info nvarchar2(1000))";

			String sql2 = "CREATE SEQUENCE seq_Ncomics INCREMENT BY 1 START WITH 1 MAXVALUE 600 NOORDER";

			if (input == 1) {
				if (Update.equals("0")) {
					System.out.println("테이블과 시퀀스를 생성중입니다.");
					dao.CreateUpdateTimeTable();
					dao.excute(sql1);
					dao.dropSequnce();
					dao.executeUpdate(sql2);
					System.out.println("테이블과 시퀀스 생성완료 ");
				} else {
					System.out.println("기존 데이터 초기화 중입니다.");
					sql1 = "DELETE FROM NCOMICS";
					dao.executeUpdate(sql1);
					dao.dropSequnce();
					dao.executeUpdate(sql2);
					System.out.println("기존 데이터 초기화 되었습니다.");
				}

				System.out.println("웹툰 정보를 추가합니다================");
				WT_Crawling_List wc = new WT_Crawling_List();
				dao.UpdateSet();
				wc.webtoon_craw();
			} else if (input == 2) {
				System.out.println("메인메뉴로 돌아갑니다.");
				return;
			}
			else {
				System.out.println("입력오류입니다. 다시입력하세요!");
				reset();
			}
		} catch (Exception e) {
			System.out.println("입력오류입니다.");
			reset();
		}
	}
}
