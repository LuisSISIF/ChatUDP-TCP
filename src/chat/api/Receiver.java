package chat.api;

public interface Receiver extends Runnable {

    @Override
    void run();
}