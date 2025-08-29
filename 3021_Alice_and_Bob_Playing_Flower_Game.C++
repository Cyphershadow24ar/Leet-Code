// PROBLEM :- (3021) Alice and Bob Playing Flower Game

// SOLUTION :

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string smallestWindow(string &s, string &p) {
        if (s.size() < p.size()) return "";

        // Step 1: Frequency map of string p
        vector<int> freqP(26, 0);
        for (char c : p) freqP[c - 'a']++;

        // Step 2: Sliding window
        vector<int> freqS(26, 0);
        int required = p.size(); // total chars needed
        int l = 0, minLen = INT_MAX, start = -1;

        for (int r = 0; r < s.size(); r++) {
            // include current char
            if (freqP[s[r] - 'a'] > 0) {
                if (freqS[s[r] - 'a'] < freqP[s[r] - 'a'])
                    required--;
                freqS[s[r] - 'a']++;
            }

            // Step 3: Contract window if valid
            while (required == 0) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    start = l;
                }

                if (freqP[s[l] - 'a'] > 0) {
                    freqS[s[l] - 'a']--;
                    if (freqS[s[l] - 'a'] < freqP[s[l] - 'a'])
                        required++;
                }
                l++;
            }
        }

        return (start == -1) ? "" : s.substr(start, minLen);
    }
};
