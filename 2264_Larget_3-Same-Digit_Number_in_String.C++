/* PROBLEM :- (2264) Largest 3-Same-Digit Number in String

SOLUTION :-
*/

class Solution {
public:
    string largestGoodInteger(string num) {
        string largest = "";
        for (int i = 0; i <= (int)num.size() - 3; i++) {
            if (num[i] == num[i+1] && num[i] == num[i+2]) {
                string good = num.substr(i, 3);
                if (good > largest) {
                    largest = good;
                }
            }
        }
        return largest;
    }
};
