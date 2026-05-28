package chat.app;

import javax.swing.SwingUtilities;


import chat.ui.ChatView;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new ChatView();
        });
    }
}