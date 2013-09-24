#include <mysql.h>  
#include <stdlib.h>  
#include <stdio.h>  
static char *server_args[] =  
{  
	"this_program",       /* this string is not used */  
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
int main(void)  
{  
	if (mysql_server_init(sizeof(server_args) / sizeof(char *),  
						  server_args, server_groups))  
		exit(1);  
		
	MYSQL *conn;  
	MYSQL_RES *res;  
	MYSQL_ROW row;  
	char *server = "localhost";  
	char *user = "root";  
	char *password = ""; /*db pass */  
	char *database = "test";  
	conn = mysql_init(NULL);  
	int rowcount;
		
	/* Connect to database */  
	if (!mysql_real_connect(conn, server,  
							user, password, database, 0, NULL, 0))  
	{  
		fprintf(stderr, "%s\n", mysql_error(conn));  
		exit(1);  
	}  
	/* send SQL query */  
	if (mysql_query(conn, "select * from cpp_test limit 1;"))  
	{  
		fprintf(stderr, "%s\n", mysql_error(conn));  
		exit(1);  
	}  
	printf("Select From Row test.cpp:\n");  
	res = mysql_use_result(conn);  
	while ((row = mysql_fetch_row(res)) != NULL)  
		printf("id:%s,title:%s,content:%s \n", row[0],row[1],row[2]);  

	/*rowcount = mysql_num_rows(res);
	printf("%d",rowcount);*/
	
	mysql_free_result(res);  
	mysql_close(conn);  
	mysql_server_end();  
	return EXIT_SUCCESS;  
}  
//g++ -o 6.out 6.cpp -lmysqlclient -I/usr/include/mysql/ -L/usr/lib/mysql
//g++ -o 1.out  1.cpp  -lmysqlclient -I/usr/local/mysql/include/mysql/ -L/usr/local/mysql/lib/mysql
//g++ -o1o $(mysql_config --cflags) 1.cpp  $(mysql_config --libs)
