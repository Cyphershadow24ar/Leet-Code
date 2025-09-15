// PROBLEM : (1935) Maximum Number of Words You Can Type

// SOLUTION :

#include <unordered_set>
#include <sstream>
#include <string>

using namespace std;

class Solution {
public:
    int canBeTypedWords(string text, string brokenLetters) {
        unordered_set<char> brokenSet;
        for (char c : brokenLetters) {
            brokenSet.insert(c);
        }
        
        int count = 0;
        string word;
        stringstream ss(text);
        
        while (ss >> word) {
            bool canType = true;
            for (char c : word) {
                if (brokenSet.count(c)) {
                    canType = false;
                    break;
                }
            }
            if (canType) count++;
        }
        
        return count;
    }
};
