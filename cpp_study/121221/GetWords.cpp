//GetWords.cpp
#include <string> 
#include <iostream> 
#include <fstream> 
#include <vector> 

using namespace std; 
//g++ -oGetWords GetWords.cpp

int main(){
  vector<string> words;
  fstream in("GetWords.cpp");
  string word;
  while (in>>word)
     words.push_back(word);
  for(int i=0;i<words.size();i++)
     cout << words[i] << endl;
  return 0;
}
