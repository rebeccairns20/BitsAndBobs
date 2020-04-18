#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;
class Money{
    public:
       void setBills(int bType, int numBills){
           numBill = numBills;
           billType = bType;

       }
       int getNumOfBills(){return numBill;}
       int getBillType(){return billType;}
    private:
       int numBill;
       int billType;
};
class SufficientFunds{
  public:a
   void enoughMoney(int inPut){
     if(inPut%10>=1){
        cout << "Incorrect amount of money. Input Must be a Multiple of Ten";
      }
      if(!inPut%10>=1 && inPut<=1720){
        cout <<  "Enough money  ";
      }
      if(inPut>1720){
        cout <<  "Not Enough money   ";
      }
    }
};

int main(){
    //link object
    SufficientFunds output;
    Money tens;
    tens.setBills(10, 5);
    Money twentys;
    twentys.setBills(20, 6);
    Money fiftys;
    fiftys.setBills(50, 3);
    Money hundreds;
    hundreds.setBills(100, 4);
    Money twoHundreds;
    twoHundreds.setBills(200, 5);

    //vector
    //vector<Money> atm;
    //atm.push_back(1);

    //withdrawl
    int withdrawl, withdrawl2;
    cout << "Please enter the first amount you would like to withdraw:";
    cin >> withdrawl;
    cout << "Please enter the second amount you would like to withdraw:";
    cin >> withdrawl2;
    cout << "First Entry: ";
    output.enoughMoney(withdrawl);
    cout << endl <<  "Second Entry: ";
    output.enoughMoney(withdrawl2);




	return 0;
}
