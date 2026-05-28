package chat.api;

public interface Sender {

    void send(String message)
            throws ChatException;
}