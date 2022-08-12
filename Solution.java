import java.util.ArrayList;

class Solution {
    public static void main(String[] args) {
        // int[] lis = {10,9,2,5,3,7,101,18};
        int[] lis = {0,1,0,3,2,3};
        lengthOfLIS(lis);
    }
    public static int lengthOfLIS(int[] nums) {
        // 0,1,0,3,2,3
        // 10,9,2,5,3
        // 2,5,3
        //[10,9,2,5,3,7,101,18]
        ArrayList<Integer> longestSubsequence = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            ArrayList<Integer> subsequence = new ArrayList<Integer>();
            subsequence.add(nums[i]);
            int subsequenceCounter = 0;
            for(int j = i; j < nums.length; j++){
                if(subsequence.get(subsequenceCounter) < nums[j]){
                    // System.out.println("i: "+i+" - j: "+j);
                    subsequence.add(nums[j]);
                    subsequenceCounter++;
                }
            }
            if(subsequence.size() > longestSubsequence.size()){
                System.out.println(subsequence.toString());
                System.out.println(longestSubsequence.toString());
                longestSubsequence = subsequence;
            }
        }
        // System.out.println(longestSubsequence.toString());
        return longestSubsequence.size();
    }
}
