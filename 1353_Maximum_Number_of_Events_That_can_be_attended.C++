/*
PROBLEM - 1353. Maximum Number of Events That Can Be Attended
*/

// SOLUTION:- 

class Solution {
public:
    int maxEvents(vector<vector<int>>& events) {
      
        // 1. Sort the Events by start day
        sort(events.begin(), events.end());

        priority_queue<int, vector<int>, greater<int>> minHeap; // Using Min Heap for end Days
        int day = 1, i= 0, n =events.size(), count = 0;

        // 2. Now find the last day among all events
        int lastDay = 0;
        for(const auto& event : events){
            lastDay = max(lastDay, event[1]);
        }

        // 3. Iterate day by day
        for(day =1; day <= lastDay; ++day){
            // It will push all the events start today.
            while((i < n && events[i][0] == day)){
                minHeap.push(events[i][1]);
                i++;
            }

            while(!minHeap.empty() && minHeap.top() < day){
                minHeap.pop();
            }

            if(!minHeap.empty()){
                minHeap.pop();
                count++;
            }
        }
        return count;
    }
};
