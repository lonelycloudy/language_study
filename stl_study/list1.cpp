#include <string>
#include <list>

int main (void) 
{
  list<string> Milkshakes;
  Milkshakes.push_back("Chocolate");
  Milkshakes.push_back("Strawberry");
  Milkshakes.push_front("Lime");
  Milkshakes.push_front("Vanilla");
  return 0;
}
