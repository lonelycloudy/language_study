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
/*��ȡ����
[root@localhost cpp_study]# g++ -oparam.o param.cpp
param.cpp:15:3: ���棺�ļ�δ�Կհ��н���
[root@localhost cpp_study]# ./param.o 12 hel
./param.o
12
hel
*/