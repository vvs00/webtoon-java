package Webtoon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DataDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;

	private static DataDAO dao;
	private static String update = "0";

	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "scott";
	private final String pwd = "1234";

	private void DataDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DataDAO newInstance() {
		if (dao == null)
			dao = new DataDAO();

		return dao;
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("-----------DriverManager Connected-----------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disConnection() {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			System.out.println("---------DriverManager DisConnected---------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void wtListInsert(WT_VO w) { // 크롤링 후 만들어진 리스트 인서트
		try {
			// 1. DB연결
			getConnection();

			// 2. sql문 작성
			String sql = "insert into Ncomics values(seq_Ncomics.NEXTVAL, ?,?,?,?,?,?)";

			// 3. 오라클로 sql문장 전송
			pstmt = conn.prepareStatement(sql);

//		   	4. ? 값 저장
			pstmt.setString(1, w.getTitle());
			pstmt.setString(2, w.getWriter());
			pstmt.setString(3, w.getDinfo());
			pstmt.setString(4, w.getGenre());
			pstmt.setString(5, w.getRecent());
			pstmt.setString(6, w.getInfo());

			// 5. 전송된 값을 커밋 또는 업데이트
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}

	}

	public void executeUpdate(String input) {
		try {
			// 1. DB연결
			getConnection();

			// 2. sql문 작성
			String sql = input;

			// 3. 오라클로 sql문장 전송
			pstmt = conn.prepareStatement(sql);

			// 4. 전송된 값을 커밋 또는 업데이트
			int res = pstmt.executeUpdate();
			if (res > 0)
				System.out.println("Commit");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}

	}

	public void excute(String input1) {
		try {
			// 1. DB연결
			getConnection();

			// 2. sql문 작성
			String sql1 = input1;

			// 3. 오라클로 sql문장 전송
			pstmt = conn.prepareStatement(sql1);
			pstmt.execute();
			System.out.println("Commit");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disConnection();
		}

	}

	public void dropSequnce() {
		try {
			// 1. DB연결
			getConnection();

			// 2. sql문 작성
			String sql = "DROP SEQUENCE seq_Ncomics";

			// 3. 오라클로 sql문장 전송
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("시퀀스 삭제완료");

		} catch (SQLException e) {
			System.out.println("시퀀스가 없습니다.");
		} finally {
			disConnection();
		}

	}

	public void rs_executeQuery(String input) {
		try {
			String sql = input;
			// 1. DB연결
			getConnection();

			// 2. 결과 담을 ResultSet 생성 후 결과 담기
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 3. 결과를 담을 ArrayList 생성
			ArrayList<WT_VO> list = new ArrayList<>();

			// 4. ResultSet에 담긴 결과를 ArrayList에 담기
			while (rs.next()) {
				WT_VO w = new WT_VO();
				w.setNum(rs.getInt("NO_COUNT"));
				w.setTitle(rs.getString("TITLE"));
				w.setWriter(rs.getString("WRITER"));
				w.setDinfo(rs.getString("DINFO"));
				w.setGenre(rs.getString("GENRE"));
				w.setRecent(rs.getString("RECENT"));
				w.setInfo(rs.getString("INFO"));
				list.add(w);
			}
			// 5. 결과물 출력
			System.out.println();
			System.out.println("----------------------------------------------------------------");
			for (WT_VO element : list) {
				System.out.print(element.getNum() + " ");
				System.out.printf("제목 : %-30s", element.getTitle());
				System.out.printf("작가 : %-17s", element.getWriter());
				System.out.printf("요일 : %-5s", element.getDinfo());
				System.out.printf("장르 : %-10s", element.getGenre());
				System.out.printf("최신화 : %-25s", element.getRecent());
				System.out.printf("소개 : %-200s", (element.getInfo().substring(0, 20)));
				System.out.println();
			}
			System.out.println("----------------------------------------------------------------");

		} catch (SQLException e) {
			System.out.println("READ 오류");

		} finally {
			disConnection();
		}

	}

	public void CreateUpdateTimeTable() {
		String sql = "CREATE TABLE WUPDATE(no NUMBER(5) Primary key,UPDATETIME nvarchar2(50))";
		try {
			// 1. DB연결
			getConnection();
			// 3. 오라클로 sql문장 전송
			pstmt = conn.prepareStatement(sql);

			// 4. 전송된 값을 커밋 또는 업데이트
			pstmt.execute();
			System.out.println("업데이트 테이블 생성 완료");
			String sql1 = "insert into WUPDATE values('0','" + update + "')";
			pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			System.out.println("업데이트 테이블 초기화 완료");
		} catch (SQLException e) {
			System.out.println("업데이트 테이블이 이미 있음!");
		} finally {
			disConnection();
		}
	}

	public void CreateNCOMICSTable() {
		String sql = "CREATE TABLE WUPDATE(no NUMBER(5) Primary key,UPDATETIME nvarchar2(50))";
		try {
			// 1. DB연결
			getConnection();
			// 3. 오라클로 sql문장 전송
			pstmt = conn.prepareStatement(sql);

			// 4. 전송된 값을 커밋 또는 업데이트
			pstmt.execute();
			System.out.println("NCOMICS 테이블 생성완료!");
		} catch (SQLException e) {
			System.out.println("NCOMICS 테이블이 이미 있음!");
		} finally {
			disConnection();
		}
	}

	public String upDateCheck() {
		try {
//			CreatUpdateTimeTable();
			// 1. DB연결
			getConnection();

			// 2. 결과 담을 ResultSet 생성 후 결과 담기
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM WUPDATE");

			// 4. ResultSet에 담긴 결과를 ArrayList에 담기
			while (rs.next()) {
				update = rs.getString("UPDATETIME");
			}
			// 5. 결과물 출력
//			System.out.print(update);
		} catch (SQLException e) {
			System.out.println("업데이트를 한적이 없음!");
		} finally {
			disConnection();
		}
		return update;

	}

	public void UpdateSet() {
		try {
			getConnection();
			LocalTime now = LocalTime.now();
			String UpdateTime = LocalDate.now().toString() + " " + now.getHour() + ":" + now.getMinute() + ":"
					+ now.getSecond();
			update = UpdateTime;
			String sql = "UPDATE WUPDATE SET UPDATETIME = '" + update + "' WHERE NO = 0";

			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("업데이트 날짜 최신화 완료");

		} catch (SQLException e) {
			System.out.println("업데이트 인서트 오류");
		}
	}
}