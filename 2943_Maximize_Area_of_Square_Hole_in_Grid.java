// PROBLEM : (2943) Maximize Area of Square Hole in Grid

// SOLUITON :

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int max_hori = 0;
        int max_ver = 0;
        int cnt =  0;

        for(int i=0;i<hBars.length-1;i++){

            if(hBars[i+1]-hBars[i] == 1 ) cnt +=1;
            else cnt=0;

            max_hori = Math.max(max_hori,cnt);
        }

        cnt = 0;

        for(int i=0;i<vBars.length-1;i++){

            if(vBars[i+1]-vBars[i] == 1 ) cnt +=1;
            else cnt=0;

            max_ver = Math.max(max_ver,cnt);
        }

        int side = Math.min(max_hori,max_ver) + 2;

        return side*side;
    }
}
