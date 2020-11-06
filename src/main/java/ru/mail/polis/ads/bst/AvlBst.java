package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);

        return node == null ? null : node.value;
    }

    private Node get(Node node, Key key){
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) return get(node.left, key);
        if (key.compareTo(node.key) > 0) return get(node.right, key);
        return node;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1);

        if (key.compareTo(node.key) < 0)
            node.left = put(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = put(node.right, key, value);
        else node.value = value;

        fixHeight(node);
        node = balance(node);

        return node;
    }

    private void fixHeight(Node node) {
        node.height = 1 +
                Math.max(
                        height(node.left),
                        height(node.right));
    }

    private Node balance(Node node) {
        if (factor(node) == 2){
            if (factor(node.left) < 0){
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (factor(node) == -2){
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node node){
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        fixHeight(node);
        fixHeight(left);
        return left;
    }

    private Node rotateLeft(Node node){
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        fixHeight(node);
        fixHeight(right);
        return right;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node node = get(root, key);

        if (node == null)
            return null;

        root = remove(root, key);

        return node.value;
    }

    private Node remove(Node node, Key key){
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0)
            node.left = remove(node.left, key);
        if (key.compareTo(node.key) > 0)
            node.right = remove(node.right, key);
        if (key.compareTo(node.key) == 0)
            node = innerDelete(node);
        return node;
    }

    private Node innerDelete(Node node){
        if (node.right == null) return node.left;
        if (node.left == null) return node.right;

        Node t = node;
        node = min(t.right);
        node.right = deleteMin(t.right);
        node.left = t.left;

        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }

    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    private Node min(Node node) {
        Node min = node;

        while (min.left != null){
            min = min.left;
        }

        return min;
    }

    @Override
    public Key max() {
        return  root == null ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return  root == null ? null : max(root).value;
    }

    private Node max(Node node){
        Node max = node;

        while (max.right != null){
            max = max.right;
        }

        return max;
    }

    @Override
    public Key floor(@NotNull Key key) {
        if (size() == 0){
            return null;
        }

        Node node = floor(root, key);

        return node == null ? null : node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null)
            return null;

        if (key == node.key){
            return node;
        }

        if (key.compareTo(node.key) < 0)
            return floor(node.left, key);

        Node right = floor(node.right, key);

        return right == null ? node : right;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        if (size() == 0){
            return null;
        }

        Node node = ceil(root, key);

        return node == null ? null : node.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null)
            return null;

        if (key == node.key){
            return node;
        }

        if (key.compareTo(node.key) > 0)
            return ceil(node.right, key);

        Node left = ceil(node.left, key);

        return left == null ? node : left;
    }

    @Override
    public int size() {
        return root == null ? 0 : size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node){
        return node == null ? 0 : node.height;
    }
}
