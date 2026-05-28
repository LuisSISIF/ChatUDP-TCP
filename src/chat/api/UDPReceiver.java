package chat.api;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver implements Receiver {

    private DatagramSocket socket;

    private MessageContainer container;

    private int bufferSize;

    public UDPReceiver(
            int localPort,
            int bufferSize,
            MessageContainer container
    ) throws ChatException {

        this.bufferSize = bufferSize;

        this.container = container;

        try {

            socket =
                    new DatagramSocket(localPort);

            new Thread(this).start();

        } catch (Exception e) {

            throw new ChatException(
                    "Erro ao iniciar UDP Receiver",
                    e
            );
        }
    }

    @Override
    public void run() {

        while (true) {

            try {

                byte[] buffer =
                        new byte[bufferSize];

                DatagramPacket packet =
                        new DatagramPacket(
                                buffer,
                                buffer.length
                        );

                socket.receive(packet);

                String message =
                        new String(
                                packet.getData(),
                                0,
                                packet.getLength()
                        );

                container.newMessage(
                        "Remoto: " + message
                );

            } catch (Exception e) {

                container.newMessage(
                        "Erro ao receber mensagem UDP"
                );
            }
        }
    }
}