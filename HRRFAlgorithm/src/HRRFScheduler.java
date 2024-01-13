public class HRRFScheduler {
    // İşlemleri ve yanıt oranlarını depolayan diziler oluşturuldu
    private Process[] processes;
    private double[] responseRatios;
    // Yapıcı metot, işlem dizilerini aldı
    public HRRFScheduler(Process[] processes) {
        this.processes = processes;
        this.responseRatios = new double[processes.length];
    }

    public void schedule() {
        // Her sürecin yanıt oranını hesaplayın
        for (int i = 0; i < processes.length; i++) {
            responseRatios[i] = (double) (processes[i].getBurstTime()) / (processes[i].getArrivalTime() + processes[i].getBurstTime());
        }

         // Yanıt oranları en yüksek olan süreci bulmak için kullanılacak olan indeks.
        int maxIndex;

        // Süreçlerin sayısı kadar döngü yapılır.
        for (int i = 0; i < processes.length; i++) {

            // Başlangıçta en yüksek yanıt oranına sahip sürecin indeksini, şu anki indeks olarak ayarla.
            maxIndex = i;

            // İçteki döngü, dıştaki döngüde belirlenen süreci geride kalan süreçlerle karşılaştırır.
            for (int j = i + 1; j < processes.length; j++) {

                // Eğer şu anki sürecin yanıt oranı, şu ana kadar bulunan en yüksek yanıt oranına sahip sürecin yanıt oranından büyükse,
                if (responseRatios[j] > responseRatios[maxIndex]) {

                    // En yüksek yanıt oranına sahip sürecin indeksini güncelle.
                    maxIndex = j;
                }
            }

            // En yüksek yanıt oranına sahip süreci, şu anki döngüde belirlenen süreçle değiştir (sırala).
            // Geçici bir değişken kullanarak iki süreci takas etmek için bu blok kullanılır.

            // İki süreci takas etmek için bir geçici değişken oluştur.
            Process temp = processes[maxIndex];

            // İlk süreci, diğer süreçle takas ederek sıralamayı gerçekleştirir.
            processes[maxIndex] = processes[i];

            // İkinci süreci, geçici değişken içinde saklanan süreçle takas ederek sıralamayı tamamlar.
            processes[i] = temp;


            // Sürecin varış zamanını bekleme süresine ekleyin
            processes[i].setWaitingTime(processes[i].getArrivalTime());

            // Sürecin bitiş zamanını hesaplayın
            processes[i].setCompletionTime(processes[i].getArrivalTime() + processes[i].getBurstTime());

            // Bir sonraki sürecin yanıt oranını hesaplamak için şu andaki yanıt oranını güncelleyin
            if (i < processes.length - 1) {
                responseRatios[i + 1] = responseRatios[i + 1] + (processes[i + 1].getArrivalTime() - processes[i].getCompletionTime());
            }
        }
    }

    public Process[] getProcesses() {
        return processes;
    }
}
