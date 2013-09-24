/*查询数据列表*/
#include <iostream>
#include <string>
#include <mysql/mysql.h>

using namespace std;

main()
{
    MYSQL mysql;
    MYSQL_RES *result = NULL;
    MYSQL_FIELD *field = NULL;
    mysql_init(&mysql);
    mysql_real_connect(&mysql, "localhost", "root", "", "test", 3306, NULL, 0);
    string str = "select * from cpp_test;";
    mysql_query(&mysql, str.c_str());
    result = mysql_store_result(&mysql);
    int rowcount = mysql_num_rows(result);
    cout << rowcount << endl;
    int fieldcount = mysql_num_fields(result);
    cout << fieldcount << endl;
    for(int i = 0; i < fieldcount; i++)
    {
     field = mysql_fetch_field_direct(result,i);
     cout << field->name << "\t\t";
    }
    cout << endl;
    MYSQL_ROW row = NULL;
    row = mysql_fetch_row(result);
    while(NULL != row)
    {
     for(int i=1; i<fieldcount; i++)
     {
        cout << row[i] << "\t\t";
     }
     cout << endl;
     row = mysql_fetch_row(result);
    }
    mysql_close(&mysql);
}
/*
[root@localhost cpp_study]# g++ -o0000o $(mysql_config --cflags) 0000.cpp  $(mysql_config --libs)
[root@localhost cpp_study]# ./0000o
13
4
id              title           content         created
title1          content1                2012
title2          content2                2012
title3          content3                2012
title4          content4                2012
ctitle1         ccontent1               0
ctitle1         ccontent1               123569685
ctitle1         ccontent1               123569685
ctitle1         ccontent1               123569685
ctitle1         ccontent1               123569685
ctitle1         ccontent1               123569685
ctitle1         ccontent1               123569685
ctitle1         ccontent1               123569685
cpp1            cppcontent              1245678
*/