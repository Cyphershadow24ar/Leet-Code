// PROBLEM : (1536) Minimum Swaps to Arrange a Binary Grid

// SOLUTION :

class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] t_zero = new int[n];
        
        for(int i=0; i<n; i++){
            int count =0;
            for(int j = n-1; j>= 0; j--){
                if(grid[i][j] == 0){
                    count++;
                } else{
                    break;
                }
            }
            t_zero[i] = count;   
        }

        int totalswap = 0;

        for(int i=0; i<n; i++){
            int r_zero = n-1 -i;
            int f_idx = -1;

            for(int j=i; j<n; j++){
                if(t_zero[j] >= r_zero){
                    f_idx = j;
                    break;
                }
            }

            if(f_idx == -1) return -1;

            for(int j= f_idx; j>i ; j--){
                int temp = t_zero[j];
                t_zero[j] = t_zero[j-1];
                t_zero[j-1] = temp;
                totalswap++;
            }
        }
        return totalswap;
    }
}
