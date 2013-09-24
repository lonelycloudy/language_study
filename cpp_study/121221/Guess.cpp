//Guess.cpp
#include <iostream>
using namespace std;

int main(){
   
   int securet=15;
   int guess=15;
   while(securet!=guess){ 
      cout << "Guess Password"; 
      cin >> guess; 
   }
   cout << "You Guessed it!" << endl; 
   return 0;
}
