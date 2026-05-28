package chat.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.api.ChatException;
import chat.api.ChatFactory;
import chat.api.MessageContainer;
import chat.api.Sender;

public class ChatView extends JFrame implements MessageContainer {

    private JTextField txtLocalPort;

    private JTextField txtRemoteIP;

    private JTextField txtRemotePort;

    private JTextArea txtMessages;

    private JTextField txtMessage;

    private JButton btnSend;

    private JComboBox<String> cmbProtocol;

    private JLabel lblStatus;

    private Sender sender;

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm");

    public ChatView() {

        setTitle("Universal Chat • TCP / UDP");

        setSize(1100, 700);

        setResizable(false);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(
                new Color(17, 24, 39)
        );

        buildInterface();

        setVisible(true);
    }

    @Override
    public void newMessage(String message) {

        String time =
                LocalTime.now()
                         .format(formatter);

        txtMessages.append(
                "[" + time + "] " +
                message + "\n"
        );

        txtMessages.setCaretPosition(
                txtMessages.getDocument().getLength()
        );
    }

    private void buildInterface() {

        // ==================================================
        // PAINEL SUPERIOR
        // ==================================================

        JPanel topPanel = new JPanel();

        topPanel.setLayout(
                new GridLayout(2, 4, 15, 8)
        );

        topPanel.setBackground(
                new Color(31, 41, 55)
        );

        topPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        // ==================================================
        // LABELS
        // ==================================================

        JLabel lblProtocol =
                new JLabel("Protocolo");

        styleLabel(lblProtocol);

        JLabel lblLocalPort =
                new JLabel("Porta Local");

        styleLabel(lblLocalPort);

        JLabel lblRemoteIP =
                new JLabel("IP Remoto");

        styleLabel(lblRemoteIP);

        JLabel lblRemotePort =
                new JLabel("Porta Remota");

        styleLabel(lblRemotePort);

        // ==================================================
        // COMPONENTES
        // ==================================================

        cmbProtocol =
                new JComboBox<>(
                        new String[] {
                                "TCP",
                                "UDP"
                        }
                );

        cmbProtocol.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        cmbProtocol.setBackground(
                new Color(55, 65, 81)
        );

        cmbProtocol.setForeground(Color.WHITE);

        cmbProtocol.setFocusable(false);

        txtLocalPort = new JTextField();

        txtRemoteIP = new JTextField();

        txtRemotePort = new JTextField();

        styleTextField(txtLocalPort);

        styleTextField(txtRemoteIP);

        styleTextField(txtRemotePort);

        // ==================================================
        // ADICIONA COMPONENTES
        // ==================================================

        topPanel.add(lblProtocol);
        topPanel.add(lblLocalPort);
        topPanel.add(lblRemoteIP);
        topPanel.add(lblRemotePort);

        topPanel.add(cmbProtocol);
        topPanel.add(txtLocalPort);
        topPanel.add(txtRemoteIP);
        topPanel.add(txtRemotePort);

        // ==================================================
        // STATUS
        // ==================================================

        lblStatus =
                new JLabel("Desconectado");

        lblStatus.setForeground(
                Color.LIGHT_GRAY
        );

        lblStatus.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        JPanel statusPanel =
                new JPanel(new BorderLayout());

        statusPanel.setBackground(
                new Color(17, 24, 39)
        );

        statusPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        15,
                        10,
                        15
                )
        );

        statusPanel.add(
                lblStatus,
                BorderLayout.WEST
        );

        // ==================================================
        // HEADER PANEL
        // ==================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BorderLayout()
        );

        headerPanel.setBackground(
                new Color(17, 24, 39)
        );

        headerPanel.add(
                topPanel,
                BorderLayout.CENTER
        );

        headerPanel.add(
                statusPanel,
                BorderLayout.SOUTH
        );

        add(headerPanel, BorderLayout.NORTH);

        // ==================================================
        // ÁREA DE MENSAGENS
        // ==================================================

        txtMessages = new JTextArea();

        txtMessages.setEditable(false);

        txtMessages.setLineWrap(true);

        txtMessages.setWrapStyleWord(true);

        txtMessages.setMargin(
                new Insets(15, 15, 15, 15)
        );

        txtMessages.setBackground(
                new Color(17, 24, 39)
        );

        txtMessages.setForeground(Color.WHITE);

        txtMessages.setCaretColor(Color.WHITE);

        txtMessages.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        JScrollPane scrollPane =
                new JScrollPane(txtMessages);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        add(scrollPane, BorderLayout.CENTER);

        // ==================================================
        // PAINEL INFERIOR
        // ==================================================

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setLayout(
                new BorderLayout(10, 0)
        );

        bottomPanel.setBackground(
                new Color(31, 41, 55)
        );

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        txtMessage = new JTextField();

        styleTextField(txtMessage);

        txtMessage.setToolTipText(
                "Digite sua mensagem..."
        );

        txtMessage.addActionListener(e -> {

            sendMessage();
        });

        btnSend =
                new JButton("Enviar");

        btnSend.setBackground(
                new Color(37, 99, 235)
        );

        btnSend.setForeground(
                Color.WHITE
        );

        btnSend.setFocusPainted(false);

        btnSend.setBorderPainted(false);

        btnSend.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        btnSend.addActionListener(e -> {

            sendMessage();
        });

        bottomPanel.add(
                txtMessage,
                BorderLayout.CENTER
        );

        bottomPanel.add(
                btnSend,
                BorderLayout.EAST
        );

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void sendMessage() {

        String message =
                txtMessage.getText().trim();

        if (message.isEmpty()) {

            return;
        }

        try {

            if (sender == null) {

                int localPort =
                        Integer.parseInt(
                                txtLocalPort.getText()
                        );

                String remoteIP =
                        txtRemoteIP.getText();

                int remotePort =
                        Integer.parseInt(
                                txtRemotePort.getText()
                        );

                boolean isTCP =
                        cmbProtocol
                            .getSelectedItem()
                            .equals("TCP");

                sender = ChatFactory.build(
                        isTCP,
                        remoteIP,
                        remotePort,
                        localPort,
                        this
                );

                lblStatus.setText(
                        "Conectado via "
                        + cmbProtocol.getSelectedItem()
                );
            }

            sender.send(message);

            newMessage(
                    "Você: " + message
            );

            txtMessage.setText("");

            txtMessage.requestFocus();

        } catch (NumberFormatException e) {

            newMessage(
                    "Portas inválidas."
            );

        } catch (ChatException e) {

            newMessage(
                    "Erro: " + e.getMessage()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void styleTextField(
            JTextField field
    ) {

        field.setBackground(
                new Color(55, 65, 81)
        );

        field.setForeground(
                Color.WHITE
        );

        field.setCaretColor(
                Color.WHITE
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        field.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        12,
                        10,
                        12
                )
        );
    }

    private void styleLabel(
            JLabel label
    ) {

        label.setForeground(
                Color.WHITE
        );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );
    }
}