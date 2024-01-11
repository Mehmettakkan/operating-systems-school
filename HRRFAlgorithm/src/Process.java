public class Process {
    // İşlem özelliklerini temsil eden değişkenler tanımlanır
    private int numProcesses; // İşlem numarası
    private int arrivalTime; // Varış zamanı
    private int burstTime; // Patlama zamanı
    private int completionTime; // Bitiş zamanı
    private int waitingTime; // Bekleme süresi

    // Yapıcı metot, işlem nesnesini oluşturur
    public Process(int numProcesses,int arrivalTime, int burstTime) {
        this.numProcesses= numProcesses;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.completionTime = 0;
        this.waitingTime = 0;
    }
    public int getNumProcesses() {
        return numProcesses;
    }

    public void setNumProcesses(int numProcesses) {
        this.numProcesses = numProcesses;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
    // İşlem bilgilerini string olarak döndüren toString() metodu
    @Override
    public String toString() {
        return "İşlem " + this.numProcesses + ": " +
                "varış zamanı: " + this.arrivalTime +
                ", patlama süresi: " + this.burstTime +
                ", bitiş zamanı: " + this.completionTime +
                ", bekleme süresi: " + this.waitingTime;
    }
}
