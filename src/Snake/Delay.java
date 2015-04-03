package Snake;

/**
 * Created by FreeFly on 03.04.2015.
 */
public class Delay {
    private long delayNanos;
    private long passedNanos;
    public Delay(long delayMillis) {
        this.delayNanos = TimeConverter.millisToNanos(delayMillis);
    }
    public boolean updateAndCheck(long deltaNanos) {
        passedNanos += deltaNanos;
        if (passedNanos > delayNanos) {
            passedNanos = 0;
            return true;
        } else {
            return false;
        }
    }
}
