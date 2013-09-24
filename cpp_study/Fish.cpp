class Animal(){
  virtual void breath() //加上virtual表示的是虚函数,意思是子类有的,调用子类的,子类没有的调用基类的.{
    cout<<"animal"<<endl;
  }

  void sleep(){
    cout<<"sleep"<<endl;
 }

 virtual void eat() = 0; //表示的是纯虚函数;既没有实现函数体.含有纯虚函数的类是抽象类.如果子类不实际eat()方法,也变得抽象类.
}

class fish():public Animal(){
   void breath(){
     cout<<"fish"<<endl;
   }

   void fn(Animal *pAn){
     pAn->breate();
   }
   virtual void eat(); //这里实际了基类的纯虚函数{
     cout<<"fish eat"<<endl;
   }
}

void main(){
  fish fh;
  Animal *pAn;
  pAn = &fh;
  fh(pAn);
}
