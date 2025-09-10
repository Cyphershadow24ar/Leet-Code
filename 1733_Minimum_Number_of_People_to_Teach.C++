// PROBLEM : (1733) Minimum Number of People to Teach

// SOLUTION :

#include <vector>
#include <unordered_set>
#include <unordered_map>
using namespace std;

class Solution {
public:
    int minimumTeachings(int n, vector<vector<int>>& languages, vector<vector<int>>& friendships) {
        int m = languages.size();
        vector<unordered_set<int>> langSet(m + 1);
        
        // Convert languages list into sets for fast lookup
        for (int i = 0; i < m; i++) {
            for (int lang : languages[i]) {
                langSet[i + 1].insert(lang);
            }
        }

        unordered_set<int> toTeachUsers;
        vector<pair<int, int>> problematicPairs;

        // Identify friendship pairs that cannot communicate
        for (auto &f : friendships) {
            int u = f[0], v = f[1];
            bool canCommunicate = false;
            
            for (int l : langSet[u]) {
                if (langSet[v].count(l)) {
                    canCommunicate = true;
                    break;
                }
            }

            if (!canCommunicate) {
                problematicPairs.push_back({u, v});
                toTeachUsers.insert(u);
                toTeachUsers.insert(v);
            }
        }

        int minTeach = m + 1;  // Start with a large number

        // Try teaching each language and count how many people need to learn it
        for (int lang = 1; lang <= n; lang++) {
            int teachCount = 0;

            for (int user : toTeachUsers) {
                if (langSet[user].count(lang) == 0) {
                    teachCount++;
                }
            }

            minTeach = min(minTeach, teachCount);
        }

        return minTeach;
    }
};
