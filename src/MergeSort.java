import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {

    private static int recursiveCallCounter;

    /**
     * Copies ArrayLists
     */
    private static ArrayList<Integer> arrayListCopy(ArrayList<Integer> list, int fromIndex, int toIndex) {
        ArrayList<Integer> copyList = new ArrayList<>();

        list.stream().skip(fromIndex).limit(toIndex).forEachOrdered(copyList::add);

        return copyList;
    }


    /**
     * Merges leftList and rightList into outList
     */
    private static void merge(ArrayList<Integer> leftList, ArrayList<Integer> rightList, ArrayList<Integer> outList) {

        int leftIndex = 0;
        int rightIndex = 0;
        int outIndex = 0;

        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {

            if (leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) <= 0) {
                outList.set(outIndex, leftList.get(leftIndex));
                leftIndex++;
            } else {
                outList.set(outIndex, rightList.get(rightIndex));
                rightIndex++;
            }

            outIndex++;

        }

        if (leftIndex < leftList.size()) {
            copyRemainder(leftList, leftIndex, outList, outIndex);
        } else {
            copyRemainder(rightList, rightIndex, outList, outIndex);
        }

    }

    /**
     * Copies elements from inputList[inputIndex..outList.size() - 1] to outList starting at index outIndex
     */
    private static void copyRemainder(ArrayList<Integer> inputList, int inputIndex, ArrayList<Integer> outList, int outIndex) {
        while (inputIndex < inputList.size()) {
            outList.set(outIndex, inputList.get(inputIndex));

            inputIndex++;
            outIndex++;
        }
    }

    /**
     * Primary recursive routine that merge sorts inputList
     */
    public static void mergeSort(ArrayList<Integer> inputList) {
        recursiveCallCounter++;
        System.out.println("Current recursive call count: " + recursiveCallCounter);

        if (inputList.size() < 2) {
            System.out.println("\nCurrent list length < 2");
            System.out.println("Current recursive call count: " + recursiveCallCounter + "\n");

            return;
        }

        ArrayList<Integer> leftList = arrayListCopy(inputList, 0, inputList.size() / 2);
        ArrayList<Integer> rightList = arrayListCopy(inputList, inputList.size() / 2, inputList.size());

        System.out.println("\nMerging left");
        System.out.println("Pre-Sort left: " + printCurrentList(leftList));
        mergeSort(leftList);
        System.out.println("Post-Sort left: " + printCurrentList(leftList));



        System.out.println("\nMerging right");
        System.out.println("Pre-Sort right: " + printCurrentList(rightList));
        mergeSort(rightList);
        System.out.println("Post-Sort right: " + printCurrentList(rightList));

        merge(leftList, rightList, inputList);
    }

    public static void main(String[] args) {
        recursiveCallCounter = 0;

        ArrayList<Integer> testList = new ArrayList<>();
        //Arrays.stream(new int[]{12, 3, 18, 24, 0, 5, -2}).forEachOrdered(testList::add);

        //mergeSort(testList);

        //printResults(testList);

        //System.out.println("\n");

        //recursiveCallCounter = 0;

        //testList = new ArrayList<>();
        Arrays.stream(new int[]{5, 3, 1, 6, 2, 4}).forEachOrdered(testList::add);

        mergeSort(testList);

        printResults(testList);

    }

    private static void printResults(ArrayList<Integer> inputList) {
        if (isSorted(inputList)) {
            System.out.println("\nMerge sort successful");
            System.out.println(printCurrentList(inputList));
            System.out.println("Number of calls to mergeSort: " + recursiveCallCounter);
        }
    }

    private static boolean isSorted(ArrayList<Integer> inputList) {
        int previous = inputList.get(0);

        for (int i : inputList) {
            if (previous > i) {
                System.out.println("Merge sort failed");
                System.out.println("Current object: " + i + "; Previous object: " + previous);
                System.out.println(printCurrentList(inputList));
                return false;
            }

            previous = i;
        }

        return true;
    }

    private static String printCurrentList(ArrayList<Integer> inputList) {
        StringBuffer sb = new StringBuffer("Current list: ");
        for (int k : inputList) {
            sb.append(k).append(" ");
        }

        return sb.toString();
    }
}
