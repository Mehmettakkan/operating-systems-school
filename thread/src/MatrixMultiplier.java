public class MatrixMultiplier {
    public static void main(String[] args) {

        // İki adet 3x3 boyutunda matris oluşturuluyor.
        int[][] matrix1 = new int[3][3];
        int[][] matrix2 = new int[3][3];

        // Her iki matris de rastgele değerlerle dolduruluyor.
        matrixValueAssign(matrix1);
        matrixValueAssign(matrix2);

        // Matris 1'in değerleri ekrana yazdırılıyor.
        System.out.println("Matrix 1: ");
        matrixPrint(matrix1);

        // Matris 2'nin değerleri ekrana yazdırılıyor.
        System.out.println("Matrix 2: ");
        matrixPrint(matrix2);

        // Çarpma işlemi başlamadan önce zaman ölçümü başlatılıyor.
        long startTime = System.nanoTime();

        // Matris çarpımı gerçekleştiriliyor.
        System.out.println("Result: ");
        int[][] result = multiplyMatrices(matrix1, matrix2);

        // Çarpım sonucu elde edilen matris ekrana yazdırılıyor.
        matrixPrint(result);

        // Çarpma işlemi tamamlandıktan sonra geçen süre hesaplanıyor.
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        // Geçen süre ekrana yazdırılıyor.
        System.out.println("Time in nanoseconds: " + elapsedTime);
    }

    // İki matrisin çarpımını hesaplayan fonksiyon.
    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {

        //Matris çarpımı, iki matrisin satırlarının ve sütunlarının iç çarpımlarının toplamıdır.


        // İlk matrisin satır ve sütun sayıları al.
        int matrix1Rows = matrix1.length;
        int matrix1Cols = matrix1[0].length;

        // İkinci matrisin sütun sayısı al
        int matrix2Cols = matrix2[0].length;

        // Çarpım sonucu elde edilecek matrisin boyutları belirleniyor.
        int[][] productMatrix = new int[matrix1Rows][matrix2Cols];

        // İlk iç for döngüsü, birinci matrisin satırlarını dolaşmak için kullanılır.
        // İkinci iç for döngüsü, ikinci matrisin sütunlarını dolaşmak için kullanılır.
        // Dış for döngüsü ise, çarpma sonucu elde edilecek matrisin satırlarını dolaşmak için kullanılır.
        for (int i = 0; i < matrix1Rows; i++) {
            for (int j = 0; j < matrix2Cols; j++) {
                for (int k = 0; k < matrix1Cols; k++) {
                    productMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        // Çarpım sonucu elde edilen matris döndürülüyor.
        return productMatrix;
    }

    // Matrise rastgele değerler atayan fonksiyon.
    public static void matrixValueAssign(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = ((int) (Math.random() * 10));
            }
        }
    }

    // Matrisi ekrana yazdıran fonksiyon.
    public static void matrixPrint(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
