import java.util.Timer;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Cada sensorWorker se encarga de enviar dos valores numéricos por segundo
        int sensorWorkersSize = 4;
        // Cada monitorWorker se encarga de analizar un valor del sensor por minuto (por eso utilizo dos workers)
        int monitorWorkersSize = 2;

        Timer timer = new Timer();
        Buffer buffer = new Buffer();
        MonitorStats monitorStats = new MonitorStats();
        // Seteo las constantes S y M, modificables en cualquier momento
        monitorStats.setS(20);
        monitorStats.setM(30);

        ThreadPool threadPool = new ThreadPool(sensorWorkersSize, monitorWorkersSize, timer, buffer, monitorStats);

        // stop all workers after 5 minutes
        // Thread.sleep(300000);
        // threadPool.stopWorkers();
    }
}
