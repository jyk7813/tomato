package pnl;
import javax.swing.*;

import button.AddMemberBtn;
import frame.MainFrame;
import tomatoPj.Member_task;
import utility.FontData;
import utility.IconData;
import utility.Utility;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.List;

public class TaskMemberAdd extends JDialog {
    private JTextField idInsertTextField;
    private JLabel notFound;

    public TaskMemberAdd(MainFrame mainFrame) {
        Taskrefrom tr = mainFrame.TBP.taskrefrom;
        IconData IC = new IconData();
        FontData FD = new FontData();

        notFound = new JLabel("해당 아이디는 존재하지 않습니다");
        notFound.setForeground(new Color(235, 105, 97));
        notFound.setSize(300,40);
        notFound.setLocation(30, 10);
        notFound.setVisible(false);

        notFound.setFont(FD.nanumFontBold(18));
        idInsertTextField = new JTextField();
        idInsertTextField.addKeyListener(new KeyAdapter() {
        	public void keyReleased(KeyEvent e) {
        		notFound.setVisible(false);
        		repaint();
        	}
        });
        Utility utility = new Utility();
        JButton addMemberBtn = new AddMemberBtn(mainFrame);
        ImageIcon imageIcon = IC.getImageIcon("addmember(BG)");
        JPanel panelWithBackground = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image on the panel
                g.drawImage(imageIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        // Optional: set the layout manager of the panel
        panelWithBackground.setLayout(null);

        // Add any other components to the panelWithBackground
        panelWithBackground.setOpaque(false);
        // Add the panel to the JDialog
        this.add(panelWithBackground);

        idInsertTextField = new JTextField();

        idInsertTextField.setBounds(34, 57, 276, 50);
        idInsertTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                repaint();
            }
        });
        utility.setButtonProperties(idInsertTextField);
        panelWithBackground.add(idInsertTextField);
        panelWithBackground.add(notFound);
        addMemberBtn = new AddMemberBtn(mainFrame);
        addMemberBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String input = idInsertTextField.getText();
                JLabel memberIcon = new JLabel();
                JLabel hidenText = new JLabel();
                hidenText.setPreferredSize(new Dimension(60,60));
    			hidenText.setForeground(new Color(0,0,0,0));
    			hidenText.add(memberIcon);
    			
                List<String> ids = new ArrayList<>();
                for (int i = 0; i < tr.ProjectMember.size(); i++) {
                    ids.add(tr.ProjectMember.get(i).getId());
                    System.out.println("선택된 프로젝트에 누구누구있어?");
                  System.out.println(tr.ProjectMember.get(i).getId());
                }
                if (ids.contains(input)) {
                	
                    notFound.setVisible(false);  // 입력된 아이디가 존재하면 notFound 라벨을 숨깁니다.

                    for (int i = 0; i < ids.size(); i++) {
                        if (input.equals(ids.get(i))) {
                            if (tr.ProjectMember.get(i).getImage() != null) {
                                ImageIcon icon = new ImageIcon(tr.ProjectMember.get(i).getImage());
                                memberIcon.setIcon(icon);
                                hidenText.setText(""+tr.ProjectMember.get(i).getMember_no());
                            } else {
                                memberIcon.setIcon(IC.getImageIcon("user1"));
                                hidenText.setText(""+tr.ProjectMember.get(i).getMember_no());
                            }
                        }
                    }

                    memberIcon.addMouseListener(new MouseAdapter() {
                    	public void mousePressed(MouseEvent e) {

                            // showInputDialog 메서드를 사용하여 입력 다이얼로그를 표시하고 이미지 아이콘을 설정합니다.
                            tr.MemberPnl.remove(memberIcon);
                            tr.MemberPnl.revalidate();
                            tr.MemberPnl.repaint();
                            tr.Count--;
                            tr.plus.setVisible(true);
                            int MemberNum = Integer.valueOf(hidenText.getText());
                            for(int i =0; i<tr.member_task_List.size();i++) {
                           	 if(tr.member_task_List.get(i).getMember_no() ==MemberNum) {
                           		tr.member_task_List.remove(i);
                           	 }
                            }
                            
                        }
                    });
                    int memberNum = Integer.valueOf(hidenText.getText());
                    Member_task comp =  new Member_task(0,memberNum,0,"");
                    memberIcon.setSize(new Dimension(60,60));
        
                	   
                   if(tr.member_task_List.contains(comp)) { 
                	   
                	   notFound.setText("이미 추가되있는 유저입니다");
                	   notFound.setVisible(true);
                   }else {
                	   tr.MemberPnl.add(memberIcon);
                	   if(tr.TakeTask != null) {
                	   tr.member_task_List.add(new Member_task(0,memberNum,tr.TakeTask.getTask_no(),""));
                	   }else {
                		   tr.member_task_List.add(new Member_task(0,memberNum,0,""));
                	   }
                   }
                   
             
                    	
            
                    

                    // 아이콘들 간의 간격을 위한 빈 공간 컴포넌트 추가

                    tr.MemberPnl.revalidate();
                    tr.MemberPnl.repaint();
                    tr.Count++;
                } else {
                	notFound.setText("해당 아이디는 존재하지 않습니다");
                    notFound.setVisible(true);  // 입력된 아이디가 존재하지 않으면 notFound 라벨을 보여줍니다.
                }

                if (tr.Count >= 4) {
                    tr.plus.setVisible(false);
                } else {
                }
                tr.MemberPnl.revalidate();
                tr.MemberPnl.repaint();
//                dispose();
            }
        });
        addMemberBtn.setBounds(110, 119, 126, 41);

        idInsertTextField.setFont(FD.nanumFontBold(12));
        panelWithBackground.add(addMemberBtn);
        setSize(346, 174);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);

        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // 팝업 창이 포커스를 얻었을 때의 동작을 정의합니다.
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                // 팝업 창이 포커스를 잃었을 때의 동작을 정의합니다.
                // 여기서는 팝업 창을 닫습니다.

                dispose();
            }
        });
    }
}
