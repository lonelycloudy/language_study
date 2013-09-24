#include <iostream>
using namespace std;
/*Base Class*/
public class Layer123{
	public :
		void layer();
		void foo();
};

void Layer123::foo(){
     cout<< "Layer123"<<endl;
}
   
class Layer1:public Layer123{
	public :
		void layer();
		void foo();
};
void Layer1::foo(){
     cout<< "Layer1"<<endl;
}
class Layer2:public Layer123{
	public :
		void layer();
		void foo();
};
void Layer2::foo(){
     cout<< "Layer2"<<endl;
}
class Layer3:public Layer123{
	public :
		void layer();
		void foo();
};
void Layer3::foo(){
     cout<< "Layer3"<<endl;
}

int main(){
  Layer123 *pLayer;
  int i;
  switch(i){
       case  1:
         Layer1 layer();
         break; 
       case  2:
         Layer2 layer();
 	break; 
       case  3:
         Layer3 layer();
         break;
  } 
  pLayer->foo();
  delete pLayer;
  return 0;
}
