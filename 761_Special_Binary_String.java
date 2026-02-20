// PROBLEM : (761) Special Binary String

// SOLUTION :

import java.util.*;

class Solution {
    public String makeLargestSpecial(String s) {

        List<String> parts = new ArrayList<>();
        int count = 0, start = 0;

        // Step 1: Split into primitive special substrings
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '1') count++;
            else count--;

            // found a balanced special substring
            if (count == 0) {
                // recursively process inside part
                String inside = s.substring(start + 1, i);
                String processed = "1" + makeLargestSpecial(inside) + "0";
                parts.add(processed);

                start = i + 1;
            }
        }

        // Step 2: Sort descending (lexicographically largest)
        Collections.sort(parts, Collections.reverseOrder());

        // Step 3: Join
        StringBuilder result = new StringBuilder();
        for (String part : parts)
            result.append(part);

        return result.toString();
    }
}
