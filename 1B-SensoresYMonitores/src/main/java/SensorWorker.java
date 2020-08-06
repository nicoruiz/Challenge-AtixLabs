import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SensorWorker extends Thread {
    private static int MIN;
    private static int MAX;
    public int id;
    public Buffer buffer;
    public Timer timer;

    public SensorWorker(int id, Buffer buffer, Timer timer) {
        this.id = id;
        this.buffer = buffer;
        this.timer = timer;
    }

    @Override
    public void run() {
        // Este thread se encarga de envíar metricas al buffer dos veces por segundo.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                addMetricsToBuffer();
            }
        }, 0, 500);
    }

    public void addMetricsToBuffer() {
        this.buffer.write(this.id, this.getRandomNumber());
    }

    public int getRandomNumber() {
        Random random = new Random();
        return MIN + random.nextInt(MAX);
    }

    public void setMIN(int MIN) {
        SensorWorker.MIN = MIN;
    }
    public void setMAX(int MAX) {
        SensorWorker.MAX = MAX;
    }

}
