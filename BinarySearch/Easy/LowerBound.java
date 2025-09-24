package BinarySearch.Easy;

public class LowerBound {
    //Brute force
    public int lowerBound1(int[] nums, int x) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i]>=x){
                return i;
            }
        }
        return nums.length;
    }

    //Optimal
    public int lowerBound2(int[] nums, int x) {
        int left = 0;
        int right = nums.length;
        while(left<right){
            int mid = left + (right-left)/2;
            if(nums[mid] < x){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
}
