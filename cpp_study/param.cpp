#include <iostream> 
#include <string> 
using namespace std; 
 
int main(int argc,char* argv[])   
{ 
    while(*argv!=NULL)
    { 
        cout<<*argv++<<endl; 
    } 
    cin.get(); 
} 
/*获取参数
[root@localhost cpp_study]# g++ -oparam.o param.cpp
param.cpp:15:3: 警告：文件未以空白行结束
[root@localhost cpp_study]# ./param.o 12 hel
./param.o
12
hel
*/