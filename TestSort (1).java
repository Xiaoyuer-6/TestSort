import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GaoBo
 * Date: 2021-01-31
 * Time: 12:17
 */
public class TestSort {

    /**
     * 时间复杂度：
     * 最坏情况下：当数据是无序的情况下：O(n^2)
     * 最好情况下：当数据有序的时候，可以达到O(n)
     *     所以：结论：越有序越快。
     *     题目：当前有一组待排序序列，但是这组待排序序列大部分是有序的。
     *         请问，下面那个排序更适合    直接插入排序。
     *     另外：直接插入排序一般也会用在一些排序的优化上。
     *          快速排序。
     * 空间复杂度：
     * 稳定性：稳定的排序
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i-1;
            for (; j >= 0 ; j--) {
                //如果这里是一个大于等于号 此时这个排序就不稳定了
                if(array[j] > tmp) {
                    array[j+1] = array[j];
                }else {
                    //array[j+1] = tmp;
                    break;
                }
            }
            array[j+1] = tmp;
        }
    }


    public static void shell(int[] array ,int gap) {
        for (int i = gap; i < array.length; i++) {
            int tmp = array[i];
            int j = i-gap;
            for (; j >= 0 ; j = j-gap) {
                //如果这里是一个大于等于号 此时这个排序就不稳定了
                if(array[j] > tmp) {
                    array[j+gap] = array[j];
                }else {
                    //array[j+1] = tmp;
                    break;
                }
            }
            array[j+gap] = tmp;
        }
    }

    /**
     * 了解：
     * 时间复杂度：O(1.5)  O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     * @param array
     */
    public static void shellSort(int[] array) {
        int[] drr = {5,3,1};//增量数组-->   16   5     3     1
        for (int i = 0; i < drr.length; i++) {
            shell(array,drr[i]);
        }
    }


    /**
     * 选择排序：
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     * @param array
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[j] < array[i]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }

    /**
     * 堆排序：
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     */

    /**
     * 冒泡排序：
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     */


    public static int pivot(int[] array,int start,int end) {
        int tmp = array[start];
        while (start < end) {
            while (start < end && array[end] >= tmp) {
                end--;
            }
            array[start] = array[end];
            //把数据赋值给start
            while (start < end && array[start] <= tmp) {
                start++;
            }
            //把start下标的值给end
            array[end] = array[start];
        }
        array[start] = tmp;
        return start;
    }




    public static void swap(int[] array,int k,int i) {
        int tmp = array[k];
        array[k] = array[i];
        array[i] = tmp;
    }
    public static void medianOfThree(int[] array,int low,int high) {
        int mid = (low+high)/2;
        //array[mid] <= array[low] <= array[end]
        if(array[low] < array[mid]) {
            swap(array,low,mid);
        }//array[mid] <= array[low]
        if(array[low] > array[high]) {
            swap(array,low,high);
        }//array[low] <= array[high]
        if(array[mid] > array[high]) {
            swap(array,mid,high);
        }//array[mid] <= array[high]
    }

    public static void quick(int[] array,int low,int high) {
        if(low < high) {
            //这个之前进行优化
            medianOfThree(array,low,high);
            int piv = pivot(array,low,high);
            quick(array,low,piv-1);
            quick(array,piv+1,high);
        }
    }

    /**
     * 时间复杂度： 最好情况：O(nlogn)     最坏情况有序的情况：O(N^2)
     * 空间复杂度：O(logn)
     * 稳定性：不稳定的排序
     *
     * 分治思想：什么时候效率最高      每次把待排序序列     均匀的划分
     *
     * @param array
     */
    public static void quickSort(int[] array) {
        quick(array,0,array.length-1);
    }

    public static void main(String[] args) {
        int[] array = new int[1_0000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        long startTime = System.currentTimeMillis();
        quickSort(array);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);

    }

    public static void main1(String[] args) {
        int[] array = {10,3,2,7,19,78,65,127};
        System.out.println(Arrays.toString(array));
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
