use portal;
set foreign_key_checks = 0;
drop table if exists users;
create table if not exists users
(
    username varchar(50)  not null primary key,
    password varchar(200) not null,
    enabled  tinyint      not null default 1
) comment '用户表';
drop table if exists authorities;
create table if not exists authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
) comment '用户权限';
drop table if exists departments;
create table if not exists departments
(
    id       int         not null primary key auto_increment,
    name     varchar(50) not null comment '部门名称',
    superior int         not null comment '上级部门'
) comment '部门表';
drop table if exists employees;
create table if not exists employees
(
    id       int         not null primary key auto_increment,
    name     varchar(10) not null comment '姓名',
    phone    char(11)    null comment '手机号码',
    username varchar(50) null comment '用户名' references users(username) ,
    position int not null default 0 comment '排序权重'
) comment '员工';
drop table if exists dept_manager;
create table if not exists dept_manager
(
    dept_id    int not null comment '部门ID',
    manager_id int not null comment '负责人员工ID',
    from_date  date comment '开始日期',
    to_date    date default '2099-12-31' comment '结束日期',
    foreign key (dept_id) references departments (id),
    foreign key (manager_id) references employees (id)
) comment '部门负责人';
drop table if exists dept_emp;
create table if not exists dept_emp
(
    dept_id   int not null comment '部门ID',
    emp_id    int not null comment '员工ID',
    from_date date comment '开始日期',
    to_date   date default '2099-12-31' comment '结束日期',
    foreign key (dept_id) references departments (id),
    foreign key (emp_id) references employees (id)
) comment '部门员工';
drop table if exists article_column;
create table if not exists  article_column
(
    id int not null primary key comment '栏目ID',
    name varchar(50) not null comment '栏目名称',
    parent int comment '父栏目'
)comment '文章栏目';
drop table if exists article;
create table if not exists  article(
    id int not null auto_increment primary key comment '文章ID',
    title varchar(50) not null comment '标题',
    cid int comment '栏目ID',
    posttime datetime comment '发布时间',
    validity datetime comment '有效期',-- 用于通知、公告，这类文章在有效期之后应当自动不予显示
    username varchar(50) not null comment '发布者账号',
    author varchar(50) comment '作者',
    auditor varchar (50) comment '审核者',
    status varchar (50) comment '状态',-- 编辑、待审核状态、发布
    body text comment '正文',
    foreign key (cid) references article_column(id)
)comment '文章';
create table if not exists asset_card(
    identifier varchar(36) comment '资产编号',
    category  varchar(40) comment '资产分类',
    name varchar(40) comment '资产名称',
    brand varchar(40) comment '品牌',
    specifications varchar(40) comment '规格型号',
    quantity tinyint comment '数量',
    worth decimal(12,2) comment '价值',
    acquire date comment '取得日期',
    management varchar(30) comment '管理部门',
    section varchar(30) comment '使用部门',
    user varchar(20) comment '使用人',
    place varchar(30) comment '存放地点',
    comments varchar(200) comment '备注'
)comment '资产卡片';
create table if not exists category_tax(
    code varchar(6) not null comment '类别代码',
    parent varchar(6) comment '上级类别代码',
    name varchar(30) comment '名称'
)comment '资产分类代码（税务）';
create table if not exists category(
    code varchar(6) not null comment '类别代码',
    parent varchar(6) comment '上级类别代码',
    name varchar(30) comment '名称',
    unit varchar(5) comment '计量单位',
    description varchar(100) comment '说明',
    code_tax varchar(11) comment '税务资产分类代码'
)comment '资产分类代码';
drop table if exists menu;
create table if not exists menu(
    id  int not null primary key auto_increment,
    name varchar(10) not null
)comment '菜单';
drop table if exists menu_item;
create table if not exists menu_item
(
    id  int not null primary key auto_increment,
    menu_id int,
    text    varchar(10)  not null,
    url     varchar(100) not null default '#',
    parent  int,
    foreign key (menu_id) references menu (id)
)comment '菜单项';
set foreign_key_checks = 1;
