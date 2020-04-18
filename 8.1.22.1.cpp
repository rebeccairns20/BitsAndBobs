//Author: Rebecca Cairns
//C++2
//4/10/19
//8.1.22.1

#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main(){
	string name, destroyableName, introduction = "My name is ";
	cout << "Please enter your name:   ";
    getline (std::cin, name);

    while(destroyableName != introduction){
        if(name.size() > 11){
            destroyableName = name;
            destroyableName.erase(11, destroyableName.size());
        }
        if(destroyableName == introduction){
            name.erase(0, 10);
        } else{
            cout << "Hi, please introduce yourself:   ";
            getline (std::cin, name);
        }
    }
    cout << "Hello " << name << "!";
	return 0;
}
