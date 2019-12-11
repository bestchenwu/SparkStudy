# -*- coding: utf-8 -*-
import os
from pyhdfs import HdfsClient
from datetime import datetime as date_time
from time import sleep, ctime
import shutil
import threading

timeFormat = "%Y-%m-%d"
todayDate = date_time.now()
today = todayDate.strftime(timeFormat)
weibo_hdfs_file_path_map = {
    '/data/bigdataSchedule/idataapi/xiaohongshu/xiaohongshu_user_info': '/user/hive/warehouse/ods.db/idata_xiaohongshu_user/dt=%s/',
    '/data/bigdataSchedule/idataapi/xiaohongshu/xiaohongshu_biji_detail': '/user/hive/warehouse/ods.db/idata_xiaohongshu_biji_detail/dt=%s/'
}
success_file_name_format = 'success_%s.txt'


# 在每个作业目录下先生成当天的日期目录,每个用户都存在当天日期目录里面
def prepare():
    today_path_list = []
    for path in weibo_hdfs_file_path_map.keys():
        if not path.endswith('/'):
            path += '/'
        os.chdir(path)
        today_path = path + today
        if os.path.exists(today_path):
            shutil.rmtree(today_path)
        os.makedirs(today_path)
        today_path_list.append(today_path)
    return today_path_list


# 上传微博的用户信息和发的各条微博信息到对应的Hive表中
def upload(today_weibo_path_list):
    # 获取去除日期后的目录名称
    threads = []
    for today_weibo_path in today_weibo_path_list:
        if today_weibo_path.endswith('/'):
            today_weibo_path = today_weibo_path[0:today_weibo_path.__len__() - 1]
        weibo_hdfs_file_path_map_key = today_weibo_path[0:today_weibo_path.rfind('/')]
        today_hdfs_path = weibo_hdfs_file_path_map.get(weibo_hdfs_file_path_map_key) % today
        new_thread = threading.Thread(target=upload_file_to_hdfs, args=[today_weibo_path, today_hdfs_path])
        threads.append(new_thread)
    for item_thread in threads:
        item_thread.start()
    # for item_thread in threads:
    #     item_thread.join()
    # print("all weibo files upload finish:", ctime())


def upload_file_to_hdfs(today_local_path, today_hdfs_path):
    # 检查当前目录下是否有success_today.txt
    success_file_name = success_file_name_format % today
    index = 0
    success_file_exist_flag = os.listdir(today_local_path).__contains__(success_file_name)
    while not success_file_exist_flag:
        # 如果没有找到success_today.txt ,则睡眠每次等待1分钟
        index += 1
        if index == 180:
            break
        else:
            sleep(60)
            success_file_exist_flag = os.listdir(today_local_path).__contains__(success_file_name)
    if not success_file_exist_flag:
        print('not get success_file')
        return
    hdfs_client = HdfsClient(hosts=['10.40.11.12:50070', '10.40.11.13:50070'], user_name='bi')
    if hdfs_client.exists(today_hdfs_path):
        hdfs_client.delete(today_hdfs_path, recursive=True)
    for local_file_item in os.listdir(today_local_path):
        if local_file_item.startswith('success'):
            # 过滤掉success.txt文件
            continue
        current_hdfs_path = today_hdfs_path + local_file_item
        local_file_item = os.path.join(today_local_path,local_file_item)
        hdfs_client.copy_from_local(local_file_item, current_hdfs_path)

def concat_file_to_hdfs():
    fileList = os.listdir('D:/logs/xiaohongshu_user/')
    hdfs_client = HdfsClient(hosts=['10.40.11.12:50070', '10.40.11.13:50070'], user_name='bi')
    hdfs_client.concat('/user/hive/warehouse/ods.db/idata_xiaohongshu_user/dt=2019-12-10/',fileList)

if __name__ == '__main__':
    #weibo_path_list = prepare()
    #upload(weibo_path_list)
    concat_file_to_hdfs()