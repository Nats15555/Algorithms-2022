package lesson4;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Префиксное дерево для строк
 */
public class Trie extends AbstractSet<String> implements Set<String> {

    private static class Node {
        SortedMap<Character, Node> children = new TreeMap<>();
    }

    private final Node root = new Node();

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root.children.clear();
        size = 0;
    }

    private String withZero(String initial) {
        return initial + (char) 0;
    }

    @Nullable
    private Node findNode(String element) {
        Node current = root;
        for (char character : element.toCharArray()) {
            if (current == null) return null;
            current = current.children.get(character);
        }
        return current;
    }

    @Override
    public boolean contains(Object o) {
        String element = (String) o;
        return findNode(withZero(element)) != null;
    }

    @Override
    public boolean add(String element) {
        Node current = root;
        boolean modified = false;
        for (char character : withZero(element).toCharArray()) {
            Node child = current.children.get(character);
            if (child != null) {
                current = child;
            } else {
                modified = true;
                Node newChild = new Node();
                current.children.put(character, newChild);
                current = newChild;
            }
        }
        if (modified) {
            size++;
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        String element = (String) o;
        Node current = findNode(element);
        if (current == null) return false;
        if (current.children.remove((char) 0) != null) {
            size--;
            return true;
        }
        return false;
    }

    /**
     * Итератор для префиксного дерева
     * <p>
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     * <p>
     * Сложная
     */
    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new TreeIterator();
    }

    public class TreeIterator implements Iterator<String> {
        final Deque<Iterator<Map.Entry<Character, Node>>> dequeIteratorEntry = new LinkedList<>();
        final StringBuilder nextString = new StringBuilder();
        Node lastNode = null;
        Node lastNextNode = root;
        boolean firstStep = true;

        @Override
        public boolean hasNext() {
            if (size > 0 && firstStep) {
                dequeIteratorEntry.addLast(root.children.entrySet().iterator());
                firstStep = false;
                findNextElementTree();
            }

            return nextString.length() != 0;
        }

        // Время О(n)
        // Память О(n)
        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            String result = nextString.toString();
            lastNode = lastNextNode;
            lastNextNode = root;
            findNextElementTree();
            return result;
        }

        // Время О(n)
        // Память О(n)
        @Override
        public void remove() {
            if (lastNode == null) {
                throw new IllegalStateException();
            }

            if (lastNode.children.remove((char) 0) != null) {
                size--;
            }

            lastNode = null;
        }

        private void findNextElementTree() {
            Iterator<Map.Entry<Character, Node>> nextNode = dequeIteratorEntry.peekLast();
            while (nextNode != null && !nextNode.hasNext()) {
                dequeIteratorEntry.pollLast();
                if (dequeIteratorEntry.isEmpty()) {
                    nextNode = null;
                } else {
                    nextString.deleteCharAt(nextString.length() - 1);
                    nextNode = dequeIteratorEntry.peekLast();
                }
            }

            lastNextNode = findNode(nextString.toString());
            if (nextNode != null) {
                while (nextNode.hasNext()) {
                    Map.Entry<Character, Node> child = nextNode.next();
                    nextNode = new HashMap<>(child.getValue().children).entrySet().iterator();
                    dequeIteratorEntry.offerLast(nextNode);
                    nextString.append(child.getKey());

                    if (child.getKey() != (char) 0) {
                        lastNextNode = lastNextNode.children.get(child.getKey());
                    }
                }

                if (nextString.charAt(nextString.length() - 1) != (char) 0) {
                    findNextElementTree();
                } else {
                    nextString.deleteCharAt(nextString.length() - 1);
                    dequeIteratorEntry.pollLast();
                }
            }
        }
    }
}