package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root = null;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        Node(Key key, Value value, int height, boolean color) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);

        return node == null ? null : node.value;
    }

    private Node get(Node node, Key key) {
        if (node == null) return null;

        int compare = key.compareTo(node.key);

        if (compare < 0) return get(node.left, key);
        if (compare > 0) return get(node.right, key);

        return node;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1, RED);

        int compare = key.compareTo(node.key);

        if (compare < 0) node.left = put(node.left, key, value);
        else if (compare > 0) node.right = put(node.right, key, value);
        else node.value = value;

        return fixUp(node);
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;

        right.color = node.color;
        node.color = RED;

        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;

        left.color = node.color;
        node.color = RED;

        return left;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (root == null) return null;

        Value removed = get(key);
        if(removed != null) {
            root = remove(root, key);
        }

        return removed;
    }

    private Node remove(Node node, Key key) {
        if (node == null)
            return null;

        int compare = key.compareTo(node.key);

        if (compare < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) node = moveRedLeft(node);
                node.left = remove(node.left, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = remove(node.right, key);
            } else if (compare == 0 && node.right == null) return null;
            else {
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) node = moveRedRight(node);
                if (key.compareTo(node.key) == 0) {
                    Node min = min(node.right);
                    node.key = min.key;
                    node.value = min.value;
                    node.right = deleteMin(node.right);
                } else node.right = remove(node.right, key);
            }
        }

        return fixUp(node);
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);

        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }

        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);

        return fixUp(node);
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    @Nullable
    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }


    @Nullable
    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    private Node min(Node node) {
        Node min = node;

        while (min.left != null) {
            min = min.left;
        }

        return min;
    }

    @Nullable
    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    private Node max(Node node) {
        Node max = node;

        while (max.right != null) {
            max = max.right;
        }

        return max;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (size() == 0) {
            return null;
        }

        Node node = floor(root, key);

        return node == null ? null : node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.key);

        if (compare == 0) {
            return node;
        }
        if (compare < 0) {
            return floor(node.left, key);
        }

        Node right = floor(node.right, key);

        return right != null ? right : node;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (size() == 0) {
            return null;
        }

        Node node = ceil(root, key);

        return node == null ? null : node.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.key);

        if (compare == 0) {
            return node;
        }
        if (compare > 0) {
            return ceil(node.right, key);
        }

        Node left = ceil(node.left, key);

        return left != null ? left : node;
    }

    @Override
    public int size() {
        return root == null ? 0 : size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }
}
