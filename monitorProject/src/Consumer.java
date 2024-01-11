import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// 3. Tüketici İş Parçacıkları: Beş tüketici iş parçacığı oluşturun.
//    Her bir tüketici iş parçacığı, buffer alanından çektikleri sayıları kendi isimleri altında bir ortak dosyaya yazacaklardır.


// Tüketici iş parçacıklarını temsil eder.
public class Consumer implements Runnable {
    private Buffer buffer;
    private String name; // Tüketicinin adını belirtir.

    // Consumer sınıfının yapıcı metodu, buffer ve tüketici adını alır.
    public Consumer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    // Runnable interface'ini implemente eden run metodu, tüketici işlemleri gerçekleştirir.
    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                // Buffer'dan veriyi çek
                int item = buffer.consume(name);

                // Dosyaya yazma işlemleri
                try {
                    writeToFile(item);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // İşlemi yavaşlatmak için bekletme
                Thread.sleep(50); // İşlemi yavaşlatmak için
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 6. Dosya İsimlendirme: Tüketici iş parçacıkları buffer alanından çektikleri sayıları Sayılar.txt dosyasına yazmalıdırlar.
    //    Bu kısım dosyaya yazma işlemleri için kullanılabilir
    public void writeToFile(int item) throws IOException {
        // Dosya oluşturma
        File file = new File("Numbers.txt");

        // Eğer dosya zaten varsa tekrar oluşturmaya gerek yok
        if (!file.exists()) {
            file.createNewFile();
        }

        // Dosya yazma
        // FileWriter sınıfını kullanarak dosyaya yazma işlemi yapılır
        // true parametresi, dosyanın sonuna eklemek üzere açılmasını sağlar
        FileWriter fileWriter = new FileWriter(file, true);

        // Dosyaya tamsayıyı yaz ve bir alt satıra geç
        fileWriter.write(item + "\n");

        // Dosya yazma işlemi tamamlandı, dosya kapatılır
        fileWriter.close();
    }
}