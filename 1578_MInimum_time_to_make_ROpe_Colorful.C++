// PROBLEM : (1578) Minimum Time to Make Rope Colorful

// SOLUTION :

class Solution {
public:
    int minCost(string colors, vector<int>& neededTime) {
        int totalTime = 0;
        
        for (int i = 1; i < colors.size(); i++) {
            if (colors[i] == colors[i - 1]) {
                // Add the smaller time to total
                totalTime += min(neededTime[i], neededTime[i - 1]);
                
                // Keep the balloon with the larger removal time
                neededTime[i] = max(neededTime[i], neededTime[i - 1]);
            }
        }
        return totalTime;
    }
};
