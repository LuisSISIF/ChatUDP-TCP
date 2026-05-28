package chat.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ChatFactory {

    private static int DEFAULT_RECEIVER_BUFFER_SIZE = 1000;

    public static Sender build(
            boolean isConnectionOriented,
            String serverName,
            int serverPort,
            int localPort,
            MessageContainer container
    ) throws ChatException {

        try {

            // TCP
            if (isConnectionOriented) {

                new TCPReceiver(
                        localPort,
                        container
                );

                return new TCPSender(
                        InetAddress.getByName(serverName),
                        serverPort
                );

            }

            // UDP
            else {

                new UDPReceiver(
                        localPort,
                        DEFAULT_RECEIVER_BUFFER_SIZE,
                        container
                );

                return new UDPSender(
                        InetAddress.getByName(serverName),
                        serverPort
                );
            }

        } catch (UnknownHostException e) {

            throw new ChatException(
                    "Receiver desconhecido.",
                    e
            );
        }
    }
}