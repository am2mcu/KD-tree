import java.io.FileNotFoundException;
import java.util.Arrays;

public class TestTable {
    public static void main(String[] args) throws FileNotFoundException {
        float[][] records = Table.readFile("testTableFile");
        Table table = new Table(records);

        float[][] points = table.search(new float[]{25, 2500, 5}, new float[]{30, 3400, 15});
        for (float[] point: points) {
            System.out.println(Arrays.toString(point));
        }
    }
}
