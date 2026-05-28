package chat.api;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSender implements Sender {

    private DatagramSocket senderSocket;

    private InetAddress receiver;

    private int receiverPort;

    public UDPSender(
            InetAddress receiver,
            int receiverPort
    ) throws ChatException {

        this.receiver = receiver;

        this.receiverPort = receiverPort;

        try {

            this.senderSocket =
                    new DatagramSocket();

        } catch (SocketException e) {

            throw new ChatException(
                    "Erro ao iniciar UDP Sender.",
                    e
            );
        }
    }

    @Override
    public void send(String message)
            throws ChatException {

        byte[] messageBytes =
                message.getBytes();

        DatagramPacket datagramPacket =
                new DatagramPacket(
                        messageBytes,
                        messageBytes.length,
                        receiver,
                        receiverPort
                );

        try {

            senderSocket.send(datagramPacket);

        } catch (IOException e) {

            throw new ChatException(
                    "Erro ao enviar mensagem UDP.",
                    e
            );
        }
    }
}