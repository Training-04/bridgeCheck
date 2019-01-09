# 数据库还原方法
1. 如果没有数据库需要先创建数据库
    - 进入mysql
    ```
        create database jpa_test;
    ```
2. 下载db.bak文件，放到c盘
    ```
        use jpa_test;
        source C:/db.bak;
    ```