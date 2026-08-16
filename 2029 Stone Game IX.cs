// PROBLEM : (2029) Stone Game IX

// SOLUTION :

public class Solution {
    public bool StoneGameIX(int[] stones) {
        int[] cnt = new int[3];

        foreach(int stone in stones){
            cnt[stone % 3]++;
        }

        if(cnt[0] % 2 == 0){
            return cnt[1] >= 1 && cnt[2] >= 1;
        }

        return Math.Abs(cnt[1] - cnt[2]) > 2;
    }
}
