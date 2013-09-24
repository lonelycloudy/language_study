//Fillstream.cpp
//g++ -oFillstream Fillstream.cpp
#include <string>
#include <iostream>
#include <fstream>
using namespace std;

int main(){
   ifstream in ("Fillstream.cpp"); 
   string s,line;
   while(getline(in,line)) 
     s+=line+"\n";
   cout << s;
   return 0; 
}
