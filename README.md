#Java project
#Jsoup을 이용한 크롤링
#Java Pojo로 구성된 애플리케이션
##java 11.0, Oracle 11g(Ojdbc6), jsoup-1.15.3 


크롤링 사이트 - 네이버웹툰
크롤링 항목 = 제목 작가 요일 정보 장르 최신화번호
오라클을 활용한 DB자동 저장
Create 업데이트 자동으로 DB 초기화 가능
INSERT 및 UPDATE 기능 작동 (UPDATE시 제목으로 검색 후 수정기능 작동)
READ (요일, 제목, 작가이름, 장르, 전체) 기능 작동
Delete 삭제기능 작동 (제목으로 검색 및 삭제 확인 메세지)