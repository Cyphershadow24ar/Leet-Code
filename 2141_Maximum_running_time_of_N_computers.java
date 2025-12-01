// PROBLEM : (2141) Maximum Running Time of N Computers

// SOLUTION :

class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sum=0;
        int max=0;
        for(int i=0;i<batteries.length;i++){
            sum+=batteries[i];
            if(batteries[i]>max){
                max=batteries[i];
            }
        }
        long right=sum/n;
        if(right>=max){
            return right;
        }
        long left=0;
        long result=0;
        while(left<=right){
           long mid=left+(right-left)/2;
           if(canCoverAllComputer(batteries,n,mid)){
               result=mid;
               left=mid+1;
           }else{
               right=mid-1;
           }
        }
        return result;
    }
    
    public boolean canCoverAllComputer(int[] batteries,int n,long time){
        long sum=0;
        for(int i=0;i<batteries.length;i++){
            if(batteries[i]>=time){
                n--;
            }else{
                sum+=batteries[i];
            }
            if(sum>=(long)n*time){
                return true;
            }
        }
        return false;
    }
}
