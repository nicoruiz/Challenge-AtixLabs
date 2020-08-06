import junit.framework.TestCase;

import java.util.Timer;

public class MonitorWorkerTest extends TestCase {
    private Timer timer;
    private Buffer buffer;
    private MonitorStats monitorStats;
    private MonitorWorker monitorWorker1;
    private MonitorWorker monitorWorker2;

    protected void setUp(){
        this.timer = new Timer();
        // buffer
        this.buffer = new Buffer();
        this.buffer.write(1, 50); this.buffer.write(1, 80);
        // monitor stats
        this.monitorStats = new MonitorStats();
        this.monitorStats.setS(20);
        this.monitorStats.setM(30);
        // monitor workers
        this.monitorWorker1 = new MonitorWorker(1, this.buffer, this.timer, this.monitorStats);
        this.monitorWorker2 = new MonitorWorker(2, this.buffer, this.timer, this.monitorStats);
    }

    public void testDosMonitorWorkersAnalizaUnBufferConDosMetricasYPasadosLos60SegundosElSizeDelBufferEs0() throws InterruptedException {
        this.monitorWorker1.start();
        this.monitorWorker2.start();
        Thread.sleep(60000);
        timer.cancel();
        assertTrue(this.buffer.isEmpty());
    }
}
