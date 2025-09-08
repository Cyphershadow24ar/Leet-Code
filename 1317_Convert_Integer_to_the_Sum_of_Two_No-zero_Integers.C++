// PROBLEM : (1317) Convert Integer to the Sum of Two No-Zero Integers

// SOLUTION :

class Solution {
public:

    bool isNoZero(int num){
        while(num > 0){
            if(num % 10 == 0) return false;
            num /= 10;
        }
        return true;
    }

    vector<int> getNoZeroIntegers(int n) {
        for(int a =1; a<n; ++a){
            int b = n-a;
            if(isNoZero(a) && isNoZero(b)){
                return{a,b};
            }
        }
        return {};
    }
};
