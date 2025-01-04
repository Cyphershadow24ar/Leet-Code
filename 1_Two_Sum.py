# PROBLEM: Two Sum

class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """

        total_value = {} 

        for i, num in enumerate(nums):  
            value = target - num
            if value in total_value:
                return [total_value[value], i]

            total_value[num] = i

