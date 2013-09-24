/*插入数据后,更新数据*/
#include <iostream>
#include <mysql/mysql.h>
#include <string>

using namespace std;

main()
{
     MYSQL mysql;
     mysql_init(&mysql);
     mysql_real_connect(&mysql, "localhost", "root", "", "test", 3306, NULL, 0);
     string sql = " update test_cpp set `title` = \"title1616\" where id = 16;";
     mysql_query(&mysql, sql.c_str());
     mysql_close(&mysql);
}
/*
[root@localhost cpp_study]# g++ -o00000o $(mysql_config --cflags) 00000.cpp  $(mysql_config --libs)
00000.cpp:23:3: 警告：文件未以空白行结束
[root@localhost cpp_study]# ./00000o
*/