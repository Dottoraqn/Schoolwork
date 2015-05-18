import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Creates an AVL Tree
 *
 * @author Emma Hargrave
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public AVL() {
        root = null;
        size = 0;
    }

    /**
     * Initializes the AVL with the data in the collection. The data
     * should be added in the same order it is in the collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("blah blah blah");
        }
        for (T datum : data) {
            if (datum == null) {
                throw new IllegalArgumentException("8lah 8lah 8lah");
            }
            add(datum);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("GütënTäg");
        } else if (root == null) {
            root = new AVLNode<T>(data);
            root.setHeight(0);
            root.setBalanceFactor(0);
            size++;
        } else {
            root = addHelper(data, root);
//            balance(root);
        }
    }

    /**
     * A helper method for the add method
     * @param data data to add
     * @param node node to add
     * @return node to add
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> node) {
        AVLNode<T> curr = node;
        if (node == null) {
            curr = new AVLNode<T>(data);
            curr.setHeight(0);
            curr.setBalanceFactor(0);
            size++;
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (node.getData().compareTo(data) < 0) {
            node.setRight(addHelper(data, node.getRight()));
        }
        return balance(curr); //do the same for remove
    }

    @Override
    public T remove(T data) {
        AVLNode<T> toData = new AVLNode<T>(null);
        root = removeHelper(data, root, toData);
        size--;
        return toData.getData();
    }

    /**
     * A helper method for the remove method
     * @param data data to add
     * @param node node to add
     * @param toData node to return when removed
     * @return node to add
     */
    private AVLNode<T> removeHelper(T data, AVLNode<T> node,
                                    AVLNode<T> toData) {
        if (data == null) {
            throw new IllegalArgumentException("no");
        } else if (node == null) {
            throw new NoSuchElementException("the north remembers");
        } else {
            if (data.equals(node.getData())) {
                toData.setData(node.getData());
                if (node.getLeft() == null && node.getRight() == null) {
                    return null;
                } else if (node.getLeft() == null || node.getRight() == null) {
                    if (node.getLeft() != null) {
                        return balance(node.getLeft());
                    } else {
                        return balance(node.getRight());
                    }
                } else {
                    AVLNode<T> predecessor = getLargest(node.getLeft());
                    T ret = node.getData();
                    node.setData(predecessor.getData());
                    node.setLeft(removeHelper(predecessor.getData(),
                            node.getLeft(), toData));
                    toData.setData(ret);
                    return balance(node);
                }
            } else if (data.compareTo(node.getData()) < 0) {
                node.setLeft(removeHelper(data, node.getLeft(), toData));
            } else if (data.compareTo(node.getData()) > 0) {
                node.setRight(removeHelper(data, node.getRight(), toData));
            }
            return balance(node);
        }
    }

    /**
     * A helper method for the remove helper method.
     * gets successor
     * @param node node to add
     * @return node to add
     */
    private AVLNode<T> getLargest(AVLNode<T> node) {
        if (node.getRight() == null) {
            return node;
        } else {
            return getLargest(node.getRight());
        }
    }

    /**
     * a private helper method to balance the tree
     * @param node the node to be balanced from
     * @return the node that was balanced
     */
    private AVLNode<T> balance(AVLNode<T> node) {
        updateBoth(node);
        if (node.getBalanceFactor() >  1) {
            if (node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(left(node.getLeft()));
            }
            return right(node);
        } else if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() > 0) {
                node.setRight(right(node.getRight()));
            }
            return left(node);
        }
        return node;
    }

    /**
     * update both heights and the balance factor
     * @param node the node to update
     */
    private void updateBoth(AVLNode<T> node) {
        int leftHeight
                = node.getLeft() == null ? -1 : node.getLeft().getHeight();
        int rightHeight
                = node.getRight() == null ? -1 : node.getRight().getHeight();
        node.setBalanceFactor(leftHeight - rightHeight);
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    /**
     * A helper method to balance the left side of the tree
     * @param node node to balance
     * @return node that was balanced
     */
    private AVLNode<T> left(AVLNode<T> node) {
        AVLNode<T> newRoot = node.getRight();
        AVLNode<T> subTree = newRoot.getLeft();
        newRoot.setLeft(node);
        node.setRight(subTree);
        updateBoth(node);
        updateBoth(newRoot);
        return newRoot;
    }

    /**
     * A helper method to balance the right side of the tree
     * @param node node to balance
     * @return node that was balanced
     */
    private AVLNode<T> right(AVLNode<T> node) {
        AVLNode<T> newRoot = node.getLeft();
        AVLNode<T> subTree = newRoot.getRight();
        newRoot.setRight(node);
        node.setLeft(subTree);
        updateBoth(node);
        updateBoth(newRoot);
        return newRoot;
    }

    @Override
    public T get(T data) {
        return getHelp(data, root);
    }

    /**
     * a helper method for the get method
     * @param data data to get
     * @param node node to search
     * @return data if true
     */
    private T getHelp(T data, AVLNode<T> node) {
        if (data == null) {
            throw new IllegalArgumentException(":P");
        } else if (node == null) {
            throw new NoSuchElementException("nope");
        } else if (data.equals(node.getData())) {
            return node.getData();
        } else if (data.compareTo(node.getData()) < 0) {
            return getHelp(data, node.getLeft());
        } else if (data.compareTo(node.getData()) > 0) {
            return getHelp(data, node.getRight());
        } else {
            return null;
        }
    }

    @Override
    public boolean contains(T data) {
        return containsHelper(data, root);
    }

    /**
     * A helper method for the contains method
     * @param data data to check for contains
     * @param node node to check for contains
     * @return boolean if contains is true
     */
    private boolean containsHelper(T data, AVLNode<T> node) {
        if (data == null) {
            throw new IllegalArgumentException("nah");
        } else if (node == null) {
            return false;
        } else if (data.equals(node.getData())) {
            return true;
        } else if (data.compareTo(node.getData()) < 0) {
            return containsHelper(data, node.getLeft());
        } else if (data.compareTo(node.getData()) > 0) {
            return containsHelper(data, node.getRight());
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> myList = new LinkedList<T>();
        preorderHelper(myList, root);
        return myList;
    }

    /**
     * a preorder recursive helper method
     * @param list list to return
     * @param node node to add to list
     * @return list
     */
    private List<T> preorderHelper(List<T> list, AVLNode<T> node) {
        if (node != null) {
            list.add(node.getData());
            preorderHelper(list, node.getLeft());
            preorderHelper(list, node.getRight());
        }
        return list;
    }

    @Override
    public List<T> postorder() {
        List<T> myList = new LinkedList<T>();
        postorderHelper(myList, root);
        return myList;
    }

    /**
     * a postorder recursive helper method
     * @param list list to return
     * @param node node to add to list
     * @return list
     */
    private List<T> postorderHelper(List<T> list, AVLNode<T> node) {
        if (node != null) {
            postorderHelper(list, node.getLeft());
            postorderHelper(list, node.getRight());
            list.add(node.getData());
        }
        return list;
    }

    @Override
    public List<T> inorder() {
        List<T> myList = new LinkedList<T>();
        inorderHelper(myList, root);
        return myList;
    }

    /**
     * a inorder recursive helper method
     * @param list list to return
     * @param node node to add to list
     * @return list
     */
    private List<T> inorderHelper(List<T> list, AVLNode<T> node) {
        if (node != null) {
            inorderHelper(list, node.getLeft());
            list.add(node.getData());
            inorderHelper(list, node.getRight());
        }
        return list;
    }

    @Override
    public List<T> levelorder() {
        List<AVLNode<T>> queue = new LinkedList<AVLNode<T>>();
        List<T> myList = new LinkedList<T>();
        AVLNode<T> node = root;
        if (node == null) {
            return myList;
        }
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.remove(0);
            myList.add(node.getData());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return myList;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return root.getHeight();
    }

    @Override
    public int depth(T data) {
        if (data == null) {
            throw new IllegalArgumentException("drop it");
        } else if (root == null) {
            return -1;
        } else {
            if (!contains(data)) {
                return -1;
            }
            return depthHelper(data, root);
        }
    }

    /**
     * a recursive helper for the depth method
     * @param data data to find the depth of
     * @param node to find the depth of
     * @return int depth
     */
    private int depthHelper(T data, AVLNode<T> node) {
        if (node != null) {
            if (data.compareTo(node.getData()) < 0) {
                return depthHelper(data, node.getLeft()) + 1;
            } else if (data.compareTo(node.getData()) > 0) {
                return depthHelper(data, node.getRight()) + 1;
            } else if (data.equals(node.getData())) {
                return 1;
            }
        }
        return -1;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        return root;
    }
}
