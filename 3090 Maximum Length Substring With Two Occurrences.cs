// PROBLEM : (3090) Maximum Length Substring With Two Occurrences

// SOLUTION :

public class Solution {
    public int MaximumLengthSubstring(string s) {
        int[] freq = new int[26];
        int left = 0;
        int maxlen = 0;

        for(int right = 0 ; right < s.Length ; right++){
            freq[s[right] - 'a']++;
            while(freq[s[right] - 'a'] > 2){
                freq[s[left] - 'a']--;
                left++;
            }
            maxlen = Math.Max(maxlen, right - left + 1);
        }
        return maxlen;
    }
}
