import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Table {
    static final int DIM = 5;
    private final KDTree kdTree;
    Table(float[][] records) {
        kdTree = new KDTree(DIM).createTree(records);
    }

    float[][] search(float[] lower_bounds, float[] upper_bounds) {
        return kdTree.searchRange(lower_bounds, upper_bounds);
    }

    static float[][] readFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        scanner.useDelimiter(",|\r\n|\n");

        KDArrayList<float[]> records = new KDArrayList<>();
        while (scanner.hasNext()) {
            float[] record = new float[DIM];
            for (int i = 0; i < DIM; i++) {
                record[i] = scanner.nextFloat();
            }

            records.add(record);
        }

        return records.toArray(new float[records.size()][DIM]);
    }
}
