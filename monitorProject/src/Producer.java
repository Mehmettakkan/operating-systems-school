import java.util.Random;
// 2. Üretici İş Parçacıkları: Beş üretici iş parçacığı oluşturun.
//    Her bir üretici iş parçacığı, 1 ile 100 arasında rastgele 50 adet sayı üretecektir.
//    Bu sayıları buffer alanına yazacaklardır.


// Üretici iş parçacıklarını temsil eder.
public class Producer implements Runnable {

    private Buffer buffer; // Buffer'a erişmek için kullanılır.
    private Random random; // Rastgele sayılar üretmek için kullanılır.



    // Üretici sınıfının constructor'u
    // buffer parametresi, buffer'a erişmek için kullanılır.
    public Producer(Buffer buffer) {
        this.buffer = buffer;
        this.random = new Random(); // random değişkenini yeni bir Random nesnesi oluşturur.
    }


    // İş parçacığının çalışması için gerekli kod.
    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                // 1 ile 100 arasında rastgele bir sayı üret
                int item = random.nextInt(100) + 1;

                // Buffer'a veriyi ekle
                buffer.produce(item);

                // İşlemi yavaşlatmak için bekletme
                Thread.sleep(random.nextInt(100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // İstisnayı konsola yazdırır.
        }
    }
}
