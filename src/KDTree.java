import java.util.*;

public class KDTree {
    int dim;
    Node root;

    public KDTree(int k) {
        dim = k;
        root = null;
    }

    KDTree createTree(float[][] points) {
        KDTree kdTree = new KDTree(points[0].length);
        for (float[] point : points) {
            kdTree.insert(point);
        }
        return kdTree;
    }

    void insert(float[] point) {
        root = insert(root, point, 0);
    }

    private Node insert(Node node, float[] point, int lvl) {
        if (node == null) {
            return new Node(point);
        }

        if (point[lvl] < node.value[lvl]) {
            node.left = insert(node.left, point, (lvl + 1) % dim);
        } else {
            node.right = insert(node.right, point, (lvl + 1) % dim);
        }

        return node;
    }

    float[] findNearest(float[] point) {
        return findMNearest(point, 1)[0];
    }

    private float distanceSquared(float[] point1, float[] point2) {
        float distance = 0;
        for (int i = 0; i < dim; i++) {
            distance += Math.pow(point1[i] - point2[i], 2);
        }
        return distance;
    }

    float[][] findMNearest(float[] point, int m) {
        KDPriorityQueue<Node> maxHeap = new KDPriorityQueue<>(m, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Float.compare(distanceSquared(point, o1.value), distanceSquared(point, o2.value));
            }
        });

        findMNearest(root, point, maxHeap, 0, m);

        float[][] result = new float[m][];
        int i = 0;
        while (!maxHeap.isEmpty() && i != m) {
            result[i] = maxHeap.poll().value;
            i++;
        }

        return result;
    }

    private void findMNearest(Node node, float[] point, KDPriorityQueue<Node> maxHeap, int lvl, int m) {
        if (node == null) {
            return;
        }

        maxHeap.add(node);

        if (point[lvl] < node.value[lvl]) {
            findMNearest(node.left, point, maxHeap, (lvl + 1) % dim, m);
            if (maxHeap.size() < m || Math.abs(node.value[lvl] - point[lvl]) < Math.sqrt(distanceSquared(point, maxHeap.peek().value))) {
                findMNearest(node.right, point, maxHeap, (lvl + 1) % dim, m);
            }
        } else {
            findMNearest(node.right, point, maxHeap, (lvl + 1) % dim, m);
            if (maxHeap.size() < m || Math.abs(node.value[lvl] - point[lvl]) < Math.sqrt(distanceSquared(point, maxHeap.peek().value))) {
                findMNearest(node.left, point, maxHeap, (lvl + 1) % dim, m);
            }
        }
    }

    float[][] searchRange(float[] lower_bounds, float[] upper_bounds) {
        KDArrayList<float[]> points = new KDArrayList<>();
        searchRange(root, lower_bounds, upper_bounds, points, 0);
        return points.toArray(new float[points.size()][dim]);
    }

    private void searchRange(Node node, float[] lower_bounds, float[] upper_bounds, KDArrayList<float[]> points, int lvl) {
        if (node == null) {
            return;
        }

        if (lower_bounds[lvl] <= node.value[lvl] && node.value[lvl] <= upper_bounds[lvl]) {
            boolean inBound = true;
            for (int i = 0; i < dim; i++) {
                if (!(lower_bounds[i] <= node.value[i] && node.value[i] <= upper_bounds[i])) {
                    inBound = false;
                    break;
                }
            }
            if (inBound)
                points.add(node.value);
            searchRange(node.left, lower_bounds, upper_bounds, points, (lvl + 1) % dim);
            searchRange(node.right, lower_bounds, upper_bounds, points, (lvl + 1) % dim);
        } else if (node.value[lvl] < lower_bounds[lvl]) {
            searchRange(node.right, lower_bounds, upper_bounds, points, (lvl + 1) % dim);
        } else {
            searchRange(node.left, lower_bounds, upper_bounds, points, (lvl + 1) % dim);
        }
    }

    boolean pointExists(float[] point) {
        return pointExists(root, point, 0);
    }

    private boolean pointExists(Node node, float[] point, int lvl) {
        if (node == null)
            return false;

        if (point[lvl] == node.value[lvl]) {
            if (Arrays.equals(point, node.value))
                return true;
            if (pointExists(node.left, point, (lvl + 1) % dim))
                return true;
            return pointExists(node.right, point, (lvl + 1) % dim);
        } else if (point[lvl] < node.value[lvl]) {
            return pointExists(node.left, point, (lvl + 1) % dim);
        } else {
            return pointExists(node.right, point, (lvl + 1) % dim);
        }
    }

    boolean deletePoint(float[] point) {
        if (pointExists(point)) {
            root = deletePoint(root, point, 0);
            return true;
        }
        return false;
    }

    private Node deletePoint(Node node, float[] point, int lvl) {
        if (node == null) {
            return null;
        }

        if (Arrays.equals(point, node.value)) {
            if (node.left == null && node.right == null)
                return null;
            else if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                // TODO: One step to left and end of right
                //  One step to right and end of left?
                Node current = node.left;
                while (current.right != null) {
                    current = current.right;
                }
                node.value = current.value;
                node.left = deletePoint(node.left, current.value, (lvl + 1) % dim);

                return node;
            }
        }

        if (point[lvl] < node.value[lvl]) {
            node.left = deletePoint(node.left, point, (lvl + 1) % dim);
        } else {
            node.right = deletePoint(node.right, point, (lvl + 1) % dim);
        }

        return node;
    }
}
