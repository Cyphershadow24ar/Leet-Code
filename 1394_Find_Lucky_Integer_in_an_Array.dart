// PROBLEM :- 1394. Find Lucky Integer in an Array

class Solution {
  int findLucky(List<int> arr) {
    // We are using Map to count the fequency of each number
    Map<int, int> freqMap = {};

    for(int num in arr){
        freqMap[num] = (freqMap[num] ?? 0 ) + 1;
    }

    int result = -1;
    //We are Iterating through the Map to find the Lucky Integers
    for(int key in freqMap.keys){
        if(key == freqMap[key] && key > result){
            result = key;
        }
    }
    return result;
  }
}
