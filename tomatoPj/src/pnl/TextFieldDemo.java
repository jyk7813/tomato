package pnl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextFieldDemo {

    private JFrame frame;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel label;

    public TextFieldDemo() {
        frame = new JFrame("TextField Demo");
        frame.setLayout(null);

        textField1 = new JTextField();
        textField1.setBounds(100, 100, 100, 20);
        frame.add(textField1);

        textField2 = new JTextField();
        textField2.setBounds(200, 100, 100, 20);
        frame.add(textField2);

        textField3 = new JTextField();
        textField3.setBounds(300, 100, 100, 20);
        frame.add(textField3);

        label = new JLabel();
        label.setBounds(400, 100, 100, 20);
        frame.add(label);

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField1.getText();
                label.setText(text);

                // 2초 후에 콘솔에 출력
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(text);
                    }
                };

                thread.start();
            }
        });

        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TextFieldDemo();
    }
}