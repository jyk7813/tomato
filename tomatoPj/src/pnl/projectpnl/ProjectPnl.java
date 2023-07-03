package pnl.projectpnl;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dbutil.SelectProjectInfo;
import frame.MainFrame;
import pnl.commonpnl.ProjectTitlePnl;
import tomatoPj.ColumnRepository;
import tomatoPj.Member;
import tomatoPj.Member_Tag_Package_Repository;
import tomatoPj.TaskRepository;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class ProjectPnl extends JPanel {
    private IconData iconData = new IconData();
    private Image image;
    private JTextField textField;
    private Member_Tag_Package_Repository mtPackageRepo;
    private ColumnRepository colRepo;
    private TaskRepository taskRepo;
    private ImageIcon pjBack;
    private Utility util;
    private FontData fontData;
    
    // 프로젝트선택시 해당정보 보관
    public void insertPjInfo(MainFrame mainFrame, int project_no, String title) {
    	mainFrame.pjInfo = new SelectProjectInfo(project_no, title, null, null);
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
		mainFrame.columnActive = true;
    }
    
    public String getTitle(MainFrame mainFrame, int project_no, String title) {
    	mainFrame.pjInfo = new SelectProjectInfo(project_no, title, null, null);
    	return mainFrame.pjInfo.getTitle();
	}
    
    public ProjectPnl(MainFrame mainFrame, int project_no, String title) {
    	fontData = new FontData();
    	util = new Utility();
    	pjBack = iconData.getImageIcon("project_bar");
    	
    	mtPackageRepo = new Member_Tag_Package_Repository();
    	colRepo = new ColumnRepository();
    	taskRepo = new TaskRepository();
    	
        this.image = iconData.getImageIcon("projectIcon").getImage(); // Set imagePath to your image path
        setOpaque(false);
        setLayout(new BorderLayout(0, 0));
        
        JButton btnNewButton = new JButton(title);
      
        btnNewButton.setIcon(pjBack);
        btnNewButton.setText(title);
        btnNewButton.setHorizontalTextPosition(JButton.CENTER);
        btnNewButton.setFont(fontData.nanumFont(27));
        btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(mainFrame.getSelectedProjectTitle().equals(title))||mainFrame.getSelectedProjectTitle()==null) {
					insertPjInfo(mainFrame, project_no, title);
					mainFrame.setSelectedProjectTitle(title);
					System.out.println(mainFrame.getSelectedProjectTitle());
				} else {
					insertPjInfo(mainFrame, project_no, title);
					mainFrame.showCard("columnSelect");
				}
			}
		});
        add(btnNewButton, BorderLayout.CENTER);
        util.setButtonProperties(btnNewButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // draws the image onto the JPanel
    }
}
