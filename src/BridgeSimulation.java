class BridgeSimulation {
    public static void main(String[] args) {
        Bridge bridge = new Bridge();

        // Basit bir simülasyon: 5 araç köprüyü aşağı ve 5 araç yukarıya geçiyor
        for (int i = 0; i < 5; i++) {
            // Aşağı yönden gelen araç
            Thread carDown = new Thread(() -> {
                try {
                    bridge.enter("Aşağı");
                    // Her araçtan sonra 1 saniye bekle
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Yukarı yönden gelen araç
            Thread carUp = new Thread(() -> {
                try {
                    bridge.enter("Yukarı");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            carDown.start();  // Aşağı yönden gelen aracı başlat
            carUp.start();    // Yukarı yönden gelen aracı başlat

            try {
                carDown.join();  // Aşağı yönden gelen aracın tamamlanmasını bekle
                carUp.join();    // Yukarı yönden gelen aracın tamamlanmasını bekle
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}