public class Main {
    public static void main(String[] args) {

        //Buffer nesnesi oluşturuldu ve kapasitesi 100
        Buffer buffer = new Buffer(100);

        // 5 adet üretici iş parçacığı oluşturmak için bir dizi oluşturuldu
        Thread[] producers = new Thread[5];

        // Her bir üretici iş parçacığını başlat:
        for (int i = 0; i < producers.length; i++) {

            // Her üretici iş parçacığı oluşturulurken bu iş parçacığına buffer'a erişim sağlayan bir referans verilir.
            producers[i] = new Thread(new Producer(buffer));

            //Thread sınıfından türetilen producers dizisi, üretici iş parçacıklarını temsil eder.
            //Bu iş parçacıkları, Producer sınıfından türetilmiştir.
            //Her bir üretici iş parçacığı, bir Producer nesnesini alır ve bu nesnenin içindeki üretici işlemleri gerçekleştirir.
            //buffer nesnesi, üretici iş parçacıklarının ortak olarak kullanacakları buffer alanına erişimi sağlar.

            producers[i].start(); // İş parçacığını başlat.
        }

        // 5 adet tüketici iş parçacığı oluşturmak için bir dizi oluşturuldu.
        Thread[] consumers = new Thread[5];

        // Her bir tüketici iş parçacığını başlat:
        for (int i = 0; i < consumers.length; i++) {

            // Her tüketici iş parçacığı oluşturulurken bu iş parçacığına buffer ve tüketici adı verilir.
            consumers[i] = new Thread(new Consumer(buffer, "Tüketici-" + (i + 1)));

            //Thread sınıfından türetilen consumers dizisi, tüketici iş parçacıklarını temsil eder.
            // Bu iş parçacıkları, Consumer sınıfından türetilmiştir.
            //Her bir tüketici iş parçacığı, bir Consumer nesnesini alır ve bu nesnenin içindeki tüketici işlemleri gerçekleştirir.
            //buffer nesnesi, tüketici iş parçacıklarının ortak olarak kullanacakları buffer alanına erişimi sağlar.
            //Tüketici-" + (i + 1) ifadesi, her tüketici iş parçacığına benzersiz bir ad atar.

            consumers[i].start(); // İş parçacıkları başlatılır.
        }

        // Tüm üretici ve tüketici iş parçacıklarının tamamlanmasını bekleyelim.
        try {
            for (Thread producer : producers) {
                producer.join();
            }

            for (Thread consumer : consumers) {
                consumer.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
