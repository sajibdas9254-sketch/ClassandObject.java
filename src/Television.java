public class Television {
    private static final int MIN_CHANNEL = 1;
    private static final int MAX_CHANNEL = 10;

    private boolean on;
    private int channel;

    public Television() {
        on = false;
        channel = MIN_CHANNEL;
    }

    public boolean isOn() {
        return on;
    }

    public void pressOnOff() {
        on = !on;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int newChannel) {
        if (!on) {
            return; // channel can only be changed when the TV is on
        }
        if (newChannel > MAX_CHANNEL) {
            channel = MIN_CHANNEL;     // after 10 comes 1
        } else if (newChannel < MIN_CHANNEL) {
            channel = MAX_CHANNEL;
        } else {
            channel = newChannel;
        }
    }
}