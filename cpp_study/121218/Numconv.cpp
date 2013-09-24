//T2.cpp
//g++ -ot2 T2.cpp
#include <iostream>
using namespace std;

int main() {
    
    int number;
    cin >> number; 
    cout << "number in hex = 0x" << hex << number << "char:" << char(number) << endl;    
    return 0;
}
