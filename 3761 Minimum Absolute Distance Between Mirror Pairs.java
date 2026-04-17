// PROBLEM : (3761) Minimum Absolute Distance Between Mirror Pairs

// SOLUTION :

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        int mindist = Integer.MAX_VALUE;

        Map<Integer, Integer> lsrev = new HashMap<>();
        for(int j =0; j<n; j++){
            int currval = nums[j];
            if(lsrev.containsKey(currval)){
                int i = lsrev.get(currval);
                mindist = Math.min(mindist, j - i);
            }

            int rev = reverse(currval);
            lsrev.put(rev, j);
        }
        return (mindist == Integer.MAX_VALUE) ? -1 : mindist;
    }

    private int reverse(int x){
        int rev = 0;
        while(x > 0){
            rev = rev * 10 + (x % 10);
            x /= 10;
        }
        return rev;
    }
}
