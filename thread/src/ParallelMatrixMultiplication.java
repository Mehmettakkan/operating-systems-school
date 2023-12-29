public class ParallelMatrixMultiplication {

    public static void main(String[] args) {
        // İki adet 3x3 boyutunda matris oluşturuluyor.
        int[][] matrix1 = new int[3][3];
        int[][] matrix2 = new int[3][3];

        // Matrislere rastgele değerler atanıyor.
        matrixValueAssign(matrix1);
        matrixValueAssign(matrix2);

        // Matris 1'in değerleri ekrana yazdırılıyor.
        System.out.println("Matrix 1: ");
        matrixPrint(matrix1);

        // Matris 2'nin değerleri ekrana yazdırılıyor.
        System.out.println("Matrix 2: ");
        matrixPrint(matrix2);

        // Çarpım sonucu için bir matris oluşturuluyor.
        int[][] result = new int[matrix1.length][matrix2[0].length];

        // Çarpmaya başlamadan önce zaman ölçümü başlatılıyor.
        long startTime = System.nanoTime();

        // Paralel matris çarpımı gerçekleştiriliyor.
        multiplyMatricesParallel(matrix1, matrix2, result);

        // Çarpma işlemi tamamlandıktan sonra geçen süre hesaplanıyor.
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        // Çarpım sonucu elde edilen matris ekrana yazdırılıyor.
        System.out.println("Result: ");
        matrixPrint(result);

        // Geçen süre ekrana yazdırılıyor.
        System.out.println("Time in nanoseconds: " + elapsedTime);
    }

    // Paralel matris çarpımını gerçekleştiren fonksiyon.
    private static void multiplyMatricesParallel(int[][] matrix1, int[][] matrix2, int[][] result) {
        // İlk matrisin satır sayısı alınıyor.
        int rowCount = matrix1.length;

        // İş parçacıklarını tutan bir dizi oluşturuluyor.
        Thread[] threads = new Thread[rowCount];

        // Her bir satır için bir iş parçacığı oluşturuluyor ve çarpma işlemi gerçekleştiriliyor.
        for (int i = 0; i < rowCount; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                multiplyRow(matrix1, matrix2, result, row);
            });
            threads[i].start(); // İş parçacığı başlatılıyor.
        }

        // Tüm iş parçacıklarının bitmesini bekleyerek devam ediliyor.
        for (Thread thread : threads) {
            try {
                thread.join(); // İş parçacığının bitmesini bekler.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Belirli bir satırın çarpımını gerçekleştiren fonksiyon.
    private static void multiplyRow(int[][] matrix1, int[][] matrix2, int[][] result, int row) {
        // İlk matrisin sütun sayısı ve ikinci matrisin sütun sayısı alınıyor.
        int matrix1Cols = matrix1[0].length;
        int matrix2Cols = matrix2[0].length;

        // İki matrisin çarpımı gerçekleştiriliyor.
        for (int j = 0; j < matrix2Cols; j++) {
            for (int k = 0; k < matrix1Cols; k++) {
                result[row][j] += matrix1[row][k] * matrix2[k][j];
            }
        }
    }

    // Matrise rastgele değerler atan fonksiyon.
    private static void matrixValueAssign(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
    }

    // Matrisi ekrana yazdıran fonksiyon.
    private static void matrixPrint(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
