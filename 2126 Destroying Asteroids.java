// PROBLEM : (2126) Destroying Asteroids

// SOLUTION :

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long currmass = mass;
        for(int ass : asteroids){
            if(currmass < ass){
                return false;
            }
            currmass += ass;
        }
        return true;
    }
}
