//Fillvector.cpp
#include <string>
#include <iostream>
#include <fstream>
#include <vector>
//g++ -oFill Fillvector.cpp
using namespace std;

int main(){
    vector <string> vec;
    ifstream in("Fillvector.cpp");
    string line;
    while(getline(in,line))
    vec.push_back(line);//push line to the end
    for(int i=0;i<vec.size();i++) 
     	cout << vec[i]<< endl;
   return 0; 
}
