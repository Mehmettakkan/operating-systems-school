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

        // Yanıt oranları en yüksek olan süreci bulun
        int maxIndex;
        for (int i = 0; i < processes.length; i++) {
            maxIndex = i;
            for (int j = i + 1; j < processes.length; j++) {
                if (responseRatios[j] > responseRatios[maxIndex]) {
                    maxIndex = j;
                }
            }

            // En yüksek yanıt oranına sahip süreci sırala
            Process temp = processes[maxIndex];
            processes[maxIndex] = processes[i];
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
