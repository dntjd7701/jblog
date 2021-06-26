desc post;
show tables;
desc user;
insert into user values();
select now();
select * from user;
desc user;
desc blog; 
-- blog와 user는 식별관계, id가 같다 
-- findByIdAndPassword
insert into blog values("123", "Spring 이야기", "/jblog03/assets/images/spring-logo.jpg");
select * from blog;
desc blog;

-- Create Blog(default)
desc blog;
delete from blog;
select * from blog;
-- Category
select * from category;
select max(no) from category where blog_id="elsa";
select count(*) from category;
desc category;  
desc post;
select * from post;
select category_no from post;
select p.no, p.title, p.contents, date_format(p.reg_date,'%Y/%m/%d') as regDate, p.category_no as categoryNo
			 from post p, category c 
			 where c.no=p.category_no 
			 	and p.category_no=3;
                
                select min(no) from category where blog_id="elsa";
                select * from post where category_no=3;
select * from category where blog_id= "elsa" limit 1;
                
select  c.no, c.name, c.desc, count(p.category_no) as count
				from category c left join post p
				on c.no = p.category_no 
				where c.blog_id = "elsa"
				group by c.no;
show tables;

insert into category values(null, "Afd", "Adsfas", now() ,"elsa");

select  c.name, c.desc, count(p.category_no) as count
from category c
left join post p
on c.no = p.category_no
where c.blog_id = 'mike'
group by c.no;
select * from category;
desc category;
select no, name, `desc`, date_format(reg_date, "%Y/%M/%D") as regDate, blog_id as id from category where blog_id = "elsa";

select `desc` from category;
-- post
select * from post where category_no=3 limit 1;
select * from post;
desc post;
select * from post p, category c where c.no=p.category_no limit 1;
select max(p.no)
			 from post p, category c 
			 where c.no=p.category_no 
			 	and c.no= 13;
desc post;
select max(p.no)
			 from post p, category c 
			 where c.no=p.category_no 
			 	and c.no=2;
select no, title, contents
			from post 
			where category_no=3 and no=1;
 select * from user where id=123 and password=1;
 select * from user where id=123 and password=2;
  
