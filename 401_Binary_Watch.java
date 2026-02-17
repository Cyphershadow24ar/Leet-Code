// PROBLEM : (401) Binary Watch

// SOLUTION :

import java.util.*;

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {

        List<String> ans = new ArrayList<>();

        for(int hour = 0; hour < 12; hour++) {
            for(int minute = 0; minute < 60; minute++) {

                if(Integer.bitCount(hour) + Integer.bitCount(minute) == turnedOn) {

                    // format minute (2 digits)
                    String time = hour + ":" + (minute < 10 ? "0" + minute : minute);
                    ans.add(time);
                }
            }
        }

        return ans;
    }
}
