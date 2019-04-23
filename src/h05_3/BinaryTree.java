package h05_3;

//**************************************************************************************************
// CLASS: BinaryTree<E> (BinaryTree.java)
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
 * Implements a generic binary tree data structure. Type parameter E can be any class or interface
 * type.
 */
public class BinaryTree<E> {

    // These constants are passsed to traverse() to specify the type of traversal.
    public static final int INORDER     = 0;
    public static final int LEVEL_ORDER = 1;
    public static final int POSTORDER   = 2;
    public static final int PREORDER    = 3;

    /**
     * A BinaryTree consists of a Node which is the root of the tree. The Node objects contains
     * references to the left and right child nodes of mRoot.
     */
    private Node<E> mRoot;

    /**
     * Create a new empty BinaryTree storing no data.
     */
    public BinaryTree() {
        this(null);
    }

    /**
     * Create a new BinaryTree with the root storing pData. The left and right subtrees are set to
     * null.
     */
    public BinaryTree(E pData) {
        this(pData, null, null);
    }

    /**
     * Create a new BinaryTree with the root storing pData. If pLeft is null, then the root of this
     * BinaryTree will not have a left subtree. If pLeft is non-null, then pLeft will become the
     * left subtree of the root of this BinaryTree. To do that, we make the left child reference
     * of the root of this BinaryTree refer to the root of the subtree pLeft. pRight is handled in
     * a similar manner.
     */
    public BinaryTree(E pData, BinaryTree<E> pLeft, BinaryTree<E> pRight) {
        Node<E> leftChild = pLeft == null ? null : pLeft.getRoot();
        Node<E> rightChild = pRight == null ? null : pRight.getRoot();
        setRoot(new Node<E>(pData, leftChild, rightChild));
    }

    /**
     * Removes all of the Nodes in this BinaryTree. After clear() returns, this BinaryTree will be
     * an empty tree.
     */
    public void clear() {
        prune(getRoot());
        setRoot(null);
    }

    /**
     * Searches the tree rooted at pRoot for a node containing pData. Returns a reference to the
     * node or null if pData is not contained within the tree.
     */
    protected Node<E> findNode(Node<E> pRoot, E pData) {
        if (pRoot == null) return null;
        if (pRoot.getData().equals(pData)) return pRoot;
        Node<E> node = findNode(pRoot.getLeft(), pData);
        if (node != null) return node;
        return findNode(pRoot.getRight(), pData);
    }

    /**
     * Returns the height of this BinaryTree.
     */
    public int getHeight() {
        return getHeight(getRoot());
    }

    /**
     * Returns the height of the subtree rooted at pRoot where the height is the maximum of the
     * heights of the left and right subtrees of pRoot.
     */
    protected int getHeight(Node<E> pRoot) {
        int heightLeft = 0, heightRight = 0;
        if (pRoot == null) return 0;
        if (pRoot.hasLeft()) heightLeft = getHeight(pRoot.getLeft()) + 1;
        if (pRoot.hasRight()) heightRight = getHeight(pRoot.getRight()) + 1;
        return Math.max(heightLeft, heightRight);
    }

    /**
     * Accessor method for mRoot.
     */
    protected Node<E> getRoot() {
        return mRoot;
    }

    /*
     * Returns true if this BinaryTree is an empty tree.
     */
    public boolean isEmpty() {
        return getRoot() == null;
    }

    /**
     * Returns true if this BinaryTree is full.
     */
    protected boolean isFull() {
        return isFull(getRoot());
    }

    /**
     * Returns true if the subtree rooted at pRoot is a full binary tree.
     */
    protected boolean isFull(Node<E> pRoot) {
        if (pRoot.isLeaf()) return true;
        boolean leftFull = pRoot.hasLeft() ? isFull(pRoot.getLeft()) : false;
        boolean rightFull = pRoot.hasRight() ? isFull(pRoot.getRight()) : false;
        return leftFull && rightFull;
    }

    /**
     * Creates a new Iterator over this BinaryTree. The current node of the returned Iterator will
     * be the root node of this BinaryTree.
     */
    public Iterator<E> iterator() {
        return new Iterator<E>(this);
    }

    /**
     * Prunes the tree rooted at pTree by pruning the left and right subtrees and then setting
     * the left and right references of the root node to null. Note: this method does not delete
     * the data stored in the root node of pTree, nor does it set the root node of pTree to null.
     */
    protected void prune(Node<E> pRoot) {
        if (pRoot == null) return;
        prune(pRoot.getLeft());
        pRoot.setLeft(null);
        prune(pRoot.getRight());
        pRoot.setRight(null);
    }

    /**
     * Prunes only the left subtree of this BinaryTree.
     */
    protected void pruneLeft(Node<E> pRoot) {
        prune(pRoot.getLeft());
        pRoot.setLeft(null);
    }

    /**
     * Prunes only the right subtree of this BinaryTree.
     */
    protected void pruneRight(Node<E> pRoot) {
        prune(pRoot.getRight());
        pRoot.setRight(null);
    }

    /**
     * Mutator method for mRoot.
     */
    protected void setRoot(Node<E> pRoot) {
        mRoot = pRoot;
    }

    /**
     * Performs a traversal specified by pWhich (which must be one of the constants INORDER,
     * LEVEL_ORDER, POSTORDER, or PREORDER) on this BinaryTree. pVisitor is an object which
     * implements the BinaryTreeVisitor interface. As each Node is visited during the traversal,
     * the visit(E data) method will be called on pVisitor.
     */
    public void traverse(int pWhich, BinaryTreeVisitor<E> pVisitor) {
        if (pWhich == LEVEL_ORDER) traverseLevelOrder(getRoot(), pVisitor);
        traverse(pWhich, getRoot(), pVisitor);
    }

    /**
     * See traverse(int, BinaryTreeVisitor).
     *
     * Performs a traversal specified by pWhich (which must be one of the constants INORDER,
     * LEVEL_ORDER, POSTORDER, or PREORDER) on the subtree rooted at pNode.
     */
    protected void traverse(int pWhich, Node<E> pRoot, BinaryTreeVisitor<E> pVisitor) {
        if (pRoot == null) return;
        switch (pWhich) {
            case INORDER:
                traverse(pWhich, pRoot.getLeft(), pVisitor);
                pVisitor.visit(pRoot.getData());
                traverse(pWhich, pRoot.getRight(), pVisitor);
                break;
            case POSTORDER:
                traverse(pWhich, pRoot.getLeft(), pVisitor);
                traverse(pWhich, pRoot.getRight(), pVisitor);
                pVisitor.visit(pRoot.getData());
                break;
            case PREORDER:
                pVisitor.visit(pRoot.getData());
                traverse(pWhich, pRoot.getLeft(), pVisitor);
                traverse(pWhich, pRoot.getRight(), pVisitor);
                break;
        }
    }

    /**
     * Make a level order traversal of pTree.
     */
    protected void traverseLevelOrder(Node<E> pRoot, BinaryTreeVisitor<E> pVisitor) {
        Queue<Node<E>> nodeQueue = new Queue<>();
        nodeQueue.enqueue(pRoot);
        while (!nodeQueue.isEmpty()) {
            Node<E> root = nodeQueue.dequeue();
            pVisitor.visit(root.getData());
            if (root.hasLeft()) nodeQueue.enqueue(root.getLeft());
            if (root.hasRight()) nodeQueue.enqueue(root.getRight());
        }
    }


    public int getSize() {

        class Counter implements BinaryTreeVisitor<E> {
            int mCount;

            Counter() {
                mCount = 0;
            }

            int getCount() {
                return mCount;
            }

            @Override
            public void visit(E pData) {
                mCount++;
            }
        }

        Counter counter = new Counter();

        traverse(LEVEL_ORDER, counter);


        return counter.getCount();
    }

    //**********************************************************************************************
    // STATIC NESTED CLASS: Node
    //**********************************************************************************************

    /**
     * The data for each element of the BinaryTree is stored in a Node object. A Node object
     * contains three instance variables: (1) mData is a reference to the data stored in the Node;
     * (2) mLeft is a reference to the left child Node; and (3) mRight is a reference to the right
     * child Node.
     *
     * Note that Node is declared as protected so it is not visible to other classes but it is
     * accessible to subclasses of BinaryTree.
     */
    protected static class Node<E> {
        /**
         * The data stored in this Node.
         */
        E mData;

        /**
         * A reference to the left child Node of this Node.
         */
        Node<E> mLeft;

        /**
         * A reference to the right child Node of this Node.
         */
        Node<E> mRight;

        /**
         * Creates a new Node storing no data and with mLeft and mRight set to null.
         */
        public Node() {
            this(null);
        }

        /**
         * Creates a new Node storing pData as the data and with mLeft and mRight set to null.
         */
        public Node(E pData) {
            this(pData, null, null);
        }

        /**
         * Creates a new Node storing pData as the data, mLeft initialized to pLeft, and mRight
         * initialized to pRight.
         */
        public Node(E pData, Node<E> pLeft, Node<E> pRight) {
            setData(pData);
            setLeft(pLeft);
            setRight(pRight);
        }

        /**
         * Accessor method for the mData instance variable.
         */
        public E getData() {
            return mData;
        }

        /**
         * Accessor method for the mLeft instance variable.
         */
        public Node<E> getLeft() {
            return mLeft;
        }

        /**
         * Returns the number of children of this Node.
         */
        public int getNumChildren() {
            int num = 0;
            if (hasLeft()) ++num;
            if (hasRight()) ++ num;
            return num;
        }

        /**
         * Accessor method for the mRight instance variable.
         */
        public Node<E> getRight() {
            return mRight;
        }

        /**
         * Returns true if this Node has a left child Node.
         */
        public boolean hasLeft() {
            return getLeft() != null;
        }

        /**
         * Returns true if this Node has a right child Node.
         */
        public boolean hasRight() {
            return getRight() != null;
        }

        /**
         * Returns true if this Node is a leaf node.
         */
        public boolean isLeaf() {
            return !hasLeft() && !hasRight();
        }

        /**
         * Mutator method for the mData instance variable.
         */
        public void setData(E pData) {
            mData = pData;
        }

        /**
         * Mutator method for the mLeft instance variable.
         */
        public void setLeft(Node<E> pLeft) {
            mLeft = pLeft;
        }

        /**
         * Mutator method for the mRight instance variable.
         */
        public void setRight(Node<E> pRight) {
            mRight = pRight;
        }

        /**
         * Returns a string representation of this Node where we define the string representation
         * to be the string representation of the data stored in this Node.
         */
        @Override
        public String toString() {
            return "" + getData();
        }

    }

    //**********************************************************************************************
    // STATIC NESTED CLASS: Iterator
    //**********************************************************************************************

    /**
     * Implements an iterator that will iterate over the Nodes of the BinaryTree.
     */
    @SuppressWarnings("PublicInnerClass")
    public static class Iterator<E> {

        /**
         * A reference to the current Node of the Iterator.
         */
        Node<E> mCurrent;

        /**
         * This stack stores references to the parent Nodes. As the Iterator moves left and right
         * downward, parent Nodes are pushed on the stack. When moveUp() is called the parent of
         * the current Node will be on top of the stack. Note: when moveToRoot() is called the
         * stack must be emptied.
         */
        Stack<Node<E>> mStack;

        /**
         * A reference to the BinaryTree over which this Iterator iterates.
         */
        BinaryTree<E> mTree;

        /**
         * Create an Iterator to iterate over the BinaryTree pTree.
         */
        public Iterator(BinaryTree<E> pTree) {
            setTree(pTree);
            setCurrent(getTree().getRoot());
            setStack(new Stack<Node<E>>());
        }

        /**
         * Create a new Node containing pData to be the left child of the current Node.
         */
        public void addLeft(E pData) throws EmptyTreeException {
            if (getTree().isEmpty()) throw new EmptyTreeException();
            pruneLeft();
            getCurrent().setLeft(new Node<E>(pData));
        }

        /**
         * Create a new Node containing pData to be the right child of the current Node.
         */
        public void addRight(E pData) throws EmptyTreeException {
            if (getTree().isEmpty()) throw new EmptyTreeException();
            pruneRight();
            getCurrent().setRight(new Node<E>(pData));
        }

        /**
         * Searches the binary tree rooted at the current node for pData. If found, the current
         * Node is changed to the Node containing pData and true is returned. If not found, the
         * current node will not be changed and false will be returned.
         */
        public boolean find(E pData) {
            Node<E> node = getTree().findNode(getCurrent(), pData);
            if (node != null) setCurrent(node);
            return node != null;
        }

        /**
         * Returns the data stored in the current Node.
         */
        public E get() {
            return mCurrent.getData();
        }

        /**
         * Protected accessor method for mCurrent.
         */
        protected Node<E> getCurrent() {
            return mCurrent;
        }

        /**
         * Protected accessor method for mStack.
         */
        protected Stack<Node<E>> getStack() {
            return mStack;
        }

        /**
         * Protected accessor method for mTree.
         */
        protected BinaryTree<E> getTree() {
            return mTree;
        }

        /**
         * Returns the height of the subtree rooted at the current Node.
         */
        public int getHeight() {
            return getTree().getHeight(getCurrent());
        }

        /**
         * Returns true if the subtree rooted at the current Node is a full binary tree.
         */
        public boolean isFull() {
            return getTree().isFull(getCurrent());
        }

        /**
         * Moves this Iterator to the left child of the current Node.
         */
        public void moveLeft() {
            if (getCurrent().hasLeft()) {
                getStack().push(getCurrent());
                setCurrent(getCurrent().getLeft());
            }
        }

        /**
         * Moves this Iterator to the right child of the current Node.
         */
        public void moveRight() {
            if (getCurrent().hasRight()) {
                getStack().push(getCurrent());
                setCurrent(getCurrent().getRight());
            }
        }

        /**
         * Moves this Iterator to the root Node of the tree.
         */
        public void moveToRoot() {
            // Note: we have to empty the stack.
            getStack().clear();
            setCurrent(getTree().getRoot());
        }

        /**
         * Moves the iterator up to the parent Node of the current Node.
         */
        public void moveUp() {
            setCurrent(getStack().pop());
        }

        /**
         * Prunes both the left and right subtrees of the current Node.
         */
        public void prune() {
            pruneLeft();
            pruneRight();
        }

        /**
         * Prunes the left subtree of the current Node.
         */
        public void pruneLeft() {
            getTree().pruneLeft(getCurrent());
        }

        /**
         * Prunes the right subtree of the current Node.
         */
        public void pruneRight() {
            getTree().pruneRight(getCurrent());
        }

        /**
         * Changes the data stored in the current Node.
         */
        public void set(E pData) {
            getCurrent().setData(pData);
        }

        /**
         * Protected mutator method for mCurrent.
         */
        protected void setCurrent(Node<E> pCurrent) {
            mCurrent = pCurrent;
        }

        /**
         * Protected mutator method for mTree.
         */
        protected void setTree(BinaryTree<E> pTree) {
            mTree = pTree;
        }

        /**
         * Protected mutator method for mStack.
         */
        protected void setStack(Stack<Node<E>> pStack) {
            mStack = pStack;
        }

        /**
         * Performs a traversal specified by pWhich (which must be one of the constants INORDER,
         * LEVEL_ORDER, POSTORDER, or PREORDER) on the subtree rooted at the current Node.
         * pVisitor is an object which implements the BinaryTreeVisitor interface. As each Node
         * is visited during the traversal, the visit(E data) method will be called on pVisitor.
         */
        public void traverse(int pWhich, BinaryTreeVisitor<E> pVisitor) {
            getTree().traverse(pWhich, getCurrent(), pVisitor);
        }
    }
}
