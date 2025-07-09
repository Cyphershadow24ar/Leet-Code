// PROBLEM - (3439) Reschedule Meetings for Maximum Free Time I

// SOLUTION :-

class Solution {
public:
    int maxFreeTime(int eventTime, int k, vector<int>& startTime, vector<int>& endTime) {
        int n = startTime.size();
        vector<pair<int,int>> vp(n);
        for(int i = 0;i < n;i++){
            vp[i] = {startTime[i],endTime[i]};
        }
        vector<int> arr;
        arr.push_back(vp[0].first);
        for(int i = 0;i < n - 1;i++){
            arr.push_back(vp[i + 1].first - vp[i].second);
        }
        arr.push_back(eventTime - vp.back().second);
        int maxsum = 0;
        int sum = 0;
        n = arr.size();
        k++;
        for(int i = 0;i < min(n,k);i++){
            sum += arr[i];
        }
        maxsum = max(maxsum,sum);
        for(int i = k;i < n;i++){
            sum -= arr[i - k];
            sum += arr[i];
            maxsum = max(maxsum,sum);
        }
        return maxsum;
    }
};
