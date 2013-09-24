#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <mysql/mysql.h>
#include <syslog.h>
MYSQL mysql;
int main()
{
char *user="root",*password="",*host="localhost",*sql=malloc(300),db[30]={0};
if(mysql_init(&mysql)==NULL)
{
printf("初始化MySQL数据库失败！\n");
return -1;
}
if(mysql_real_connect(&mysql,host,user,password,db,0,NULL,0)==NULL)
{
printf("连接MySQL数据库失败：%s!",mysql_error(&mysql));
return -1;
}
printf("请输入数据库名：\n");
scanf("%s",&db);
//strcpy(db_arr,db);
sprintf(sql,"create database if not exists `%s`",db);
if(mysql_query(&mysql,sql))
{
printf("创建数据库失败：%s!\n",mysql_error(&mysql));
return -1;
}
printf("成功创建数据库!%s\n",db);
return 0;
}
//cc -o createdb1 $(mysql_config --cflags) createdb1.c $(mysql_config --libs)
//./createdb1
