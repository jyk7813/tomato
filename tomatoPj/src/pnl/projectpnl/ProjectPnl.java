package pnl.projectpnl;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frame.MainFrame;
import tomatoPj.ColumnRepository;
import tomatoPj.Member;
import tomatoPj.Member_Tag_Package_Repository;
import tomatoPj.TaskRepository;
import utility.IconData;

public class ProjectPnl extends JPanel {
    private IconData iconData = new IconData();
    private Image image;
    private JTextField textField;
    private Member_Tag_Package_Repository mtPackageRepo;
    private ColumnRepository colRepo;
    private TaskRepository taskRepo;
    
    // 프로젝트선택시 해당정보 보관
    public void insertPjInfo(MainFrame mainFrame, int project_no, String title) {
    	mainFrame.showCard("columSelect");
		mainFrame.pjInfo.setProject_no(project_no);
		mainFrame.pjInfo.setTitle(title);
		try {
			for (Member mem : mtPackageRepo.returnMemberByPj_no(project_no)) {
				mainFrame.pjInfo.getMemberList().add(mem);
			}
			mainFrame.pjInfo.setCol(colRepo.selectByColNo(project_no));
			mainFrame.pjInfo.setTask(taskRepo.taskListBypjNo(project_no));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }
    
    public ProjectPnl(MainFrame mainFrame, int project_no, String title) {
    	mtPackageRepo = new Member_Tag_Package_Repository();
    	colRepo = new ColumnRepository();
    	taskRepo = new TaskRepository();
    	
        this.image = iconData.getImageIcon("projectIcon").getImage(); // Set imagePath to your image path
        setOpaque(false);
        setLayout(new BorderLayout(0, 0));
        
        JButton btnNewButton = new JButton(title);
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertPjInfo(mainFrame, project_no, title);
				mainFrame.showCard("columnSelect");
			}
		});
        add(btnNewButton, BorderLayout.CENTER);
        
        
        
        

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // draws the image onto the JPanel
    }
}
