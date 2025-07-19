// PROBLEM - (1233) Remove Sub-Folders from the Filesystem

// SOLUTION :-

class Solution {
public:
    vector<string> removeSubfolders(vector<string>& folder) {
        sort(folder.begin(), folder.end());
        vector<string> result;
        
        for (const string& f : folder) {
            if (result.empty() || f.find(result.back() + "/") != 0) {
                result.push_back(f);
            }
        }
        return result;
    }
};
