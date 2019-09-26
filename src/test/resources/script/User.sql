--进入mysql
--输入mysql -u test -p123456 -Dtest -A
drop table if exists test.User;
CREATE TABLE test.User
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT,
    `user_name`       varchar(50)      NOT NULL COMMENT '用户名称',
    `password`        varchar(50)      NOT NULL COMMENT '密码',
    `created_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT='用户表';

insert into test.User(user_name,password) value("marry","123456");
insert into test.User(user_name,password) value("jack","654321");