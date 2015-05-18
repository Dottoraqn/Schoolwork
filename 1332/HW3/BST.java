import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Initializes the BST with the data in the collection. The data in the BST
     * should be added in the same order it is in the collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("*sideshow bob noises*");
        }
        for (T datum : data) {
            if (datum == null) {
                throw new IllegalArgumentException("*sideshow bob noises*");
            }
            add(datum);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Step off");
        } else if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            addHelper(data, root);
        }
    }

    /**
     * A helper method for the add method
     * @param data data to add
     * @param node node to add
     * @return node to add
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> node) {
        BSTNode<T> cur = node;
        if (node == null) {
            cur = new BSTNode<T>(data);
            size++;
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (node.getData().compareTo(data) < 0) {
            node.setRight(addHelper(data, node.getRight()));
        }
        return cur;
    }

    @Override
    public T remove(T data) {
        BSTNode<T> toData = new BSTNode<T>(null);
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
    private BSTNode<T> removeHelper(T data, BSTNode<T> node,
                                    BSTNode<T> toData) {
        if (data == null) {
            throw new IllegalArgumentException("Step off");
        } else if (node == null) {
            throw new NoSuchElementException("go away");
        } else {
            if (data.equals(node.getData())) {
                toData.setData(node.getData());
                if (node.getLeft() == null && node.getRight() == null) {
                    return null;
                } else if (node.getLeft() == null || node.getRight() == null) {
                    if (node.getLeft() != null) {
                        return node.getLeft();
                    } else {
                        return node.getRight();
                    }
                } else {
                    BSTNode<T> successor = getSmallest(node.getRight());
                    T ret = node.getData();
                    node.setData(successor.getData());
                    node.setRight(removeHelper(successor.getData(),
                            node.getRight(), toData));
                    toData.setData(ret);
                    return node;
                }
            } else if (data.compareTo(node.getData()) < 0) {
                node.setLeft(removeHelper(data, node.getLeft(), toData));
            } else if (data.compareTo(node.getData()) > 0) {
                node.setRight(removeHelper(data, node.getRight(), toData));
            }
            return node;
        }
    }

    /**
     * A helper method for the remove helper method.
     * gets successor
     * @param node node to add
     * @return node to add
     */
    private BSTNode<T> getSmallest(BSTNode<T> node) {
        if (node.getLeft() == null) {
            return node;
        } else {
            return getSmallest(node.getLeft());
        }
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
    private T getHelp(T data, BSTNode<T> node) {
        if (data == null) {
            throw new IllegalArgumentException("Step off");
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
    private boolean containsHelper(T data, BSTNode<T> node) {
        if (data == null) {
            throw new IllegalArgumentException("Step off");
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
    private List<T> preorderHelper(List<T> list, BSTNode<T> node) {
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
    private List<T> postorderHelper(List<T> list, BSTNode<T> node) {
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
    private List<T> inorderHelper(List<T> list, BSTNode<T> node) {
        if (node != null) {
            inorderHelper(list, node.getLeft());
            list.add(node.getData());
            inorderHelper(list, node.getRight());
        }
        return list;
    }

    @Override
    public List<T> levelorder() {
        List<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        List<T> myList = new LinkedList<T>();
        BSTNode<T> node = root;
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
        return heightHelper(root);
    }

    /**
     * A recursive helper for the height method
     * @param node a node to check the height of
     * @return int height
     */
    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }
        return Math.max(heightHelper(node.getLeft()),
                heightHelper(node.getRight())) + 1;
    }

    @Override
    public int depth(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Go away");
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
    private int depthHelper(T data, BSTNode<T> node) {
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
    public BSTNode<T> getRoot() {
        return root;
    }
}
