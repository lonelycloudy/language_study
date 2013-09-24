#include <stdio.h> 
#include <mysql.h>
#include <stdlib.h> 


static char *server_args[] =  
    {  
        "this_program",
        "--datadir=.",  
        "--key_buffer_size=32M"  
    };  
static char *server_groups[] =  
{  
	"embedded",  
	"server",  
	"this_program_SERVER",  
	(char *)NULL  
};
int main(){

   if (mysql_server_init(sizeof(server_args) / sizeof(char *),  
						  server_args, server_groups))  
		exit(1);  	
	
	MYSQL *mysql;
	MYSQL_RES *result;
	MYSQL_ROW row;
  	
	char *server = "localhost";  
	char *user = "root";  
	char *password = "";
	char *database = "test";  
	mysql = mysql_init(NULL);
	
	if(!mysql_real_connect(mysql,server,user,password,database,0,NULL,0)){ 
		printf("Connect Failed");
		exit(1);
	}
	if(mysql_query(mysql," select * from cpp_test limit 1;")){ 
		printf("Select Error");
		exit(1);
	}
	result = mysql_use_result(mysql);  
	while ((row = mysql_fetch_row(result)) != NULL)  
		printf("id:%s,title:%s,content:%s \n", row[0],row[1],row[2]);
	mysql_free_result(result);
	mysql_close(mysql);
	return EXIT_SUCCESS;  
}
//g++ -o3o $(mysql_config --cflags) 3.cpp  $(mysql_config --libs)