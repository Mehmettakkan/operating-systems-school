// 1. Buffer Alanı: Ortak kullanılacak olan buffer alanının kapasitesi 100 olsun.
//    Bu buffer, üretici ve tüketici iş parçacıkları arasında veri iletişimi için kullanılacaktır.

//veri depolamak için kullanılır.
public class Buffer {
    // Buffer için bir dizi, boyutu ve geçerli öğe sayısı
    private int[] buffer;
    private int size;
    private int count;

    // Buffer sınıfının constructor'u
    // capacity parametresi, buffer'ın boyutunu belirtir.
    public Buffer(int capacity) {
        // buffer dizisini oluşturur.
        this.buffer = new int[capacity];

        // size değişkenini capacity değerine ayarlar.
        this.size = capacity;

        //ilk başta geçerli öğe yok.
        this.count = 0;
    }

    // 5. İş Parçacıklar Arası İletişim: Üretici iş parçacıkları buffer alanına ürettikleri sayıları eklerken,
    //    tüketici iş parçacıkları buffer alanından sayıları çekerken monitör yapısı kullanarak senkronize bir şekilde çalışmalıdır.
    //    İş parçacıkları arasındaki senkronizasyon ve veri bütünlüğü burada sağlanmaktadır.

    // 4. Monitör Yapısı: Monitör yapısını kullanarak, üretici iş parçacıklarının ve tüketici iş parçacıklarının
    //    güvenli bir şekilde buffer alanını paylaşmasını sağlayın.
    //    İş parçacıkları arasındaki senkronizasyonu ve veri bütünlüğünü monitör yapısıyla sağlamaktasınız.


    // Üretici tarafından kullanılacak olan produce metodu
    // item parametresi, buffer'a eklenecek veri.
    public synchronized void produce(int item) throws InterruptedException {
        // Buffer dolu ise bekle
        while (count == size) {
            wait(); // Bu metod, iş parçacığını beklemeye alır.
        }

        // item değerini buffer'ın sonuna ekler.
        // count değişkenini bir arttırır.
        buffer[count++] = item;
        System.out.println("Üretildi: " + item + ", Toplam: " + count);

        // Bu metod, bekleyen tüm iş parçacıklarını uyandırır.
        notifyAll();
    }


    // Tüketici tarafından kullanılacak olan consume metodu
    // consumerName parametresi, tüketicinin adını belirtir.
    public synchronized int consume(String consumerName) throws InterruptedException {
        // Buffer boş ise bekle
        while (count == 0) {
            wait();
        }

        // buffer'ın son öğesini alır.
        // count değişkenini bir azaltır.
        int item = buffer[--count];
        System.out.println(consumerName + " tarafından tüketildi: " + item + ", Toplam: " + count);

        // Bu metod, bekleyen tüm iş parçacıklarını uyandırır.
        notifyAll();

        return item;
    }
}