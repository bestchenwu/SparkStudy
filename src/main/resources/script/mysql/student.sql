create table `student`(
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键,自增长',
    `name` varchar(15) NOT NULL COMMENT '名称,唯一',
    `age` int(11) NOT NULL COMMENT '年龄',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

--初始化语句
insert into student(name,age) value('jack',18);
insert into student(name,age) value('mary',22);
insert into student(name,age) value('tom',35);