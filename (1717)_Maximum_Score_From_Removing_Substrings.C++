// PROBLEM :- (1717) Maximum Score From Removing Substrings

// SOLUTION :-

class Solution {
public:
    int maximumGain(string s, int x, int y) {
        char firstA, firstB;
        int firstScore, secondScore;

        if(x > y){
            firstA = 'a'; firstB ='b';
            firstScore = x; secondScore = y;
        } else {
            firstA = 'b'; firstB = 'a';
            firstScore = y; secondScore = x;
        }

        string temp;
        long long score = 0;
        for(char c : s){
            if(!temp.empty() && temp.back() == firstA && c == firstB){
                temp.pop_back();
                score += firstScore;
            } else{
                temp.push_back(c);
            }
        }

        string finalStr;
        for(char c : temp){
            if(!finalStr.empty() && finalStr.back() == firstB && c == firstA){
                finalStr.pop_back();
                score += secondScore;
            }else{
                finalStr.push_back(c);
            }
        }
        return score;
    }
};
