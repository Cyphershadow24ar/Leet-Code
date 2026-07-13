# PROBLEM : (1291) Sequential Digits

# SOLUTION :

class Solution:
    q = [*range(1,10)]

    for x in q:
        d= x % 10
        if d < 9:
            q.append(x * 10 + d + 1)

    def sequentialDigits(self, low: int, high: int) -> List[int]:
        return [x for x in self.q if low <= x <= high]
        
