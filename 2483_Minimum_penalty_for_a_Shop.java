// PORBLEM : (2483) Minimum penalty for a Shop

// SOLUTION :

class Solution {
    public int bestClosingTime(String customers) {
        int penalty = 0;
        for(char c : customers.toCharArray()){
            if( c == 'Y') penalty++;
        }

        int minPenalty = penalty;
        int bestHour = 0;

        for(int i =0; i< customers.length(); i++){
            if(customers.charAt(i) == 'Y'){
                penalty--;
            } else{
                penalty++;
            }
            if(penalty < minPenalty){
                minPenalty = penalty;
                bestHour = i + 1;
            }
        }

        return bestHour;
    }
}
