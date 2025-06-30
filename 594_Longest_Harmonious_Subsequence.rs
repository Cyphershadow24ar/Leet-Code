// PROBLEM - 594. Longest Harmonious Subsequence

use std::collections::HashMap;

impl Solution {
    pub fn find_lhs(nums: Vec<i32>) -> i32 {
        let mut count_map = HashMap::new();

        // for Count frequencies of all numbers
        for &num in nums.iter(){
            *count_map.entry(num).or_insert(0) +=1;
        }        

        let mut result = 0;
         // for checking the pairs where key + 1 exits ( number)
        for(&num, &count) in count_map.iter(){
            if let Some(&next_count) = count_map.get(&(num + 1)){
                result = result.max(count + next_count);
            }
        }
        result
    }
}
