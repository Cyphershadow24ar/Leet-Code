# PROBLEM : (3731) Find Missing Elements

# SOLUTION :

class Solution:
    def findMissingElements(self, nums: List[int]) -> List[int]:
        nums.sort()
        a=[]
        j=0
        for i in range(nums[0],nums[-1]):
            if nums[j]!=i:
                a.append(i)
            else:
                j+=1
        return a
