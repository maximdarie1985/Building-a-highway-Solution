
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class MyFitnessFunction {


    private int pathLength = 0;
    private int[] path = null;
    private int[] seq = null;
    private int vertexCount;
    private int[][] matrix;


    public MyFitnessFunction(String filename) throws FileNotFoundException {
        super();
        Scanner in = new Scanner(new FileReader(filename));
        this.vertexCount = in.nextInt();
        this.matrix = new int[this.vertexCount][this.vertexCount];
        for (int i = 0; i < this.vertexCount; i++) {
            for (int j = 0; j < this.vertexCount; j++) {
                this.matrix[i][j] = in.nextInt();
            }
        }
        in.close();
        this.pathLength = vertexCount;
        this.path = new int[this.pathLength];
        this.seq = new int[this.pathLength];
    }


    public static void printLongInBin(long l, int last) {
        if (last > 0) {
            int p = (int) (l & 1);
            printLongInBin(l >> 1, --last);
            System.out.print(p);
        }
    }

    public static void generateRandomFile(String filename, int n) throws IOException {
        Random random = new Random();
        BufferedWriter out = new BufferedWriter(new FileWriter(filename));
        out.write(n + "\n");

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                matrix[i][j] = random.nextInt(256);
                matrix[j][i] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.write(matrix[i][j] + " ");
            }
            out.write("\n");
        }

        out.flush();
        out.close();
    }
}