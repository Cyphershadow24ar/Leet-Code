// PROBLEM : (2833 Furthest Point From Origin

// SOLUTION :

class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int left = 0, right = 0, blank = 0;
        
        for(int i = 0; i < moves.length(); i++){
            char ch = moves.charAt(i);
            
            if(ch == 'L') left++;
            else if(ch == 'R') right++;
            else blank++; // '_'
        }
        
        return Math.abs(right - left) + blank;
    }
}
