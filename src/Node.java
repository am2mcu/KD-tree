public class Node {
    float[] value;
    Node left, right;

    Node(float[] value) {
        this.value = value;
        left = right = null;
    }
}