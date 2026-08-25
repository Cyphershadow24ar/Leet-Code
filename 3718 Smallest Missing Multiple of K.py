# PROBLEM : (3718) Smallest Missing Multiple of K

# SOLUTION :

class Solution:
    def missingMultiple(self, nums: List[int], k: int) -> int:
        seen = set(nums)
        ans = k
        while ans in seen:
            ans += k
        return ans

        
