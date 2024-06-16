import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class KNNClassifier {
    private KDTree kdTree;
    private final int neighborSearchNum;
    final static int DIM = 28 * 28;

    KNNClassifier(float[][] train_data, int k) {
        neighborSearchNum = k;
        kdTree = new KDTree(DIM);
        kdTree = kdTree.createTree(train_data);
    }

    int classify(float[] data_point) {
        int[] labels = new int[neighborSearchNum];
        float[][] nearPoints = kdTree.findMNearest(data_point, neighborSearchNum);
        for (int i = 0; i < nearPoints.length; i++) {
            labels[i] = (int) nearPoints[i][DIM];
        }

        return mostFrequentElement(labels);
    }

    int mostFrequentElement(int[] arr) {
        Arrays.sort(arr);
        int maxFrequent = 0;
        int maxLabel = -1;

        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
                count++;
                i++;
            }
            if (count > maxFrequent) {
                maxFrequent = count;
                maxLabel = arr[i];
            }
        }
        return maxLabel;
    }

    int[] classifyAll(float[][] data_points) {
        int[] labels = new int[data_points.length];
        for (int i = 0; i < data_points.length; i++) {
            labels[i] = classify(data_points[i]);
        }
        return labels;
    }

    float accuracy(int [] labels_true, int[] labels_predicted) {
        float truePredictions = 0;
        for (int i = 0; i < labels_predicted.length; i++) {
            if (labels_true[i] == labels_predicted[i]) {
                truePredictions++;
            }
        }
        return (truePredictions / labels_predicted.length) * 100;
    }

    static float[][] readDataPoints(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        scanner.useDelimiter(",|\r\n|\n");

        KDArrayList<float[]> dataset = new KDArrayList<>();
        while (scanner.hasNext()) {
            float[] data = new float[DIM + 1];
            for (int i = 0; i < data.length; i++) {
                data[i] = scanner.nextFloat();
            }

            dataset.add(data);
        }

        return dataset.toArray(new float[dataset.size()][DIM + 1]);
    }

    static int[] readLabels(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));

        KDArrayList<Integer> labels = new KDArrayList<>();
        while (scanner.hasNext()) {
            labels.add(scanner.nextInt());
        }

        int[] result = new int[labels.size()];
        for (int i = 0; i < labels.size(); i++) {
            result[i] = labels.get(i);
        }
        return result;
    }
}
