#include <mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
static char* server_groups[]={"embedded","server","this_program_server",(char*)NULL};
int main(){

MYSQL mysql;
MYSQL_RES *res;
MYSQL_ROW row;
char sqlcmd[200];
int t,r;
mysql_library_init(0,NULL,server_groups);//初始化MYSQL数据库
mysql_init(&mysql);//初始化MYSQL标识符，用于连接
if(!mysql_real_connect(&mysql,"localhost","root","","test",0,NULL,0)){
  fprintf(stderr,"无法连接到数据库，错误原因是:%s/n",mysql_error(&mysql));
}
else {
 puts("数据库连接成功");
//首先向数据库中插入一条记录
 sprintf(sqlcmd,"%s","insert into cpp_test (title,content,created) Values ('ctitle1','ccontent1','123569685')");
 mysql_query(&mysql,sqlcmd);
 sprintf(sqlcmd,"%s","select * from cpp_test");
 t=mysql_real_query(&mysql,sqlcmd,(unsigned int)strlen(sqlcmd));

 if(t){
 printf("查询数据库失败%s/n",mysql_error(&mysql));
}
else {
res=mysql_store_result(&mysql);//返回查询的全部结果集
while(row=mysql_fetch_row(res)){//mysql_fetch_row取结果集的下一行
 for(t=0;t<mysql_num_fields(res);t++){//结果集的列的数量
   printf("%s/t",row[t]);
 }
 printf("/n/r");

}

mysql_free_result(res);//操作完毕，查询结果集
}
mysql_close(&mysql);//关闭数据库连接

}

mysql_library_end();//关闭MySQL库

return EXIT_SUCCESS;
}
//cc -o select1 $(mysql_config --cflags) select1.c $(mysql_config --libs)