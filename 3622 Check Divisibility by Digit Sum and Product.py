# PROBLEM : (3622) Check Divisibility by Digit Sum and Product

# SOLUTION :

class Solution:
    def checkDivisibility(self, n: int) -> bool:
        t, s, p = n, 0, 1
        while t > 0:
            d = t % 10
            s += d
            p *= d
            t //= 10
        return n % (s + p) == 0
