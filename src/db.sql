SHOW DATABASES ;
SHOW TABLES ;
USE test;
select * from test.student;
SELECT * FROM test.studentlogin;
SELECT * FROM test.user;
SELECT t.email,t.password ,DATE_FORMAT(sysdate(),'%Y-%m-%d') as date ,count(t.email) as count FROM test.user t
GROUP BY t.email,t.password HAVING count >1
;
SELECT DISTINCT t.email,t.password  from test.user t ;
CREATE TABLE tm SELECT DISTINCT t.email,t.password  from test.user t ;
SELECT *  from odinglists t ORDER BY str_to_date(listdate,"%Y-%m-%d %T") desc   limit 0,10;
SELECT * FROM tm;
SHOW DATABASES ;
USE test;
SHOW DATABASES ;
SHOW TABLES ;
CREATE TABLE admin SELECT * FROM user;
DELETE * FROM admin;
SELECT * FROM test.teacher;
DROP TABLE admin;
SHOW TABLES ;
CREATE TABLE test.HeadPortrait(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255),
  HeadPortrait VARCHAR(255)

);
SELECT * from test.food;

DELETE FROM food where foodurl ='' ;
CREATE TABLE test.odinglists(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  ordingid VARCHAR(255),
  foodname VARCHAR(255),
  foodprice VARCHAR(255),
  foodcount VARCHAR(255),
  listdate  VARCHAR(255)
);
DROP TABLE secondmenu;
CREATE TABLE test.fristmenu(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  menu VARCHAR(255),
  tile VARCHAR(255),

  icon  VARCHAR(255),
  createdate  VARCHAR(255)

);
CREATE TABLE test.secondmenu(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  fristmenu VARCHAR(255),
  name VARCHAR(255),
  title VARCHAR(255),

  jump  VARCHAR(255),
  createdate  VARCHAR(255)

);
INSERT INTO fristmenu (menu, tile, icon, createdate) VALUES ('organization','系统管理','layui-icon-set',sysdate());
INSERT INTO secondmenu (fristmenu ,name, title,  jump, createdate) VALUES ('系统管理','departmentManagement','菜单管理',''
,sysdate());
SELECT * from fristmenu;
DELETE FROM fristmenu WHERE id=9;
SELECT * from secondmenu where fristmenu='商品管理';
select * from odinglists  WHERE str_to_date(listdate,"%Y-%m-%d %T") BETWEEN str_to_date('2019-07-14 11:14:42',"%Y-%m-%d %T") and str_to_date('2019-07-14 15:20:28',"%Y-%m-%d %T");
SELECT * FROM odinglists WHERE  str_to_date(listdate,"%Y-%m-%d ")BETWEEN date_sub(curdate(),interval 1 day)AND date_sub(curdate(),interval 0 day) ;
select t.*,foodprice*foodcount as countprice  from odinglists t;
SELECT CONCAT('v_content',1);
select str_to_date(CONCAT_WS(' ',curdate(),'00:00:01'),"%Y-%m-%d %T") - interval 24 hour  sysdate;
select CONCAT_WS('','%',curdate()-interval 1 DAY ,'%');
select date_sub(curdate(),interval 1 day);
select str_to_date(CONCAT_WS(' ',date_sub(curdate(),interval 1 day),'00:00:01'),"%Y-%m-%d %T");
DELETE FROM test_view_2;
where time between  now()  and (NOW() - interval 24 hour)
SELECT count(ordingid) ordingcount,sum(price) ordingprice from test_view_4 t WHERE
  str_to_date(ordingdate,"%Y-%m-%d %T")BETWEEN
  str_to_date(CONCAT_WS(' ',curdate(),'00:00:01'),"%Y-%m-%d %T") - interval 24 hour AND
  str_to_date(CONCAT_WS(' ',curdate(),'00:00:01'),"%Y-%m-%d %T") ;



CREATE VIEW test_view_4 AS
SELECT count(ordingid) as count,ordingid,sum(foodprice*foodcount) price ,listdate as ordingdate from odinglists GROUP BY ordingid;
SELECT count(ordingid) ordingcount,sum(price)ordingprice from test_view_4 t WHERE str_to_date(ordingdate,"%Y-%m-%d ")>date_sub(curdate(),interval 1 day);
SELECT t.*from test_view_4 t WHERE str_to_date(ordingdate,"%Y-%m-%d ")BETWEEN date_sub(curdate(),interval 2 day)AND date_sub(curdate(),interval 1 day) ;

/*当天实时数据统计*/
select count(ordingid) ordingcount,sum(price)  FROM test_view_4
where str_to_date(ordingdate,"%Y-%m-%d %T") BETWEEN
(str_to_date(CONCAT_WS(' ',curdate(),'00:00:01'),"%Y-%m-%d %T")+ interval 12 hour ) AND
(str_to_date(CONCAT_WS(' ',curdate(),'00:00:01'),"%Y-%m-%d %T") + interval 20 hour);
select *  FROM test_view_4 WHERE str_to_date(ordingdate,"%Y-%m-%d ")
LIKE CONCAT_WS('','%',curdate()-interval 3 DAY ,'%');

str_to_date(CONCAT_WS(' ',curdate()),"%Y-%m-%d ")
/*最近一周数据统计*/

select count(ordingid) ordingcount,sum(price)  FROM test_view_4
where str_to_date(ordingdate,"%Y-%m-%d %T") BETWEEN
str_to_date(CONCAT_WS(' ',date_sub(curdate(),interval 3 day),'00:00:01'),"%Y-%m-%d %T")
and str_to_date(CONCAT_WS(' ',date_sub(curdate(),interval 2 day),'00:00:01'),"%Y-%m-%d %T");

/*昨日销量top10*/
select t.foodname ,sum(foodcount) count from odinglists t
GROUP BY t.foodname  ORDER BY count desc  ;
select * from odinglists;



DROP procedure test.num_from_employe;

call num_from_employe;
DELETE FROM listtj;
SELECT  * from listtj where str_to_date(listdate,"%Y-%m-%d")=curdate()-interval 1 DAY ;
INSERT INTO listtj(listdate,listcount)VALUES ((select date_sub(curdate(),interval 3 day)),
 6
 );
select t.foodname ,count(t.foodname) countt from odinglists t
GROUP BY t.foodname  ORDER BY countt
CREATE TABLE test.listtj(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  listdate VARCHAR(255),
  listcount VARCHAR(255)
);
CREATE TABLE test.food(

  foodmodel VARCHAR(255),
  foodname VARCHAR(255),
  foodurl VARCHAR(255)
);
ALTER TABLE food ADD price VARCHAR(255);
SELECT * ,
  CASE foodmodel
  WHEN '1' THEN '热销'
  WHEN 2 THEN '荤菜'
  WHEN 3 THEN '素菜'
  WHEN 4 THEN '凉菜'
  WHEN 5 THEN '今日促销'
  ELSE '其他' END  foodmode2 from food  limit 0,10;
SELECT * ,
  CASE foodmodel
  WHEN '1' THEN '热销'
  WHEN 2 THEN '荤菜'
  WHEN 3 THEN '素菜'
  WHEN 4 THEN '凉菜'

  ELSE '其他' END  foodmode2 from food;
DELETE  from test.food where foodname="kkkk";

CREATE TABLE test.teacher(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY ,

  email VARCHAR(255),
  password VARCHAR(255)
);
UPDATE test.food SET foodmodel=2,foodurl=http://localhost:8081/upload/imgs/20190630/1561830456699_688.png,         price=55         WHERE foodname=我是热销
SELECT t.* FROM test.food t WHERE t.foodmodel=1;
CREATE TABLE test.zqinfo(
  idd  VARCHAR(255) ,
  datea  VARCHAR(255) ,
  email VARCHAR(255),
  password VARCHAR(255),
  name VARCHAR(255)
);
SELECT * FROM zqinfo;
  DROP TABLE subject;
CREATE TABLE test.subject(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  subjectid VARCHAR(255),
  subject VARCHAR(255),
  classplace VARCHAR(255),
  classtime VARCHAR(255),
 classhour VARCHAR(255)

);
DESC test.subject;
SHOW INDEX test.subject;
SELECT * FROM subject;
INSERT INTO subject VALUES (20,004,'aaa','aaa','aaa','aaa');
CREATE TABLE test.studentlogin(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  email VARCHAR(255),
  password VARCHAR(255)
);
SELECT * FROM test.subject where subject='大物'limit 0,10 ;
SELECT * FROM test.subject          limit 0,10;
INSERT INTO studentlogin VALUES (NULL,'a','123456' );
CREATE TABLE test.student(
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  studentid VARCHAR(255),
  name VARCHAR(255),
  classname VARCHAR(255)
);
CREATE TABLE test.xuanke (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  studentid VARCHAR(255),
  name VARCHAR(255),
  subject VARCHAR(255)

);
DROP TABLE student;
SELECT * FROM student;
SELECT * FROM test.xuanke;
SELECT t.studentid,t.name,t.classname,s.subject FROM student t,xuanke x ,subject s  WHERE t.studentid=x.studentid and x.subject=s.subjectid;

CREATE TABLE aaa SELECT * FROM subject;

SELECT * FROM aaa;
ALTER TABLE aaaa ADD studentid VARCHAR(255);
ALTER TABLE aaaa DROP datetimea;
DROP TABLE aaaa;
INSERT INTO  aaaa (studentid)
    SELECT studentid FROM student;
INSERT INTO aaaa SELECT * FROM student;
