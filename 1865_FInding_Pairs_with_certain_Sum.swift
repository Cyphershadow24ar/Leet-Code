
// PROBLEM : 1865 - Finding Pairs With a Certain Sum

// SOLUTION :

import Foundation

class FindSumPairs {
    private var nums1: [Int]
    private var nums2: [Int]
    private var counts2: [Int : Int]

    init(_ nums1: [Int], _ nums2: [Int]) {
        self.nums1 = nums1
        self.nums2 = nums2
        self.counts2 = [:]

        for num in nums2{
            counts2[num, default: 0] += 1
        }
    }
    
    func add(_ index: Int, _ val: Int) {
        let oldValue = nums2[index]

        counts2[oldValue]! -= 1

        if counts2[oldValue] == 0{
            counts2[oldValue] = nil
        }
        nums2[index] += val

        let newValue = nums2[index]

        counts2[newValue, default: 0] += 1
    }
    
    func count(_ tot: Int) -> Int {
        var totalPairs = 0

        for num1Val in nums1{
            let target = tot - num1Val
            totalPairs += counts2[target, default: 0]
        }
        return totalPairs
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * let obj = FindSumPairs(nums1, nums2)
 * obj.add(index, val)
 * let ret_2: Int = obj.count(tot)
 */
