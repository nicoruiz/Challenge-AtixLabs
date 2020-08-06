import javax.management.monitor.Monitor;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class ThreadPool {
    public int sensorWorkersSize;
    public int monitorWorkersSize;
    public Timer timer;
    public Buffer buffer;
    public MonitorStats monitorStats;
    public List<SensorWorker> sensors;
    public List<MonitorWorker> monitors;

    public ThreadPool(int sensorWorkersSize, int monitorWorkersSize, Timer timer, Buffer buffer, MonitorStats monitorStats) {
        this.sensorWorkersSize = sensorWorkersSize;
        this.monitorWorkersSize = monitorWorkersSize;
        this.timer = timer;
        this.buffer = buffer;
        this.monitorStats = monitorStats;
        this.sensors = new ArrayList<>();
        this.monitors = new ArrayList<>();

        // Creo todos los workers necesarios
        this.createSensorWorkers();
        this.createMonitorWorkers();
    }

    private void createSensorWorkers() {
        for (int i=0; i < this.sensorWorkersSize; i++) {
            SensorWorker sensorWorker = new SensorWorker(i, buffer, timer);
            // Se setea el valor minimo y maximo de las métricas que producen los sensores
            sensorWorker.setMIN(1);
            sensorWorker.setMAX(100);
            // Se agregan a una lista por si se luego se quieren modificar variables o interrumpir el thread.
            this.sensors.add(sensorWorker);
            sensorWorker.start();
        }
    }

    private void createMonitorWorkers() {
        for (int i=0; i < this.monitorWorkersSize; i++) {
            MonitorWorker monitorWorker = new MonitorWorker(i, buffer, timer, monitorStats);
            // Se agregan a una lista por si se luego se quieren modificar variables o interrumpir el thread.
            this.monitors.add(monitorWorker);
            monitorWorker.start();
        }
    }

    public void stopWorkers() {
        this.timer.cancel();
        this.sensors.forEach(s -> s.interrupt());
        this.monitors.forEach(m -> m.interrupt());
    }
}
