package h05_3;//**************************************************************************************************
// CLASS: Main
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
//
// (c) 2018 Kevin R Burger
//**************************************************************************************************

/**
 * Main
 */
public class Main implements BinaryTreeVisitor<Integer> {

    public static void main(String[] pArgs) {
        new Main().run();
    }

    /**
     * Create this BinaryTree:
     *
     *             1
     *            / \
     *           2   3
     *          / \ / \
     *         4  5 6  7
     */
    private void run() {
        // Create the BinaryTree with the root node storing 1.
        BinaryTree<Integer> tree = new BinaryTree<>(1);

        // Create an Iterator which we can use to iterate over the elements of the tree. The
        // iterator is initialized to refer to the root node of the tree.
        BinaryTree.Iterator<Integer> it = tree.iterator();

        // Add a new node storing 2 to be the left child of the root node.
        it.addLeft(2); it.addRight(3);

        // Add a new node storing 3 to be the right child of the root node.
        it.addRight(3);

        // Move the iterator to refer to the node storing 2.
        it.moveLeft();

        // Add a new node storing 3 to be the left child of 2.
        it.addLeft(4);

        // Add a new node storing 5 to be the right child of 2.
        it.addRight(5);

        // Move the iterator back to the root node.
        it.moveToRoot();

        // Move the iterator to refer to the node storing 3.
        it.moveRight();

        // Add a new node storing 6 to be the left child of 3.
        it.addLeft(6);

        // Add a new node storing 7 to be the right child of 3.
        it.addRight(7);

        // Move the iterator back to the root node.
        it.moveToRoot();

        // Perform a level order traversal of the tree. This class implements the BinaryTreeVisitor
        // interface, so the implemented visit() method -- see below -- will be called as each node
        // is visited. We simply print the data stored in the node, but the visit() method could
        // do anything it wanted to with the data.
        // Will print: 1 2 3 4 5 6 7
        tree.traverse(BinaryTree.LEVEL_ORDER, this);
        System.out.println();

        System.out.println(tree.getSize());


        // Perform a preorder traversal of the tree.
        // Will print: 1 2 4 5 3 6 7
        tree.traverse(BinaryTree.PREORDER, this);
        System.out.println();

        // Perform a postorder traversal of the tree.
        // Will print: 4 5 2 6 7 3 1
        tree.traverse(BinaryTree.POSTORDER, this);
        System.out.println();

        // Perform an inorder traversal of the tree.
        // Will print: 4 2 5 1 6 3 7
        tree.traverse(BinaryTree.INORDER, this);
        System.out.println();

        // Let's prune the right subtree of 1.
        it.pruneRight();

        // Perform a level order traversal of the tree. This class implements the BinaryTreeVisitor
        // interface, so the implemented visit() method -- see below -- will be called as each node
        // is visited. We simply print the data stored in the node, but the visit() method could
        // do anything it wanted to with the data.
        // Will print: 1 2 4 5
        tree.traverse(BinaryTree.LEVEL_ORDER, this);
        System.out.println();

        System.out.println(tree.getSize());
    }

    /**
     * Implements the visit() method of the BinaryTreeVisitor interface. We simply print the data
     * stored in the node.
     */
    @Override
    public void visit(Integer pData) {
        System.out.print(pData + " ");
    }

}
