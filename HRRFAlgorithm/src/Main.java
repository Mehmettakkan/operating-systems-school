import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Kullanıcı girişi için Scanner nesnesi oluşturulur
        Scanner scanner = new Scanner(System.in);

        // Kullanıcıdan işlem sayısını alır
        System.out.print("İşlem sayısını girin: ");
        int numProcesses = scanner.nextInt();

        // İşlemleri depolamak için bir dizi oluşturulur
        Process[] processes = new Process[numProcesses];

        // Her işlem için varış ve patlama sürelerini alın
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("İşlem " + (i + 1) + " için varış zamanını girin: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("İşlem " + (i + 1) + " için patlama zamanını girin: ");
            int burstTime = scanner.nextInt();

            // İşlem nesnesi oluşturulur ve diziye eklenir
            processes[i] = new Process((i+1),arrivalTime, burstTime);
        }

        // HRRF algoritmasını uygulamak için yeni bir HRRFScheduler nesnesi oluşturulur
        HRRFScheduler scheduler = new HRRFScheduler(processes);

        // Algoritmayı çalıştırır
        scheduler.schedule();

        // İşlemlerin programını yazdırır
        System.out.println("\nİşlemlerin programı:");
        for (Process process : scheduler.getProcesses()) {
            System.out.println(process);
        }

        // Ortalama bekleme süresini hesaplayın
        double averageWaitingTime = 0;
        for (Process process : scheduler.getProcesses()) {
            averageWaitingTime += process.getWaitingTime();
        }
        averageWaitingTime /= numProcesses;

        System.out.println("\nOrtalama bekleme süresi: " + averageWaitingTime);
    }
}
