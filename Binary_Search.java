public class Binary_Search {
    public static int search(int[] nums, int target) {
        int start=0;
        int end=nums.length-1;
        int num=-1;
        while(start<=end)
        {
            int mid=( start+end )/2;
            if(nums[mid]==target)
            {
                num= mid;
                break;
            }
            else if(nums[mid]>target)
                end=mid-1;
            else if(nums[mid]<target)
                start=mid+1;
        }
        return num;
    }

    public static void main(String[] args) {
        int[] arr = {1 , 2 , 6 , 7 , 12 , 23};
        int t = 23 ;
        System.out.println(search(arr , t));
    }
}
