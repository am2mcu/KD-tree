import java.io.FileNotFoundException;

public class testKNNNewLabel {
    public static void main(String[] args) throws FileNotFoundException {
        float[][] dataPoints = KNNClassifier.readDataPoints("testEdited.csv");
        int[] labels = KNNClassifier.readLabels("test_labels.csv");

        KNNClassifier knnClassifier = new KNNClassifier(dataPoints, 5);
        System.out.println(knnClassifier.accuracy(labels, knnClassifier.classifyAll(dataPoints)) + " %");

        System.out.println("-----------------");

        dataPoints = KNNClassifier.readDataPoints("trainEdited.csv");
        float[][] test = KNNClassifier.readDataPoints("testEdited.csv");
        KNNClassifier knnClassifier1 = new KNNClassifier(dataPoints, 5);
        System.out.println(knnClassifier1.accuracy(labels, knnClassifier1.classifyAll(test)) + " %");
    }
}
