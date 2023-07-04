package pnl.projectpnl;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import button.DeletePjBtn;
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
    public int project_no;
    private JLabel titleLbl;
    private MainFrame mainFrame;
    private String title;
    public DeletePjBtn deletePjBtn;
    private MouseAdapter mouseAdapter;

    
//	private JButton projectButton;
    
    // 프로젝트선택시 해당정보 보관
    public void insertPjInfo(MainFrame mainFrame, int project_no, String title) {
    	this.mainFrame = mainFrame;
    	this.title = title;
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
    	this.project_no = project_no;
    	fontData = new FontData();
    	util = new Utility();
    	pjBack = iconData.getImageIcon("project_bar");
    	
    	mtPackageRepo = new Member_Tag_Package_Repository();
    	colRepo = new ColumnRepository();
    	taskRepo = new TaskRepository();
    	
    	setLayout(null);
        setOpaque(false);
        
        titleLbl = new JLabel(title, SwingConstants.CENTER);
		titleLbl.setBounds(275, 40, 350, 60);
        titleLbl.setFont(fontData.nanumFontBold(27));
		add(titleLbl);
		
		deletePjBtn = new DeletePjBtn(mainFrame, project_no);
		deletePjBtn.setBounds(30, 30, 30, 30);
		add(deletePjBtn);
		deletePjBtn.setVisible(false);
        
//        JButton projectButton = new JButton(title);
//      
//        projectButton.setIcon(pjBack);
//        projectButton.setText(title);
//        projectButton.setBounds(30, 30, 90, 90);
//        projectButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (!(mainFrame.getSelectedProjectTitle().equals(title))||mainFrame.getSelectedProjectTitle()==null) {
//					insertPjInfo(mainFrame, project_no, title);
//					mainFrame.setSelectedProjectTitle(title);
//					System.out.println(mainFrame.getSelectedProjectTitle());
//				} else {
//					insertPjInfo(mainFrame, project_no, title);
//					mainFrame.showCard("columnSelect");
//				}
//			}
//		});
//        add(projectButton);
//        util.setButtonProperties(projectButton);
        this.image = iconData.getImageIcon("project").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // draws the image onto the JPanel
    }
    public void setimage() {
		if (!isEnabled()) {
			this.image = iconData.getImageIcon("project").getImage();
			deletePjBtn.setVisible(false);
		} else {
			this.image = iconData.getImageIcon("projectselect").getImage();
			
			
		}
		revalidate();
		repaint();
	}
    public void mouseListen() {
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	if (e.getButton()== MouseEvent.BUTTON1) {
            		if (!(mainFrame.getSelectedProjectTitle().equals(title))||mainFrame.getSelectedProjectTitle()==null) {
            			insertPjInfo(mainFrame, project_no, title);
            			mainFrame.setSelectedProjectTitle(title);
            			System.out.println(mainFrame.getSelectedProjectTitle());
            		} else {
            			insertPjInfo(mainFrame, project_no, title);
            			mainFrame.showCard("columnSelect");
            		}
				}
            }
        };
        addMouseListener(mouseAdapter);
    }

    public void removeMouseListener() {
        if (mouseAdapter != null) {
            removeMouseListener(mouseAdapter);
        }
    }
    

}
