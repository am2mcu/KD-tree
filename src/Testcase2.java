import java.io.FileNotFoundException;
import java.util.Arrays;

public class Testcase2 {
    public static void main(String[] args) throws FileNotFoundException {
        float[][] dataPoints = KNNClassifier.readDataPoints("trainEdited.csv");
        float[][] testData = KNNClassifier.readDataPoints("testEdited.csv");
        int[] testLabels = KNNClassifier.readLabels("test_labels.csv");
//        testData = Arrays.copyOfRange(testData, 0, 10);

        KNNClassifier knnClassifier = new KNNClassifier(dataPoints, 5);
        System.out.println(knnClassifier.accuracy(testLabels, knnClassifier.classifyAll(testData)) + " %");
    }
}
