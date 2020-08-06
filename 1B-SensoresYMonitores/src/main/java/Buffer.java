import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    public List<Integer> metricas;
    private final Logger logger;

    public Buffer() {
        this.metricas = new ArrayList<>();
        this.logger = Logger.getLogger("SensorLogger");
    }
    public synchronized int read(int monitorId) {
        while(isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Removes the first element in the list
        int metric = this.metricas.remove(0);
        this.logger.log(Level.INFO, "Monitor{" + monitorId + "} Started processing metric{" + metric + "} from buffer, with current size: " + this.metricas.size());
        return metric;
    }

    public synchronized void write(int sensorId, int metric) {
        this.metricas.add(metric);
        this.logger.log(Level.INFO, "Sensor{" + sensorId + "} Writing metric{" + metric + "} into buffer, with current size: " + this.metricas.size());
        notifyAll();
    }
    public boolean isEmpty() {
        return this.metricas.size() == 0;
    }
}
