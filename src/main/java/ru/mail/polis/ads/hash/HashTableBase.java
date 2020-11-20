package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class HashTableBase<Key, Value> implements HashTable<Key, Value> {
    private static class Entry<Key, Value> {
        private Key key;
        private Value value;
        private Entry<Key, Value> next;

        Entry(Key key, Value value, Entry<Key, Value> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private final int SIZE = 16;
    private int size = 0;
    private int capacity = SIZE;

    private Entry[] entries = new Entry[capacity];

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hash(key);
        Entry<Key, Value> entry = entries[hash];

        while (entry != null && !entry.key.equals(key)) {
            entry = entry.next;
        }

        if (entry == null)
            return null;
        else return entry.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hash = hash(key);
        Entry entry = entries[hash];

        if (entry == null) {
            entries[hash] = new Entry<>(key, value, null);
            size++;
        } else {
            while (entry != null && !entry.key.equals(key)) {
                entry = entry.next;
            }

            if (entry != null) {
                entry.value = value;
                entry.next = new Entry<>(key, value, null);
            }
        }

        double FACTOR = 0.75;
        if (size > capacity * FACTOR) {
            resizeIfNeed();
        }
    }

    private void resizeIfNeed() {
        capacity *= 2;
        size = 0;
        Entry<Key, Value>[] roots = entries;
        entries = new Entry[capacity];
        for (Entry<Key, Value> part : roots) {
            Entry<Key, Value> head = part;

            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);

        Entry<Key, Value> prev = null;
        Entry<Key, Value> entry = entries[hash];

        if (entry != null) {
            Value currentValue = entry.value;
            size--;

            while (entry.next != null && !entry.key.equals(key)) {
                prev = entry;
                entry = entry.next;
            }

            if (entry.key.equals(key)) {
                if (prev == null) {
                    entries[hash] = entry.next;
                    return currentValue;
                } else {
                    prev.next = entry.next;
                    return currentValue;
                }
            }
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(Key key) {
        return Math.abs(key.hashCode() % entries.length);
    }
}
