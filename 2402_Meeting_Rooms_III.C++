// PROBLEM :- (2402) Meeting Rooms III

// SOLUTION :-

class Solution {
public:
    int mostBooked(int n, vector<vector<int>>& meetings) {
        sort(meetings.begin(), meetings.end());  // Sort by start time

        priority_queue<int, vector<int>, greater<int>> available;  // min-heap for available rooms
        for (int i = 0; i < n; ++i)
            available.push(i);

        using pii = pair<long long, int>;  // {end time, room number}
        priority_queue<pii, vector<pii>, greater<pii>> busy;  // min-heap for busy rooms

        vector<int> count(n, 0);  // Count of meetings per room

        for (const auto& meeting : meetings) {
            long long start = meeting[0], end = meeting[1];
            long long duration = end - start;

            // Free up rooms that have become available by the current meeting's start time
            while (!busy.empty() && busy.top().first <= start) {
                available.push(busy.top().second);
                busy.pop();
            }

            if (!available.empty()) {
                // Assign the meeting to the smallest available room
                int room = available.top(); available.pop();
                count[room]++;
                busy.push({start + duration, room});
            } else {
                // Delay the meeting until the earliest room is free
                auto [nextFreeTime, room] = busy.top(); busy.pop();
                count[room]++;
                busy.push({nextFreeTime + duration, room});
            }
        }

        // Find the room with the maximum meeting count
        int maxMeetings = 0, resRoom = 0;
        for (int i = 0; i < n; ++i) {
            if (count[i] > maxMeetings) {
                maxMeetings = count[i];
                resRoom = i;
            }
        }

        return resRoom;
    }
};
