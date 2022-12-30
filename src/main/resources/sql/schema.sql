use portal;
set foreign_key_checks = 0;
drop table if exists user;
create table if not exists users
(
    username varchar(50)  not null primary key,
    password varchar(200) not null,
    enabled  boolean      not null
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
    sequence int         not null default 99999 comment '排序权重',
    name     varchar(10) not null comment '姓名',
    phone    char(11)    null comment '手机号码',
    username varchar(50) null comment '用户名'
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
set foreign_key_checks = 1;