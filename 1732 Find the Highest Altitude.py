# PROBLEM : (1732) Find the Highest Altitude

# SOLUTION :

class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        curr_alt = 0
        high_p = curr_alt

        for alt_gain in gain:
            curr_alt += alt_gain
            high_p = max(high_p, curr_alt)

        return high_p

