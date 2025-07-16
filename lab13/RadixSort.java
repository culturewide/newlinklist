/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */

/**
 *1. 假如列表是b，c，a为结尾   需要记录他们出现的次数，也就是count数组，
 * 数组长度应该是ascii表的长度，（0，256）因为都有可能出现，2.计数完成，考虑start数组，
 * 读取到a，位置为0（一开始），如果有两个a，start【 b】为2，长度依然为（0，256）。
 * 3.第三步 开始填空新数组  sorted【】，长度为原数组的length， 开始读取原数组的第一个元素，第一个元素为b，
 * 获取start中b的位置，先搞一个中间量    temp= init【0】。start【temp】=2，sorted【pos】=init【0】；
 * 完成拷贝
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        //find the max length of character
        int max = 0;
        for (int i = 0; i < asciis.length; i++) {

            if(asciis[i].length() > max) {
                max = asciis[i].length();
            }
        }
        // 1. 关键一步：创建原始数组的副本。后续所有操作都在这个副本上进行。
        String[] sortedCopy = new String[asciis.length];
        for(int i = 0; i < asciis.length; i++) {
            sortedCopy[i] = asciis[i];
        }
        for(int i = 0; i < sortedCopy.length; i++) {
            if(sortedCopy[i].length() < max){
              sortedCopy[i] =  addSpace(sortedCopy[i],max);
            }
        }
        for(int d = 0; d < max; d++) {
            sortHelperLSD(sortedCopy,d,max);
        }
        for(int i = 0; i < sortedCopy.length; i++) {
           sortedCopy[i] =  removePadding(sortedCopy[i]);
        }
        return sortedCopy;
    }
    private static String removePadding(String string) {
        int i = string.length() - 1;
        while(i!= 0 && string.charAt(i) == '_') {
            i--;
        }
        return string.substring(0, i+1);
    }
    private static String addSpace(String ascii, int max) {
        int length = ascii.length();
        char holder =  '_';
        while(length < max){
            ascii += holder;
            length++;
        }
        return ascii;
    }
    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index, int max) {
        // Optional LSD helper method for required LSD radix sort
        int [] count = new int [256];
        //完成对所有的字符的计数
        for(int i = 0 ; i < asciis.length; i++){
            count [(int)asciis[i] .charAt(max - index - 1)]++;
        }
        int [] start = new int [256];
        int pos = 0;
        for(int i = 0 ; i < 256; i++){
            start[i] = pos;
            pos += count[i];
        }
        String [] sorted = new String[asciis.length];
        for(int i = 0 ; i < asciis.length; i++){
            int temp = (int)asciis[i].charAt(max - index - 1);
            int num = start[temp];
            sorted[num] = asciis[i];
            start[temp]++;
        }
        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted[i];
        }


    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    public static void main(String[] args) {
        String[] st = new String[]{"2","100","bbbb"};
        String[] sorted = sort(st);
        for(int i = 0 ; i < st.length; i++){
            System.out.println(st[i]);
        }
        for(int i = 0 ; i < sorted.length; i++){
            System.out.println(sorted[i]);
        }
    }

}
