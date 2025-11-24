// PROBLEM: (1018) Binary Prefix Divisible By 5

// SOLUTION :
 class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>();
        int rem = 0;

        for (int bit : nums) {
            rem = (rem * 2 + bit) % 5;
            result.add(rem == 0);
        }

        return result;
    }
}
