//Scopy.cpp
//g++ -oScopy ./Scopy.cpp
#include <string>
#include <fstream>
using namespace std;

int main(){
    ifstream in ("Scopy.cpp");
    ofstream out("Scopy2.cpp");
    string s;
    while (getline(in,s))
	out << s << "\n";
    return 0;
}
