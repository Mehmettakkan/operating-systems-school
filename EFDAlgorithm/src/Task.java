public class Task {
    int id;
    int duration;
    int deadline;

    public Task(int id, int duration) { // ID'si ve süresi ile yeni bir görev başlatır.
        this.id = id;
        this.duration = duration;
        this.deadline = duration + id; // Son teslim tarihi, süre + ID olarak hesaplanır
    }

    public int start() { //Görevin hesaplanan başlangıç zamanını döndürür
        return deadline - duration; // Son teslim tarihi ve süreye göre başlangıç zamanını hesaplar
    }
}