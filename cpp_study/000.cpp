/*��������*/
#include <iostream>
#include <mysql/mysql.h>
#include <string>

using namespace std;

main()
{
     MYSQL mysql;
     mysql_init(&mysql);
     mysql_real_connect(&mysql, "localhost", "root", "", "test", 3306, NULL, 0);
     string sql = "update cpp_test set title = 'title1616' where id = 16;";
     mysql_query(&mysql, sql.c_str());
     mysql_close(&mysql);
}
/*
[root@localhost cpp_study]# g++ -o000o $(mysql_config --cflags) 000.cpp  $(mysql_config --libs)
00000.cpp:23:3: ���棺�ļ�δ�Կհ��н���
[root@localhost cpp_study]# ./000o
*/