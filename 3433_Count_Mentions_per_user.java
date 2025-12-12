// PROBLEM : (3433) Count Mentions Per User

// SOLUTION : 
import java.util.*;

class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        // 1. Sort the events
        // Primary Key: Timestamp (Ascending)
        // Secondary Key: Event Type ("OFFLINE" before "MESSAGE")
        Collections.sort(events, (a, b) -> {
            int timeA = Integer.parseInt(a.get(1));
            int timeB = Integer.parseInt(b.get(1));
            
            // Compare timestamps
            if (timeA != timeB) {
                return Integer.compare(timeA, timeB);
            }
            
            // If timestamps are equal, we need OFFLINE to come before MESSAGE
            // to ensure status updates happen before we check who is "HERE".
            String typeA = a.get(0);
            String typeB = b.get(0);
            
            if (typeA.equals("OFFLINE") && typeB.equals("MESSAGE")) return -1;
            if (typeA.equals("MESSAGE") && typeB.equals("OFFLINE")) return 1;
            return 0;
        });

        int[] mentions = new int[numberOfUsers];
        // Stores the timestamp when the user comes back online. 
        // Initially 0, so everyone is online at t >= 1.
        int[] onlineTime = new int[numberOfUsers];

        // 2. Process events
        for (List<String> event : events) {
            String type = event.get(0);
            int timestamp = Integer.parseInt(event.get(1));
            String data = event.get(2);

            if (type.equals("OFFLINE")) {
                int userId = Integer.parseInt(data);
                // User becomes offline at 'timestamp'.
                // They stay offline for 60 units and return at timestamp + 60.
                onlineTime[userId] = timestamp + 60;
            } else {
                // MESSAGE Event
                if (data.equals("ALL")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        mentions[i]++;
                    }
                } else if (data.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        // User is online if current time is past or equal to their return time
                        if (timestamp >= onlineTime[i]) {
                            mentions[i]++;
                        }
                    }
                } else {
                    // Specific IDs, e.g., "id0 id1 id0"
                    String[] tokens = data.split(" ");
                    for (String token : tokens) {
                        // Skip the "id" prefix and parse the number
                        int userId = Integer.parseInt(token.substring(2));
                        mentions[userId]++;
                    }
                }
            }
        }

        return mentions;
    }
}
