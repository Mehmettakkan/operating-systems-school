import java.util.ArrayList; //Görevleri depolamak için kullanılır.
import java.util.Collections; //Koleksiyonları sıralamak ve manipüle etmek için yardımcı yöntemler sağlar.

public class Main {

    public static void main(String[] args) {

        // Görevleri tanımla
        ArrayList<Task> tasks = new ArrayList<>();//Görevleri depolamak için bir ArrayList oluştur.
        tasks.add(new Task(1, 10));
        tasks.add(new Task(2, 5));
        tasks.add(new Task(3, 2));

        // Görevleri son teslim tarihlerine göre sırala
        // Amaç: Görevleri son teslim tarihlerine göre yükselen sırada sıralar.
        Collections.sort(tasks, (task1, task2) -> task1.deadline - task2.deadline);

        // Zaman eksenini oluştur
        int[] times = new int[11];
        for (int i = 0; i < times.length; i++) {
            times[i] = -1; // Boş zaman dilimlerini belirlemek için -1 kullanılır.
        }

        // Grafiği çiz
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            int start = task.start();
            int end = Math.min(task.deadline, times.length); // Sınırı kontrol et

            for (int i = start; i < end; i++) {
                if (times[i] == -1) { // Sadece boş zaman dilimlerine yaz
                    times[i] = task.id;
                    output.append((char) ('A' + task.id - 1)); // Görevin id'sini harfe çevirerek output'a ekle
                }
            }
        }

        // Grafiği yazdır
        System.out.println(output.toString());
    }

}
