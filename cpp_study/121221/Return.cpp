//Return.cpp
#include <iostream>
//g++ -oReturn ./Return.cpp
using namespace std;

char cfunc(int i){
  if(i==0) 
	return 'a';
  if(i==1) 
	return 'g';
  if(i==5) 
	return 'z';
  return 'c';
}
int main(){
   cout << "Please Type an integer";
   int val; 
   cin >>val;
   cout << cfunc(val)<< endl;
}
