package Webtoon;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WT_Crawling_List {
	List<WT_VO> list = new ArrayList<WT_VO>();


	public void webtoon_craw() {
				
		DataDAO dao = DataDAO.newInstance();
		
		try {
			Document doc = Jsoup.connect("https://comic.naver.com/webtoon/weekday").get();
			Elements day = doc.select("div.daily_all div.col");
			Elements link = doc.select("div.daily_all div.col_inner div.thumb a");

			int k = 1;

			for (int i = 0; i < day.size(); i++) {
				Elements title = doc.select("div.daily_all div.col:eq( " + i + " ) ul li");
				Elements dinfo = doc.select("div.daily_all div.col:eq(" + i + ") div.col_inner h4");

				for (int j = 0; j < 5; j++) {
					try {
						System.out.println(k);
						System.out.println("제목 : " + title.get(j).text());
						System.out.println("연재요일 : " + dinfo.text().substring(0, 2) + "일");
						Document doc2 = Jsoup.connect("https://comic.naver.com" + link.get(j).attr("href")).get();
						Elements writer = doc2.select("div.comicinfo div.detail h2 span.wrt_nm");
						Elements info = doc2.select("div.comicinfo div.detail p:eq(1)");
						Elements genre = doc2.select("div.comicinfo div.detail p.detail_info span.genre");
						Elements recent = doc2.select("table.viewList tbody tr td.title");
//               Elements like = doc2.select("div.detail ul.btn_group li:eq(4) div.u_likeit_module em");

						System.out.println("작가 :" + writer.text());
						System.out.println("설명 : " + info.text());
						System.out.println("장르 : " + genre.text());
						System.out.println("최신화 차수 : " + recent.get(0).text());
//               System.out.println(like.text());
						System.out.println("======================================================================");

						WT_VO w = new WT_VO();
						w.setTitle(title.get(j).text());
						w.setDinfo(dinfo.text().substring(0, 2) + "일");
						w.setWriter(writer.text());
						w.setInfo(info.text());
						w.setGenre(genre.text());
						w.setRecent(recent.get(0).text());

						dao.wtListInsert(w);
												
						k++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}