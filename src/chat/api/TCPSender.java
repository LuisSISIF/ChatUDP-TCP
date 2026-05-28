package chat.api;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class TCPSender implements Sender {

    private Socket socket;

    private DataOutputStream outputFlow;

    private InetAddress receiver;

    public TCPSender(
            InetAddress receiver,
            int receiverPort
    ) throws ChatException {

        this.receiver = receiver;

        try {

            boolean isConnected = false;

            while (!isConnected) {

                try {

                    this.socket =
                            new Socket(
                                    this.receiver,
                                    receiverPort
                            );

                    isConnected = true;

                } catch (ConnectException e) {

                    // tenta novamente
                }
            }

            this.outputFlow =
                    new DataOutputStream(
                            this.socket.getOutputStream()
                    );

        } catch (IOException e) {

            throw new ChatException(
                    "Erro ao iniciar TCP Sender.",
                    e
            );
        }
    }

    @Override
    public void send(String message)
            throws ChatException {

        try {

            this.outputFlow.writeUTF(message);

        } catch (IOException e) {

            throw new ChatException(
                    "Erro ao enviar mensagem TCP.",
                    e
            );
        }
    }
}