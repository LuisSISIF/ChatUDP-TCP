package chat.api;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiver implements Receiver {

    private ServerSocket serverSocket;

    private MessageContainer container;

    public TCPReceiver(
            int localPort,
            MessageContainer container
    ) throws ChatException {

        this.container = container;

        try {

            serverSocket =
                    new ServerSocket(localPort);

            new Thread(this).start();

        } catch (Exception e) {

            throw new ChatException(
                    "Erro ao iniciar TCP Receiver",
                    e
            );
        }
    }

    @Override
    public void run() {

        while (true) {

            try {

                Socket socket =
                        serverSocket.accept();

                Thread thread =
                        new Thread(() -> {

                    try {

                        DataInputStream inputFlow =
                                new DataInputStream(
                                        socket.getInputStream()
                                );

                        while (true) {

                            String message =
                                    inputFlow.readUTF();

                            container.newMessage(
                                    "Remoto: " + message
                            );
                        }

                    } catch (Exception e) {

                        container.newMessage(
                                "Cliente desconectado."
                        );
                    }

                });

                thread.start();

            } catch (Exception e) {

                container.newMessage(
                        "Erro no servidor TCP."
                );
            }
        }
    }
}