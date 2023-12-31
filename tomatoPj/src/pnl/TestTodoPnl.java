package pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;
import pnl.commonpnl.TopMainPnl;
import tomatoPj.Column;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import tomatoPj.Member_Tag_Package_Repository;
import tomatoPj.Project;
import tomatoPj.ProjectRepository;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import utility.CalendarData;
import utility.FontData;
import utility.IconData;
import utility.PrintPlanner;
import utility.PrintPlannerList;
import utility.Utility;

public class TestTodoPnl extends JPanel {
   private final static IconData IC = new IconData();
   private final static FontData FT = new FontData();
   private final static Utility UT = new Utility();
   private final static CalendarData CD = new CalendarData();

   private Image image;
   private PrintPlanner pp;
   private Project selectPj;
   private boolean toggleSwitch = true;
   
   CalendarSwing printCal;

//   private TaskRepository tr = new TaskRepository();
//   private ProjectRepository pr = new ProjectRepository();
//   private MemberRepository mr = new MemberRepository();
   public List<PrintPlanner> ppList;
//   static List<Project> pjOfUser;
   PrintPlannerList ppr = new PrintPlannerList();
   int loginMemberNo = 0;
   
   public TestTodoPnl() {

   }

   public TestTodoPnl(MainFrame mainFrame) {
      addComponentListener(new ComponentListener() {
         @Override
         public void componentShown(ComponentEvent e) {
            ppList = ppr.getPrintPlannerList(mainFrame.loginMember.getMember_no());
         }

         @Override
         public void componentResized(ComponentEvent e) {
         }

         @Override
         public void componentMoved(ComponentEvent e) {
         }

         @Override
         public void componentHidden(ComponentEvent e) {
         }
      });

      // 메인 영역 배경 패널 ------------------------------------
      JPanel calBgPnl = new JPanel() {
         public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(IC.getImageIcon("calendarWeekBg").getImage(), 0, 0, null);
            setOpaque(false);
         }
      };

      // 배경 패널 -----------------------------------------
      JPanel bgPnl = new JPanel() {
         public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(IC.getImageIcon("Background").getImage(), 0, 0, null);
            setOpaque(false);
         }
      };

      // 상단 패널 -----------------------------------------
      TopMainPnl topPnl = new TopMainPnl(mainFrame);
      topPnl.showCard("SelectedTodo");
      topPnl.setBounds(0, 0, 1920, 135);
      topPnl.setOpaque(false);

      // 달력 패널 -----------------------------------------
      JPanel calPnl = new JPanel();
      calPnl.setBounds(164, 300, 857, 870);
      calPnl.setLayout(null);
      calPnl.setOpaque(false);

      // 달력 출력 패널 --------------------------------------
      loginMemberNo = mainFrame.loginMember.getMember_no();
      if(loginMemberNo != 0) {
         printCal = new CalendarSwing(loginMemberNo, toggleSwitch, mainFrame);
         printCal.setBounds(200, 135, 1718, 870);
         printCal.setLayout(null);
         printCal.setOpaque(false);
         add(printCal); // 사용자값 받아 달력 출력 패널
      } else {
         printCal = new CalendarSwing();
         printCal.setBounds(200, 135, 1718, 870);
         printCal.setLayout(null);
         printCal.setOpaque(false);
         add(printCal); // 달력 기본 생성자
      }

      // 막대바 출력 패널 -------------------------------------

      // 투두 리스트 패널 ------------------------------------
      JPanel todoListPnl = new JPanel();
      todoListPnl.setBounds(1026, 175, 857, 870);
      todoListPnl.setLayout(null);
      todoListPnl.setOpaque(false);

      // 투두리스트 출력 패널 ----------------------------------

      // 뷰 설정 토글 버튼
      JButton toggleBtn = UT.getBtn(350, 0, "prijectAll_toggle");
      todoListPnl.add(toggleBtn);
      ActionListener listener = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (toggleSwitch) {
               toggleBtn.setIcon(IC.getImageIcon("prijectEach_toggle"));
               toggleSwitch = false;
               printCal = new CalendarSwing(loginMemberNo, toggleSwitch, mainFrame);
            } else {
               toggleBtn.setIcon(IC.getImageIcon("prijectAll_toggle"));
               toggleSwitch = true;
               printCal = new CalendarSwing(loginMemberNo, toggleSwitch, mainFrame);
            }
         }
      };
      toggleBtn.addActionListener(listener);

      // 선택 버튼
      JButton selBtn = UT.getBtn(785, 15, "projectOpne");
      todoListPnl.add(selBtn);

      calBgPnl.setBounds(164, 160, 1718, 870);
      calBgPnl.setLayout(null);
      calBgPnl.setOpaque(false);

      // 배경 패널에 각 패널 붙이기 ------------------------------
      bgPnl.add(topPnl); // 상단 패널
      bgPnl.add(todoListPnl); // 투두 리스트 패널
      bgPnl.add(calPnl); // 달력 패널
//      add(printCal); // 달력 패널
      bgPnl.add(calBgPnl); // 메인 영역 배경 패널
      // -----------------------------------------------
      bgPnl.setBounds(0, 0, 1920, 1080);
      bgPnl.setLayout(null);
      bgPnl.setOpaque(false);
      setLayout(null);
      setOpaque(false);
      add(bgPnl);
      setVisible(true);
   }
}