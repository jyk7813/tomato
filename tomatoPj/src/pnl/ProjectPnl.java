package pnl;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.IconData;

public class ProjectPnl extends JPanel {
	private IconData iconData = new IconData();


    public ProjectPnl() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Title"); // 테스트를 위해 "Title" 추가
        titleLabel.setOpaque(true); // 레이블의 배경을 불투명하게 설정
        titleLabel.setBackground(Color.RED); // 레이블의 배경색을 변경

        JLabel projectLabel = new JLabel(iconData.getImageIcon("project_bar"));
        JLabel blankLabel = new JLabel();
        blankLabel.setOpaque(true);
        blankLabel.setBackground(Color.BLUE);

        add(titleLabel);
        add(projectLabel);
        add(blankLabel);
    }
}


