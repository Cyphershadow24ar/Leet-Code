// PROBLEM: Longest Palindromic Substring 

class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        if len(s) == 0:
            return ""

        start, end = 0,0

        def expand_around_center(left,right):
            while left >= 0 and right < len(s) and s[left] == s[right]:
                left -= 1
                right += 1

            return right - left - 1

        for i in range(len(s)):
            len1 = expand_around_center(i,i)
            len2 = expand_around_center(i, i+1)
            max_len = max(len1,len2)

            if max_len > (end - start):
                start = i - (max_len - 1 ) // 2
                end = i + max_len // 2

        return s[start:end + 1]
