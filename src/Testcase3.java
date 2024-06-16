import java.io.FileNotFoundException;
import java.util.Arrays;

public class Testcase3 {
    public static void main(String[] args) throws FileNotFoundException {
        float[][] records = Table.readFile("data.csv");

        Table table = new Table(records);

        float[][] result = table.search(new float[]{20, 15, 10, 15, 10}, new float[]{50, 90, 80, 40, 85});

        for (float[] node: result) {
            System.out.println(Arrays.toString(node));
        }
    }
}
