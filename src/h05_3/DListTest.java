package h05_3;//**************************************************************************************************
// CLASS: DListTest (DListTest.java)
//
// DESCRIPTION
// Tests the DList<T> class.
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
//
// (c) 2018 Kevin R. Burger
//**************************************************************************************************

public class DListTest {

    public static void main(String[] pArgs) {
        new DListTest().run();
    }

    private DListTest() {
    }

    private void passOrFail(int pCase, DList<Integer> list, String pExpected) {
        String listAsString = list.toString().trim();
        if (!listAsString.equals(pExpected)) {
            System.out.println("failed [" + listAsString + "]");
        } else {
            System.out.println("passed");
        }
    }

    private void run() {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testCase6();
        testCase7();
        testCase8();
        testCase9();
        testCase10();
        testCase11();
        testCase12();
        testCase13();
        testCase14();
        testCase15();
        testCase16();
        testCase17();
        testCase18();
        testCase19();
    }

    /**
     * Test Case 1: tests appending of one element to an empty list.
     */
    private void testCase1() {
        System.out.print("Running test case 1... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        passOrFail(1, list, "1");
    }

    /**
     * Test Case 2: tests appending of multiple elements.
     */
    private void testCase2() {
        System.out.print("Running test case 2... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);
        passOrFail(2, list, "1 2 3 4 5 6");
    }

    /**
     * Test Case 3: test adding one element to an empty list (at pIndex = 0).
     */
    private void testCase3() {
        System.out.print("Running test case 3... ");
        DList<Integer> list = new DList<>();
        list.add(0, 1);
        passOrFail(3, list, "1");
    }

    /**
     * Test Case 4: test adding multiple elements to the head of a list (at pIndex = 0).
     */
    private void testCase4() {
        System.out.print("Running test case 4... ");
        DList<Integer> list = new DList<>();
        list.add(0, 1);
        list.add(0, 2);
        list.add(0, 3);
        list.add(0, 4);
        list.add(0, 5);
        passOrFail(4, list, "5 4 3 2 1");
    }

    /**
     * Test Case 5: test adding multiple elements to the tail of a nonempty list (at
     * pIndex = list.getSize()).
     */
    private void testCase5() {
        System.out.print("Running test case 5... ");
        DList<Integer> list = new DList<>();
        list.add(0, 1);
        list.add(list.getSize(), 2);
        list.add(list.getSize(), 3);
        list.add(list.getSize(), 4);
        list.add(list.getSize(), 5);
        passOrFail(5, list, "1 2 3 4 5");
    }

    /**
     * Test Case 6: test adding multiple elements to the middle of a nonempty list.
     */
    private void testCase6() {
        System.out.print("Running test case 6... ");
        DList<Integer> list = new DList<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(1, 3);
        list.add(1, 4);
        list.add(1, 5);
        list.add(1, 6);
        passOrFail(6, list, "1 6 5 4 3 2");
    }

    /**
     * Test Case 7: tests prepending of one element to an empty list.
     */
    private void testCase7() {
        System.out.print("Running test case 7... ");
        DList<Integer> list = new DList<>();
        list.prepend(1);
        passOrFail(7, list, "1");
    }

    /**
     * Test Case 8: tests prepending of multiple elements to a nonempty list.
     */
    private void testCase8() {
        System.out.print("Running test case 8... ");
        DList<Integer> list = new DList<>();
        list.prepend(1);
        list.prepend(2);
        list.prepend(3);
        list.prepend(4);
        list.prepend(5);
        list.prepend(6);
        passOrFail(8, list, "6 5 4 3 2 1");
    }

    /**
     * Test Case 9: tests that get(index) throws an IndexOutOfBoundsException when an invalid
     * index is passed on an empty list.
     */
    private void testCase9() {
        System.out.print("Running test case 9... ");
        DList<Integer> list = new DList<>();
        try {
            Integer i = list.get(0);
        } catch (IndexOutOfBoundsException pExcept) {
            System.out.println("passed");
        }
    }

    /**
     * Test Case 10: tests that get(index) throws an IndexOutOfBoundsException when an invalid
     * index < 0 is passed on a nonempty list.
     */
    private void testCase10() {
        System.out.print("Running test case 10... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        try {
            Integer i = list.get(-1);
        } catch (IndexOutOfBoundsException pExcept) {
            System.out.println("passed");
        }
    }

    /**
     * Test Case 11: tests that get(index) throws an IndexOutOfBoundsException when an invalid
     * index >= list.getSize() is passed on a nonempty list.
     */
    private void testCase11() {
        System.out.print("Running test case 11... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        try {
            Integer i = list.get(3);
        } catch (IndexOutOfBoundsException pExcept) {
            System.out.println("passed");
        }
    }

    /**
     * Test Case 12: tests that remove(index) throws an IndexOutOfBoundsException when an invalid
     * index is passed on a empty list.
     */
    private void testCase12() {
        System.out.print("Running test case 12... ");
        DList<Integer> list = new DList<>();
        try {
            Integer i = list.remove(0);
        } catch (IndexOutOfBoundsException pExcept) {
            System.out.println("passed");
        }
    }

    /**
     * Test Case 13: tests that remove(index) throws an IndexOutOfBoundsException when an invalid
     * index < 0 is passed on a nonempty list.
     */
    private void testCase13() {
        System.out.print("Running test case 13... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        try {
            Integer i = list.remove(-1);
        } catch (IndexOutOfBoundsException pExcept) {
            System.out.println("passed");
        }
    }

    /**
     * Test Case 14: tests that remove(index) throws an IndexOutOfBoundsException when an invalid
     * index >= list.getSize() is passed on a nonempty list.
     */
    private void testCase14() {
        System.out.print("Running test case 14... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        try {
            Integer i = list.remove(3);
        } catch (IndexOutOfBoundsException pExcept) {
            System.out.println("passed");
        }
    }

    /**
     * Test Case 15: tests that remove(index) removes the element at index 0 in a list of size 1.
     */
    private void testCase15() {
        System.out.print("Running test case 15... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        Integer i = list.remove(0);
        if (i != 1) System.out.println("failed");
        passOrFail(15, list, "");
    }

    /**
     * Test Case 16: tests that remove(index) removes the element at index 0 in a nonempty list.
     */
    private void testCase16() {
        System.out.print("Running test case 16... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        Integer i = list.remove(0);
        if (i != 1) System.out.println("failed");
        passOrFail(16, list, "2 3 4");
     }

    /**
     * Test Case 17: tests that remove(index) removes the element at the end of a nonempty list.
     */
    private void testCase17() {
        System.out.print("Running test case 17... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        Integer i = list.remove(3);
        if (i != 4) System.out.println("failed");
        passOrFail(17, list, "1 2 3");
    }

    /**
     * Test Case 18: tests that remove(index) removes the elements from the middle of a nonempty
     * list.
     */
    private void testCase18() {
        System.out.print("Running test case 18... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);
        list.append(7);
        list.append(8);
        list.append(9);
        Integer i = list.remove(2);
        if (i != 3) System.out.println("failed");
        i = list.remove(4);
        if (i != 6) System.out.println("failed");
        i = list.remove(5);
        if (i != 8) System.out.println("failed");
        passOrFail(18, list, "1 2 4 5 7 9");
    }

    /**
     * Test Case 19/20: tests that clear erases the list.
     */
    private void testCase19() {
        System.out.print("Running test case 19... ");
        DList<Integer> list = new DList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);
        list.append(7);
        list.append(8);
        list.append(9);
        list.clear();
        passOrFail(19, list, "");
        System.out.print("Running test case 20... ");
        list.add(0, 8);
        list.append(9);
        list.append(9);
        list.append(9);
        passOrFail(20, list, "8 9 9 9");
    }

}