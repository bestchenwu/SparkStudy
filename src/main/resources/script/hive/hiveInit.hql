create database if not exists inf comment 'inf 接口数据存储位置' with dbproperties('creator'='sweet','date'='2019-01-06')

--例子sql
CREATE EXTERNAL TABLE IF NOT EXISTS `dmp_clearlog` (
  `date_log` string COMMENT 'date in file',
  `hour` int COMMENT 'hour',
  `device_id` string COMMENT '(android) md5 imei / (ios) origin  mac',
  `imei_orgin` string COMMENT 'origin value of imei',
  `mac_orgin` string COMMENT 'origin value of mac',
  `mac_md5` string COMMENT 'mac after md5 encrypt',
  `android_id` string COMMENT 'androidid',
  `os` string  COMMENT 'operating system',
  `ip` string COMMENT 'remote real ip',
  `app` string COMMENT 'appname' )
COMMENT 'cleared log of origin log'
PARTITIONED BY (
  `date` date COMMENT 'date used by partition'
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
TBLPROPERTIES ('creator'='szh', 'crate_time'='2018-06-07')
;

CREATE TABLE IF NOT EXISTS people_bucket2(
  name string comment '用户姓名',
  age bigint comment '用户年龄'
) COMMENT '用户表2';