import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Test {


    public static void main(String[] args) throws UnsupportedEncodingException {
        int[] nums = {2};
        System.out.println(removeElement(nums, 3));
        System.out.println(Arrays.toString(nums));

    }

    public static int removeElement(int[] nums, int val) {
        int index = 0, len = nums.length - 1;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {
                continue;
            } else {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

}