// PROBLEM : (1625) Lexicographically Smallest String After Applying Operations 

// SOLUTION :

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string findLexSmallestString(string s, int a, int b) {
        queue<string> q;
        unordered_set<string> visited;
        string ans = s;

        q.push(s);
        visited.insert(s);

        while (!q.empty()) {
            string cur = q.front(); q.pop();
            ans = min(ans, cur);

            // Operation 1: Add 'a' to all odd indices
            string addOp = cur;
            for (int i = 1; i < addOp.size(); i += 2) {
                addOp[i] = ( (addOp[i] - '0' + a) % 10 ) + '0';
            }
            if (!visited.count(addOp)) {
                visited.insert(addOp);
                q.push(addOp);
            }

            // Operation 2: Rotate right by b
            string rotOp = cur.substr(cur.size() - b) + cur.substr(0, cur.size() - b);
            if (!visited.count(rotOp)) {
                visited.insert(rotOp);
                q.push(rotOp);
            }
        }

        return ans;
    }
};
