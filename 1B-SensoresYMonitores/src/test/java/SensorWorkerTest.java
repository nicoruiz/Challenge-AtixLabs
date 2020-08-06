import junit.framework.TestCase;

import java.util.Timer;

public class SensorWorkerTest extends TestCase {
    private Timer timer;
    private Buffer buffer;
    private SensorWorker sensorWorker;

    protected void setUp(){
        this.timer = new Timer();
        this.buffer = new Buffer();
        this.sensorWorker = new SensorWorker(1, this.buffer, timer);
        this.sensorWorker.setMIN(1); this.sensorWorker.setMAX(100);
    }

    public void testSensorEnvia2MedicionesPorSegundoYAlDormirAlThreadPor3SegundosDeberiaTener6MetricasEnElBuffer() throws InterruptedException {
        this.sensorWorker.start();
        Thread.sleep(3000);
        timer.cancel();
        assertTrue(this.buffer.metricas.size() > 5);
    }
}
