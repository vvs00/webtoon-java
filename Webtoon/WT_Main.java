package Webtoon;

import java.util.Scanner;

public class WT_Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("======================================");
			System.out.println("C   0.CREATE 웹툰 정보 생성 및 최신화     W");
			System.out.println("R   1.READ 웹툰 검색                   O");
			System.out.println("U   2.UPDATE 웹툰 추가/수정             O");
			System.out.println("D   3.DELETE 웹툰 삭제                 O");
			System.out.println("6   9.EXIT 프로그램 종료                O");
			System.out.println("======================================");
			System.out.print(">>");
			int input = sc.nextInt();

			if(input == 0) WT_Function_reset.reset();
			if(input == 1) WT_Function_search.f_search();
			else if(input == 2)	WT_Function_insert.f_update();
			else if(input == 3) WT_Function_delete.f_delete();
			else if(input == 9) {System.out.println("프로그램을 종료합니다."); System.exit(0);}
			else System.out.println("다시입력하세요!");
		}
	}
}