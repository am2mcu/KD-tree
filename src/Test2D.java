import java.util.Arrays;

public class Test2D {
    public static void main(String[] args) {
        int dim = 2;

        float[][] points = new float[][]{
                new float[]{51, 75},
                new float[]{25, 40},
                new float[]{70, 70},
                new float[]{10, 30},
                new float[]{35, 90},
                new float[]{55, 1},
                new float[]{60, 80},
                new float[]{1, 10},
                new float[]{50, 50}
        };

        KDTree kdTree = new KDTree(dim);
        kdTree = kdTree.createTree(points);

//        System.out.println(Arrays.toString(kdTree.root.value));
//        System.out.println(Arrays.toString(kdTree.root.left.value));
//        System.out.println(Arrays.toString(kdTree.root.right.value));
//        System.out.println(Arrays.toString(kdTree.root.left.right.right.value));
//
//        System.out.println(kdTree.pointExists(new float[]{50, 50}));
//
//        float[][] searchRangePoints = kdTree.searchRange(new float[]{49, 49}, new float[]{81, 81});
//        for (float[] point : searchRangePoints)
//            System.out.println(Arrays.toString(point));
//
//        System.out.println("Near to: " + Arrays.toString(kdTree.findNearest(new float[]{49, 49})));

//        System.out.println(kdTree.deletePoint(new float[]{50, 50}));
//        System.out.println(kdTree.pointExists(new float[]{50, 50}));

        System.out.println("\nM Nearest Test");
        float[][] mNearest = kdTree.findMNearest(new float[]{49, 49}, 9);
        for (float[] point : mNearest)
            System.out.println(Arrays.toString(point));

        System.out.println("-------------");
        mNearest = kdTree.findMNearest(new float[]{20, 24}, 1);
        for (float[] point : mNearest)
            System.out.println(Arrays.toString(point));

        System.out.println("----searchRange---------");
        mNearest = kdTree.searchRange(new float[]{1, 10}, new float[]{70, 90});
        for (float[] point : mNearest)
            System.out.println(Arrays.toString(point));

        System.out.println("----pointExists---------");
        for (float[] point : points)
            System.out.println(Arrays.toString(point) + " | " + kdTree.pointExists(point));

        System.out.println("----deletePoint---------");
//        kdTree.deletePoint(new float[]{51, 75});
//        kdTree.deletePoint(new float[]{25, 40});
        kdTree.deletePoint(new float[]{50, 50});
        for (float[] point : points)
            System.out.println(Arrays.toString(point) + " | " + kdTree.pointExists(point));
        System.out.println(Arrays.toString(kdTree.root.value));
        System.out.println(Arrays.toString(kdTree.root.left.value));
        System.out.println(Arrays.toString(kdTree.root.right.value));
        System.out.println(Arrays.toString(kdTree.root.left.left.value));
        System.out.println(Arrays.toString(kdTree.root.left.right.value));
        System.out.println(Arrays.toString(kdTree.root.right.left.value));
        System.out.println(Arrays.toString(kdTree.root.right.right.value));
        System.out.println(Arrays.toString(kdTree.root.left.left.left.value));
        System.out.println(Arrays.toString(kdTree.root.left.right.right.value));
    }
}
