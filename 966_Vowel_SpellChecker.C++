// PORBLEM : (966) Vowel Spellchecker

// SOLUTOIN :

class Solution {
public:
    vector<string> spellchecker(vector<string>& wordlist, vector<string>& queries) {
        unordered_set<string> exactSet(wordlist.begin(), wordlist.end());
        unordered_map<string, string> lowerMap;
        unordered_map<string, string> vowelMap;
        
        // Helper lambda to lowercase a string
        auto toLower = [](string s) {
            for (auto &ch : s) ch = tolower(ch);
            return s;
        };
        
        // Helper lambda to convert vowels into '*'
        auto devowel = [&](string s) {
            for (auto &ch : s) {
                char low = tolower(ch);
                if (low=='a' || low=='e' || low=='i' || low=='o' || low=='u') ch = '*';
                else ch = low;
            }
            return s;
        };
        
        // Preprocessing wordlist
        for (string &word : wordlist) {
            string lower = toLower(word);
            if (!lowerMap.count(lower)) lowerMap[lower] = word;
            
            string vow = devowel(word);
            if (!vowelMap.count(vow)) vowelMap[vow] = word;
        }
        
        vector<string> ans;
        for (string &query : queries) {
            if (exactSet.count(query)) {
                ans.push_back(query);
            } else {
                string lower = toLower(query);
                if (lowerMap.count(lower)) {
                    ans.push_back(lowerMap[lower]);
                } else {
                    string vow = devowel(query);
                    if (vowelMap.count(vow)) {
                        ans.push_back(vowelMap[vow]);
                    } else {
                        ans.push_back("");
                    }
                }
            }
        }
        
        return ans;
    }
};
