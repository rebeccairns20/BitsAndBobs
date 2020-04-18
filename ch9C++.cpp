//Author: Rebecca Cairns
//Date: 4/10/19
//ch9 c++2
//Note: It does not print decimals...I'm not sure why.
#include <iostream>
#include <fstream>
#include <string>
#include <iomanip>
#include <sstream>

using namespace std;
template <class T> void printer(T val1, T val2){
    cout << val1 << " " << val2 << endl;
}
template <class T> string stringer(T val){
    return to_string(val);
}

template <class T> T returner(T val){
    if(val < 0){
        return -1;
    }else if(val == 0){
        return 0;
    }else{;
        return 1;
    }
}
int main() {
	int val1, val2;
    cout << "Please enter a number:  ";
    cin >> val1;
    cout << "Please enter another number:  ";
    cin >> val2;

    string numString1 = stringer(returner(val1));
    string numString2 = stringer(returner(val2));

    printer(numString1, numString2);
    printer(val1, val2);

	return 0;
}
