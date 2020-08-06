import java.util.Timer;
import java.util.TimerTask;

public class MonitorWorker extends Thread{
    public int id;
    public Buffer buffer;
    public Timer timer;
    private MonitorStats monitorStats;

    public MonitorWorker(int id, Buffer buffer, Timer timer, MonitorStats monitorStats) {
        this.id = id;
        this.buffer = buffer;
        this.timer = timer;
        this.monitorStats = monitorStats;
    }

    @Override
    public void run() {
        // Este thread se encarga de procesar una métrica del buffer una vez por minuto.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                processMetricFromBuffer();
            }
        }, 0, 3000);
    }

    public void processMetricFromBuffer() {
        int metric = this.buffer.read(this.id);
        this.monitorStats.processMetric(metric);
    }
}
