import java.util.concurrent.Semaphore;
class Bridge {
    private Semaphore semaphore;
    private int directionCount; // Köprüdeki araç sayısını tutan değişken

    public Bridge() {
        semaphore = new Semaphore(1);  // Semaforu başlat, sadece bir izin ver
        directionCount = 0;  // Başlangıçta köprüde araç yok
    }

    // Araç köprüye giriş yapan metot
    public void enter(String direction) throws InterruptedException {
        System.out.println(direction + " yönden araç köprüye geldi.");

        // Eğer köprüde araç varsa veya karşı yönden araçlar geçiyorsa beklet
        //Bu ifade, this nesnesi üzerinde senkronizasyon yapar.
        // Yani, aynı anda sadece bir iş parçacığının bu kritik bölgeye girebileceğini belirtir.
        synchronized (this) {
            while (directionCount != 0) {
                wait();  // Bekleme durumunda diğer iş parçacıklarına fırsat ver
            }
            directionCount++;
            //beklemeye giren araç, bekleyen araç sayısını temsil eden directionCount değerini artırır.
            // Böylece diğer araçlar, bekleyen araç sayısı 0 olana kadar beklemeye devam eder.
        }

        System.out.println(direction + " yönden araç köprüye girdi.");

        // Köprüden geçiş sırasını beklet
        semaphore.acquire();  // Semafor izin verene kadar beklet

        System.out.println(direction + " yönden araç köprüden geçiyor.");

        // Köprüden çıkış iznini serbest bırak
        semaphore.release();  // Semaforu serbest bırak, diğer iş parçacıklarına izin ver

        // Aracın köprüden çıktığını belirt
        synchronized (this) {
            directionCount--;
            notifyAll();  // Bekleyen diğer araçlara haber ver
            System.out.println(direction + " yönden araç köprüden çıktı.");
        }
    }
}