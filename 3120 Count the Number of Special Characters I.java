// PROBLEM : (3120) Count the Number of Special Characters I

// SOLUTION :

class Solution {
    public int numberOfSpecialChars(String word) {
        Set<Character> s = new HashSet<>();
        for(char  c : word.toCharArray()){
            s.add(c);
        }
        int cnt = 0;
        for(char  c = 'a'; c <= 'z' ; c++){
            if(s.contains(c) && s.contains((char) (c- 'a' + 'A'))){
                cnt++;
            }
        }
        return cnt;
    }
}
