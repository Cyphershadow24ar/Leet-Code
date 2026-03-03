# PROBLEM : (1545) Find Kth Bit in Nth Binary String

# SOLUTION :

class Solution:
    def findKthBit(self, n: int, k: int) -> str:
        if n == 1:
            return "0"
        
        length = (2 ** n) - 1
        mid = (length // 2) + 1
        
        if k == mid:
            return "1"
        elif k < mid:
            return self.findKthBit(n - 1, k)
        else:
            bit = self.findKthBit(n - 1, length - k + 1)
            return "1" if bit == "0" else "0"
