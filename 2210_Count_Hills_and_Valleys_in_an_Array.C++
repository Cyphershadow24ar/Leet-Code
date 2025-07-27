// PROBLEM :- (2210) Count Hills and Valleys in an Array

// SOLUTION :-

class Solution {
public:
    int countHillValley(vector<int>& nums) {
      vector<int>arr;
      arr.push_back(nums[0]);
      for(int i=1; i<nums.size(); i++){
        if(nums[i] != nums[i-1]){
            arr.push_back(nums[i]);
        }
      }

      int count =  0;
      for(int i=1 ; i<arr.size()-1; i++){
        if((arr[i] > arr[i-1] && arr[i] > arr[i+1])|| (arr[i] < arr[i-1] && arr[i] < arr[i+1])){
            count++;
        }
      }
      return count;  
    }
};
