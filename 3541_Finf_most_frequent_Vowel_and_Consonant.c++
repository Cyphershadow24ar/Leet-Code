// PROBLEM: (3541) Find Most Frequent Vowel and Consonant

// SOLUTION: 

#include <iostream>
#include <string>
#include <unordered_map>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxFreqSum(string s) {
        unordered_map<char, int> freq;
        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;
        string vowels = "aeiou";
        
        // Count frequency of each character
        for (char c : s) {
            freq[c]++;
        }
        
        // Find max frequency for vowels and consonants
        for (auto& p : freq) {
            char c = p.first;
            int count = p.second;
            if (vowels.find(c) != string::npos) {
                maxVowelFreq = max(maxVowelFreq, count);
            } else {
                maxConsonantFreq = max(maxConsonantFreq, count);
            }
        }
        
        return maxVowelFreq + maxConsonantFreq;
    }
};

