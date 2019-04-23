public class QuickSort {

    private static int mWhileLoopCounter;
    private static int mTotalWhileLoopCounter;

    public static void main(String[] args) {

        mWhileLoopCounter = 0;
        mTotalWhileLoopCounter = 0;


        int[] testArray = new int[]{5, 4, 2, 9, 1, 7, 3, 8, 6};

        quickSort(testArray, 0, testArray.length - 1);


        StringBuffer sb = new StringBuffer();
        for (int i : testArray) {
            sb.append(i).append(" ");
        }

        System.out.println("\nTotal while loop passes: " + mTotalWhileLoopCounter);
        System.out.println("\n" + sb.toString().trim());
    }

    private static void swap (int[] inputArray, int start, int end) {
        int tempLeft = inputArray[start];
        inputArray[start] = inputArray[end];
        inputArray[end] = tempLeft;
    }

    private static int partition(int[] inputArray, int start, int end) {
        int pivot = inputArray[start];

        int leftIndex = start - 1;
        int rightIndex = end + 1;
        StringBuffer sb = new StringBuffer("\nlist = { ");

        for (int i = 0; i < inputArray.length; i++) {
            sb.append(inputArray[i]);

            if (i < inputArray.length - 1) {
                sb.append(",");
            }

            sb.append(" ");
        }

        sb.append("}, ").append("pivot = ").append(pivot).append(", leftIndex = ").append(leftIndex).append(", rightIndex = ").append(rightIndex);

        System.out.println(sb.toString());

        while (leftIndex < rightIndex) {
            System.out.println("\nWhile loop pass: " + ++mWhileLoopCounter);
            ++mTotalWhileLoopCounter;

            leftIndex++;

            while (inputArray[leftIndex] < pivot) {
                leftIndex++;
            }

            rightIndex--;
            while (inputArray[rightIndex] > pivot) {
                rightIndex--;
            }

            System.out.println("leftIndex ends up at " + leftIndex + " rightIndex ends up at " + rightIndex);

            if (leftIndex < rightIndex) {


                swap(inputArray, leftIndex, rightIndex);
                System.out.println("leftIndex < rightIndex so swap list[" + leftIndex + "] and list[" + rightIndex
                        + "]: list = { " + printArray(inputArray) + " }");
            }

        }
        System.out.println("While loop terminates because leftIndex: " + leftIndex + " is >= rightIndex: " + rightIndex);

        mWhileLoopCounter = 0;

        return rightIndex;
    }


    public static void quickSort(int[] inputArray, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = partition(inputArray, start, end);

        int[] leftArray = new int[pivot];

        for (int i = 0; i < pivot - 1; i++) {
            leftArray[i] = inputArray[i];
        }

        int[] rightArray = new int[inputArray.length - pivot];

        for (int i = pivot; i < inputArray.length - 1; i++) {
            rightArray[i - pivot] = inputArray[i];
        }

        System.out.println("partition() returns " + pivot + " so leftList = { " + printArray(leftArray) + " }, rightList = { " + printArray(rightArray) + " }");

        quickSort(inputArray, start, pivot);
        quickSort(inputArray, pivot + 1, end);
    }

    private static String printArray(int[] inputArray) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < inputArray.length; i++) {
            sb.append(inputArray[i]);

            if (i < inputArray.length - 1) {
                sb.append(",");
            }

            sb.append(" ");
        }

        return sb.toString().trim();
    }
}
