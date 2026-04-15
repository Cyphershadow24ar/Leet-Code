// PROBLME : (2515) Shortest Distance to Target String in a Circular Array

// SOLUTOIN :

class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int mindist = Integer.MAX_VALUE;

        for(int i =0; i<n; i++){
            if(words[i].equals(target)){
                int dist = Math.abs(i - startIndex);
                int minstep = Math.min(dist, n-dist);
                mindist = Math.min(mindist, minstep);
            }
        }
        return mindist == Integer.MAX_VALUE ? -1 : mindist;
    }
}
