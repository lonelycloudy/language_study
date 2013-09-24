/*统计行数*/
#include <iostream>
#include <string>
#include <mysql/mysql.h>

using namespace std;

main()
{
    MYSQL mysql;
    MYSQL_RES *result = NULL;
    mysql_init(&mysql);
	mysql_real_connect(&mysql, "localhost", "root", "", "test", 3306, NULL, 0);
    string sql = "select * from cpp_test limit 3;";
    mysql_query(&mysql, sql.c_str());
    result = mysql_store_result(&mysql);
    int rowcount = mysql_num_rows(result);
    cout << rowcount << endl;
    mysql_close(&mysql);
}
/*

[root@localhost cpp_study]# g++ -o00o $(mysql_config --cflags) 00.cpp  $(mysql_config --libs)
[root@localhost cpp_study]# ./00o
3
*/