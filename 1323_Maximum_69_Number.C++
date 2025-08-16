// PROBLEM : (1323) Maximum 69 Number

// SOLUTION :-

class Solution {
public:
    int maximum69Number (int num) {
        string s = to_string(num);   // convert number to string
        for (char &c : s) {
            if (c == '6') {          // find the first '6'
                c = '9';             // change it to '9'
                break;               // only one change allowed
            }
        }
        return stoi(s);              // convert back to int
    }
};
