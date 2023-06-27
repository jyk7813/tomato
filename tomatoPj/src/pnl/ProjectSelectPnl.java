
package pnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import frame.MainFrame;

public class ProjectSelectPnl extends JPanel{
    private Image image;

    public ProjectSelectPnl(Image image, MainFrame mainFrame) {
        this.image = image;
        setLayout(null); // null layout 사용
        JButton jButton = new JButton("Click me"); // 버튼에 텍스트 추가

        jButton.setBounds(0, 0, 100, 100);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectPnl projectPnl = new ProjectPnl();
                projectPnl.setBounds(150, 150, 500, 500); // ProjectPnl의 위치와 크기 설정
                add(projectPnl);
                revalidate(); // 컴포넌트의 레이아웃을 다시 설정
                repaint(); // 컴포넌트를 다시 그림
            }
        });

        add(jButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}