// PROBLEM : (2273) Find Resultant Array After Removing Anagrams

// SOLUTION :

class Solution {
public:
    vector<string> removeAnagrams(vector<string>& words) {
        vector<string> result;
        string prev = "";  // store sorted version of last kept word
        
        for (auto &word : words) {
            string sortedWord = word;
            sort(sortedWord.begin(), sortedWord.end());
            
            if (sortedWord != prev) {
                result.push_back(word);
                prev = sortedWord;
            }
        }
        
        return result;
    }
};
