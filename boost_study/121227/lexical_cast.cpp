#include <iostream>
#include <string>
#include "boost/lexical_cast.hpp"
int main() { 
   // string to int  
   std::string s="42";
   int i=boost::lexical_cast<int>(s);
   std::cout << "string to int" << i << std::endl;
   // float to string  
   float f=3.14151;
   s=boost::lexical_cast<std::string>(f); 
   std::cout << "float to string"  << s << std::endl;
   //literal to double  
   double d=boost::lexical_cast<double>("2.52");
   std::cout << "literal to double" << s << std::endl;
  //失败的转换  
  s="Not an int";  try {
      i=boost::lexical_cast<int>(s);
  }
  catch(boost::bad_lexical_cast& e) {
    // 以上lexical_cast将会失败，我们将进入这里  
  }
 
}

