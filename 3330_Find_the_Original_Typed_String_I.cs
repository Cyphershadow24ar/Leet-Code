// PROBLEM :- 3330. Find the Original Typed String I

class Solution {
public:
    int possibleStringCount(string word) {
        int n = word.length();
        int count = 0;
        int i=0;

        while(i<n){
            int j = i;
            // for moving J to the end of the curret group
            while(j < n && word[j] == word[i]){
                j++;
            }
            int grouplength = j - i;
            if(grouplength > 1){
                count += grouplength - 1;
            }
            i = j;
        }
        // with this +1 to include the orginial word
        return count + 1;
    }
};
