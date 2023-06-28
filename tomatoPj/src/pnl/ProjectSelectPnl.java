package pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.SQLException;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.plaf.basic.BasicScrollBarUI;

import dbutil.SelectProjectInfo;
import frame.MainFrame;
import pnl.projectpnl.ProjectPnl;
import pnl.projectpnl.ProjectSelectWestPnl;
import tomatoPj.ColumnRepository;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import tomatoPj.Member_Tag_Package_Repository;
import tomatoPj.Project;
import utility.IconData;
import utility.Utility;

public class ProjectSelectPnl extends JPanel {

	private Image image;
	private IconData iconData;
	private Utility utility;
	private JLayeredPane centerPnl;
	private JButton jButton;
	private JScrollPane scrollPane;
	private MemberRepository memberRepo;
	private HashSet<Member> memberList;
	private Member_Tag_Package_Repository mtPackageRepo;
	private MainFrame mainFrame;
	private ColumnRepository colRepo;
	private SelectProjectInfo pjInfo;

	public ProjectSelectPnl(Image image, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		memberRepo = new MemberRepository();
		mtPackageRepo = new Member_Tag_Package_Repository();
		memberList = new HashSet<>();
		colRepo = new ColumnRepository();
		this.image = image;
		iconData = new IconData();
		utility = new Utility();
		setLayout(new BorderLayout(0, 0));

		centerPnl = new JLayeredPane();
		centerPnl.setOpaque(false);
		centerPnl.setLayout(null); // Necessary for JScrollPane to function correctly

		ProjectSelectWestPnl westPnl = new ProjectSelectWestPnl() {
			@Override
			public Dimension getPreferredSize() {
				setOpaque(false);
				return new Dimension(510, 905);
			}
		};
		JPanel eastPnl = new JPanel() {

			@Override
			public Dimension getPreferredSize() {
				setOpaque(false);
				return new Dimension(510, 905);
			}

		};

		JPanel northPanel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				setOpaque(false);
				return new Dimension(1920, 135);
			}
		};
		jButton = new JButton(iconData.getImageIcon("blankAdd"));
		jButton.setBounds(0, 55, 900, 216);

		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//addPanel();
				mainFrame.showCard("columnSelect");
			}
		});

		centerPnl.add(jButton, new Integer(3)); // Add jButton to a higher layer
		utility.setButtonProperties(jButton);

		// Create JScrollPane and add the centerPnl to it
		scrollPane = new JScrollPane(centerPnl, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneSetLayout();

		scrollPaneSetUI();

		scrollPane.setComponentZOrder(scrollPane.getVerticalScrollBar(), 0);
		scrollPane.setComponentZOrder(scrollPane.getViewport(), 1);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false); // Add this line
		scrollPane.setBorder(null);

		add(scrollPane, BorderLayout.CENTER); // Add the JScrollPane to the main panel
		add(northPanel, BorderLayout.NORTH);
		add(westPnl, BorderLayout.WEST);
		add(eastPnl, BorderLayout.EAST);

		addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {
				int a = mainFrame.loginMember.getMember_no();
				try {
					mainFrame.loginMember.setPjList(memberRepo.returnMemberPj(a));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for (Project project : mainFrame.loginMember.getPjList()) {
					int key = project.getProject_no();
					try {

						for (Member mem : mtPackageRepo.returnMemberByPj_no(key)) {
							memberList.add(mem);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.out.println("제대로된 멤버? " + memberList);
				//////////// 가지고있는 프로젝트 리스트 패널 생성  ///////////
				for (Project project : mainFrame.loginMember.getPjList()) {
					addPanel(project.getProject_no(), project.getTitle());
				}
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
	}

	private void scrollPaneSetLayout() {
		scrollPane.setLayout(new ScrollPaneLayout() {
			@Override
			public void layoutContainer(Container parent) {
				JScrollPane scrollPane = (JScrollPane) parent;

				Rectangle availR = scrollPane.getBounds();
				availR.x = availR.y = 0;

				Insets insets = parent.getInsets();
				availR.x = insets.left;
				availR.y = insets.top;
				availR.width -= insets.left + insets.right;
				availR.height -= insets.top + insets.bottom;

				Rectangle vsbR = new Rectangle();
				vsbR.width = 12;
				vsbR.height = availR.height;
				vsbR.x = availR.x + availR.width - vsbR.width;
				vsbR.y = availR.y;

				if (viewport != null) {
					viewport.setBounds(availR);
				}
				if (vsb != null) {
					vsb.setVisible(true);
					vsb.setOpaque(false);

					vsb.setBounds(vsbR);
				}
			}
		});

	}

	private void scrollPaneSetUI() {
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			private final Dimension d = new Dimension();

			@Override
			protected JButton createDecreaseButton(int orientation) {
				return new JButton() {
					@Override
					public Dimension getPreferredSize() {
						return d;
					}
				};
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				return new JButton() {
					@Override
					public Dimension getPreferredSize() {
						return d;
					}
				};
			}

			@Override
			protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
			}

			@Override
			protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Color color = null;
				JScrollBar sb = (JScrollBar) c;
				if (!sb.isEnabled() || r.width > r.height) {
					return;
				} else if (isDragging) {
					color = new Color(1, 147, 121, 0);
				} else if (isThumbRollover()) {
					color = new Color(1, 147, 121, 0);
				} else {
					color = new Color(1, 147, 121, 0);
				}
				g2.setPaint(color);
				g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
				g2.setPaint(color);
				g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
				g2.dispose();
			}

			@Override
			protected void setThumbBounds(int x, int y, int width, int height) {
				super.setThumbBounds(x, y, width, height);
				scrollbar.repaint();
			}
		});

	}

	private void addPanel(int project_no, String title) {
		
		ProjectPnl projectPnl = new ProjectPnl(mainFrame, project_no, title);
		projectPnl.setBounds(0, jButton.getY(), 900, 216); // Set the position to current jButton position
		centerPnl.add(projectPnl, new Integer(2)); // Add projectPnl to a lower layer
		jButton.setLocation(jButton.getX(), jButton.getY() + projectPnl.getHeight() + 10); // Move jButton down

		// Update the preferred size of the centerPnl and validate the JScrollPane
		centerPnl.setPreferredSize(new Dimension(centerPnl.getWidth(), jButton.getY() + jButton.getHeight()));
		scrollPane.validate();
		centerPnl.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
