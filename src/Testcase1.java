import java.util.Arrays;

public class Testcase1 {
    public static void main(String[] args) {
        KDTree kdTree = new KDTree(3);
        kdTree.insert(new float[]{57, 71, 99});
        kdTree.insert(new float[]{59, 57, 65});
        kdTree.insert(new float[]{75, 24, 23});
        kdTree.insert(new float[]{65, 60, 80});
        kdTree.insert(new float[]{78, 23, 12});
        kdTree.insert(new float[]{57, 38, 18});
        kdTree.insert(new float[]{11, 68, 88});
        kdTree.insert(new float[]{81, 5, 76});
        kdTree.insert(new float[]{50, 57, 83});
        kdTree.insert(new float[]{94, 78, 83});
        kdTree.insert(new float[]{20, 79, 1});
        kdTree.insert(new float[]{67, 8, 7});

        System.out.println(Arrays.toString(kdTree.findNearest(new float[]{4, 24, 30})));
        System.out.println(kdTree.deletePoint(new float[]{57, 38, 18}));
        System.out.println(Arrays.toString(kdTree.findNearest(new float[]{4, 24, 30})));
        System.out.println(kdTree.deletePoint(new float[]{20, 79, 1}));
        System.out.println(Arrays.toString(kdTree.findNearest(new float[]{4, 24, 30})));
    }
}
