// PROBLEM : (2125) Number of Laser Beams in a Bank

// SOLUTION :

class Solution {
public:
    int numberOfBeams(vector<string>& bank) {
        int totalBeams = 0;
        int prev = 0; // number of devices in previous non-empty row

        for (string& row : bank) {
            int curr = count(row.begin(), row.end(), '1'); // count devices in current row
            if (curr > 0) {
                totalBeams += prev * curr; // beams formed with previous row
                prev = curr; // update previous
            }
        }

        return totalBeams;
    }
};
