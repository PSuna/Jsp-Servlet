create table product (
 product_code varchar2(10) primary key,
 product_name varchar2(100) not null, --상품명
 product_desc varchar2(1000) not null,
 product_price number not null,
 sale_price number,
 like_it number(3,1),
 image varchar2(100)
);
insert into product values('P2023001', '과테말라 안티구아','과테말라 안티구아 맛있는 커피입니다', 5000, 4500, 4.5, '과테말라 안티구아.jpg');
insert into product values('P2023002', '니카라구아 아라비카','니카라구아 아라비카 맛있는 커피입니다', 5500, 4500, 4.0, '니카라구아 아라비카.jpg');
insert into product values('P2023003', '브라질산토스','브라질산토스 맛있는 커피입니다', 6000, 5000, 3.5, '브라질산토스.jpg');
insert into product values('P2023004', '에티오피아 예가체프','에티오피아 예가체프 맛있는 커피입니다', 4000, 3500, 4.0, '에티오피아 예가체프.jpg');
insert into product values('P2023005', '케냐 오크라톡신','케냐 오크라톡신 맛있는 커피입니다', 4500, 3000, 3.0, '케냐 오크라톡신.jpg');
insert into product values('P2023006', '코스타리카 따라주','코스타리카 따라주 맛있는 커피입니다', 3000, 2500, 5.0, '코스타리카 따라주.jpg');
