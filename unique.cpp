//Author: Rebecca Cairns
//Date: 3/26/19
//note: Does not work.  I really need help.
#include
#include
#include
#include

using namespace std;

class Pets{
    public:
      string name;
      string type;
    //Pets(){name = ""}
};

void print(const Pets & pet) {
    cout<< pet.name <<" " << pet.type << " ";
}

bool comparePets (const Pets & lhs, const Pets & rhs ) {
  return (lhs.name == rhs.name);
}

int main(){
     Pets animals;
     vector pets = { {"Amber", "Cat"}, {"Barky", "Dog"}, { "Barky", "Reptile" },
                  {"Casper", "Fish"}, {"Casper", "Reptile"}, {"Godzilla", "Reptile"} };
     vector uniquePets(pets.size());
     sort(pets.begin(), pets.end(),comparePets);
     unique_copy(pets.begin(), pets.end(), uniquePets.begin(), comparePets);
     for_each(pets.begin(), pets.end(), print);
     cout << endl;
     for_each(uniquePets.begin(), uniquePets.end(), print);

    //cout << "Program ran";

 return 0;

}
