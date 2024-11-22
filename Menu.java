import javax.swing.*;

public class Menu extends JFrame {
    public Menu() {
        setTitle("Menú");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton exampleButton = new JButton("No Funko");
        panel.add(exampleButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menuWindow = new Menu();
            menuWindow.setVisible(true);
        });
    }
}
