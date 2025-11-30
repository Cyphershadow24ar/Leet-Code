// PROBLEM : (1590) Make Sum Divisible by P

// SOLUTION :

class Solution {
    public int minSubarray(int[] nums, int p) {
        long total = 0;
        for (int x : nums) total += x;
        
        int need = (int)(total % p);
        if (need == 0) return 0; // Already divisible

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Prefix before array starts
        long prefix = 0;
        int ans = nums.length;

        for (int i = 0; i < nums.length; i++) {
            prefix = (prefix + nums[i]) % p;
            int target = (int)((prefix - need + p) % p);

            if (map.containsKey(target)) {
                ans = Math.min(ans, i - map.get(target));
            }

            map.put((int)prefix, i);
        }

        return ans == nums.length ? -1 : ans;
    }
}
