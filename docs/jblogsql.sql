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
select count(*) from category;
desc category; 


insert into category values(null, "Afd", "Adsfas", date_formate(now(),"yyyy-mm-dd") ,"elsa");

select  c.name, c.desc, count(p.category_no) as count
from category c
left join post p
on c.no = p.category_no
where c.blog_id = 'mike'
group by c.no;



desc post;
 select * from user where id=123 and password=1;
 select * from user where id=123 and password=2;
 