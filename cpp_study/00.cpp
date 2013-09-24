/*删除数据记录*/
#include <iostream>
#include <mysql/mysql.h>
#include <string>
using namespace std;
main()
{
     MYSQL mysql;
     mysql_init(&mysql);
     mysql_real_connect(&mysql, "localhost", "root", "", "test", 3306, NULL, 0);
     string sql = " delete from cpp_test order by id asc limit 1;";
     mysql_query(&mysql, sql.c_str());
     mysql_close(&mysql);
}
/*
[root@localhost cpp_study]#  g++ -o00o $(mysql_config --cflags) 00.cpp  $(mysql_config --libs)
0000.cpp:17:3: 警告：文件未以空白行结束
[root@localhost cpp_study]# ./00o
*/