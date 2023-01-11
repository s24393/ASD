import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoder {
    public static Map<Character, String> buildEncodingMap(char[] chars, int[] frequencies) {
        // Tworzymy kolejkę priorytetową dla węzłów Huffmana
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        // Tworzymy węzły listy i dodajemy je do kolejki priorytetowej
        for (int i = 0; i < chars.length; i++) {
            pq.add(new HuffmanNode(chars[i], frequencies[i]));
        }

        // Dopóki w kolejce zostaje więcej niż jeden węzeł
        while (pq.size() > 1) {
            // Pobieramy dwie najmniejsze wartości
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            // Tworzymy nowy węzeł, którego wartością jest suma wartości lewego i prawego węzła
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            // Dodajemy nowy węzeł z powrotem do kolejki
            pq.add(parent);
        }

        // Pobieramy korzeń drzewa Huffmana
        HuffmanNode root = pq.poll();

        // Tworzymy mapę, która będzie zawierać kody Huffmana dla każdego znaku
        Map<Character, String> encodingMap = new HashMap<>();

        // Rekurencyjnie przechodzimy przez drzewo i tworzymy kody Huffmana
        buildEncodingMap(root, "", encodingMap);

        return encodingMap;
    }

    private static void buildEncodingMap(HuffmanNode node, String s, Map<Character, String> encodingMap) {
        if (node.left == null && node.right == null) {
            // Jeśli jesteśmy w liściu, dodajemy kod do mapy
            encodingMap.put(node.ch, s);
            return;
        }

        // Rekurencyjnie przechodzimy w lewo i dodajemy "0" do kodu
        buildEncodingMap(node.left, s + "0", encodingMap);

        // Rekurencyjnie przechodzimy w prawo i dodajemy "1" do kodu
        buildEncodingMap(node.right, s + "1", encodingMap);
    }

    private static class HuffmanNode implements Comparable<HuffmanNode> {
        char ch;
        int frequency;
        HuffmanNode left;
        HuffmanNode right;

        HuffmanNode(char ch, int frequency) {
            this.ch = ch;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(HuffmanNode other) {
            return this.frequency - other.frequency;
        }
    }

}
