import java.util.logging.Level;
import java.util.logging.Logger;

public class MonitorStats {
    private static int S;
    private static double M;
    public int minActual;
    public int maxActual;
    public double promActual;
    private final Logger logger;

    public MonitorStats() {
        this.minActual = 0;
        this.maxActual = 0;
        this.promActual = 0;
        this.logger = Logger.getLogger("MonitorStatsLogger");
    }

    public void setS(int S) {
        MonitorStats.S = S;
    }
    public void setM(double M) {
        MonitorStats.M = M;
    }

    public synchronized void processMetric(int metric) {
        this.calculateStats(metric);
        this.checkAgainstS();
        this.checkAgainstM();
    }

    public void calculateStats(int metric) {
        if(this.minActual == 0 || metric < this.minActual)
            this.minActual = metric;
        if(this.maxActual == 0 || metric > this.maxActual)
            this.maxActual = metric;
        this.promActual = this.promActual == 0 ? metric : (this.promActual + metric) / 2;
    }

    public void checkAgainstS() {
        int diffToCheck = this.maxActual - this.minActual;
        if(diffToCheck > MonitorStats.S) {
            String msg = "The difference{"+diffToCheck+"} between the max and the min values is higher than the S{"+MonitorStats.S+"} constant";
            this.logger.log(Level.SEVERE, msg);
        }
    }
    public void checkAgainstM() {
        if(this.promActual > MonitorStats.M) {
            String msg = "The average{"+this.promActual+"} is higher than the M{"+MonitorStats.M+"} constant";
            this.logger.log(Level.SEVERE, msg);
        }
    }
}
