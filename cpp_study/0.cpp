/*≤Â»Î ˝æ›*/
#include <iostream>
#include <mysql/mysql.h>
#include <string>
using namespace std;
int main(int argc,char* argv[])
{
     MYSQL mysql;
     mysql_init(&mysql);
     mysql_real_connect(&mysql, "localhost", "root", "", "test", 3306, NULL, 0);
     string sql = "insert into cpp_test (title,content,created) values ('titlecpp','titlecpp','titlecpp');";
     mysql_query(&mysql, sql.c_str());
     mysql_close(&mysql);
}
/*
g++ -o 0.out 0.cpp -lmysqlclient -I/usr/include/mysql/ -L/usr/lib/mysql
[root@localhost cpp_study]# g++ -o0o $(mysql_config --cflags) 0.cpp  $(mysql_config --libs)
[root@localhost cpp_study]# ./0o
*/