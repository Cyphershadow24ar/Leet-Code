// PROBLEM : (3003) Maximize the Number of Partitions After Operations

// SOLUTION :

class Solution {
public:
    int maxPartitionsAfterOperations(string s, int k) {
        int n = s.size();
        int basePartitions = countPartitions(s, -1, k);
        int maxPartitions = basePartitions;
        
        for (int i = 0; i < n; i++) {
            int partitions = countPartitions(s, i, k);
            if (partitions > maxPartitions) {
                maxPartitions = partitions;
            }
        }
        return maxPartitions;
    }
    
private:
    int countPartitions(string s, int changeIndex, int k) {
        int partitions = 0;
        int currentDistinct = 0;
        vector<int> freq(26, 0);
        
        for (int j = 0; j < s.size(); j++) {
            if (j == changeIndex) {
                currentDistinct++;
            } else {
                int idx = s[j] - 'a';
                if (freq[idx] == 0) {
                    currentDistinct++;
                }
                freq[idx]++;
            }
            
            if (currentDistinct > k) {
                partitions++;
                currentDistinct = 0;
                fill(freq.begin(), freq.end(), 0);
                
                if (j == changeIndex) {
                    currentDistinct = 1;
                } else {
                    int idx = s[j] - 'a';
                    freq[idx] = 1;
                    currentDistinct = 1;
                }
            }
        }
        
        if (currentDistinct > 0) {
            partitions++;
        }
        return partitions;
    }
};
