package Webtoon;

public class WT_VO {

	private int num; //번호
	private String title;	//제목
	private String writer;	//작가
	private String dinfo; 	//요일
	private String info;	//정보
	private String genre;	//장르
	private String recent;	//최신화

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDinfo() {
		return dinfo;
	}
	public void setDinfo(String dinfo) {
		this.dinfo = dinfo;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getRecent() {
		return recent;
	}
	public void setRecent(String recent) {
		this.recent = recent;
	}


}
