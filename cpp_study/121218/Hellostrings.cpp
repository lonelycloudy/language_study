//Hellostring.cpp
//g++ -oHellostring Hellostring.cpp
#include <string>
#include <iostream>
using namespace std;
int main(){
   string s1,s2; 
   string s3="Hello,from Linux";
   string s4="world";
   s2="Today";
   s1=s3+ " " + s4;
   s1+="8";
   cout << s1+s2 + "!" << endl;
   return 0; 
}
