#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <mysql/mysql.h> 
#include <assert.h>
 
 
int main()
{
 
    MYSQL* con = NULL;
     
    con = mysql_init(NULL);
 
    assert(con != NULL);
 
    if(!mysql_real_connect(con, "localhost", "root", "", "test", 0, NULL, 0)) 
    {
 
        mysql_real_query(con,"select * from cpp_test;",20);
        MYSQL_RES *res=mysql_store_result(con);
        int num_fields = mysql_num_fields(res);
        MYSQL_ROW row;
        while((row = mysql_fetch_row(res)))
        {
            int i;
            for(i=0;i<num_fields;i++)
            {
                char buf[100];
                sprintf(buf,"[%s]",row[i]?row[i]:"NULL");
                fprintf(stdout,buf);
            }
            fprintf(stdout,"\n");
        }
        mysql_free_result(res);
        mysql_close(con); 
        printf("connect mysql success\n");
        exit(0);
    }
 
    else 
    {
        printf("connect mysql failed\n");
    }
 
 
 
     
 
}
