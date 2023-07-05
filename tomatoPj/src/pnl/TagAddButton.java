package pnl;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import button.AddMemberBtn;
import frame.MainFrame;
import tomatoPj.Function_Tag;
import tomatoPj.Task;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class TagAddButton extends JDialog {
	JTextField idInsertTextField;
	JButton addMemberBtn;
	MainFrame mainFrame;
	Utility utility;
	int CountTag;
	private IconData iconData = new IconData();
	Taskrefrom ts;
	JLabel plus;
	JPanel tagpnl;
	Task task;
	private JLabel notFound;
	public TagAddButton(MainFrame mainFrame,JLabel plus,JPanel tagpnl,Taskrefrom ts,Task task) {
		this.ts = ts;
		this.tagpnl = tagpnl;
		this.task = task;
		IconData IC = new IconData();
		utility = new Utility();
		this.plus = plus;
		ImageIcon imageIcon = iconData.getImageIcon("addmember(BG)");
		notFound = new JLabel("이미 존재하는 태그입니다.");
        notFound.setForeground(new Color(235, 105, 97));
        notFound.setSize(300,40);
        notFound.setLocation(30, 10);
        notFound.setVisible(false);
    
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
		panelWithBackground.add(notFound);
		idInsertTextField = new JTextField();
		
		idInsertTextField.setBounds(34, 57, 276, 50);
		idInsertTextField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				repaint();
			}
		});
		utility.setButtonProperties(idInsertTextField);
		panelWithBackground.add(idInsertTextField);
		addMemberBtn = new AddMemberBtn(mainFrame);
		addMemberBtn.setBounds(110, 119, 126, 41);
		FontData FD = new FontData();
		idInsertTextField.setFont(FD.nanumFontBold(12));
//		idInsertTextField.setBackground(new Color(255,0,0));
		
		((AbstractDocument) idInsertTextField.getDocument()).setDocumentFilter(new DocumentFilter() {


		    @Override
		    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		        int currentLength = fb.getDocument().getLength();
		        int replaceLength = text.length();
		        int newLength = currentLength - length + replaceLength;
		        if (newLength <= 6) {
		            super.replace(fb, offset, length, text, attrs);
		        }
		    }
		});
		
		
		
		JLabel tag = new JLabel(IC.getImageIcon("tag"));
		JLabel tagText = new JLabel();		
		tagText.setFont(FD.nanumFont(12));
		tag.setLayout(null);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		
		
	
		tagText.setSize(80,30);
		tagText.setLocation(10,0);
		addMemberBtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {

			    ts.tagTexts.add(idInsertTextField.getText());
			    tagText.setText(idInsertTextField.getText());
			    List<Function_Tag> list2 = ts.return_Function_Tag_List;
			    tag.add(tagText,constraints);
			    tagText.setVisible(true);
			    tag.revalidate();
			    tag.repaint();
			    
			    tagText.revalidate();
			    tagText.repaint();
			    ts.CountTag ++;
			    
			    tag.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
						ts.CountTag--;
						tagpnl.remove(tag);
	        	        // 아이콘들 왼쪽 정렬
	        	        for(int i = 0;i< ts.tagTexts.size();i++) {
	        	        	if(ts.tagTexts.get(i).equals(tagText.getText()))
	        	        	ts.tagTexts.remove(i);
	        	        }
	        	        for(int i = 0; i<list2.size(); i++) {
		        	    	   if(list2.get(i).getText().equals(tagText.getText())) {
		        	    		   list2.remove(i);
		        	    	   }
		        	       }
	        	        plus.setVisible(true);
	        	        tagpnl.revalidate();
	        	        tagpnl.repaint();
	        	        
				}
			    });		
			    Function_Tag tag2 = new Function_Tag(0,0,"",idInsertTextField.getText());
			    if(!ts.return_Function_Tag_List.contains(tag2))
			    		{
			    	tagpnl.add(tag);
			    	if(task!=null) {
			    		ts.return_Function_Tag_List.add(new Function_Tag(task.getTask_no(), "", idInsertTextField.getText()));
			    	}else if(task==null) {
			    		ts.return_Function_Tag_List.add(new Function_Tag(0, "", idInsertTextField.getText()));
			    	}
			    	notFound.setVisible(false);			    	
			    }else {			    	
			    	notFound.setVisible(true);
			    				    
			    }

			    	

			    tagpnl.revalidate();
    	        tagpnl.repaint();
//			    dispose();
			}
		});
		panelWithBackground.add(addMemberBtn);
		setSize(346, 174);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setVisible(true);

		if(ts.CountTag>3) {
			plus.setVisible(false);
		    tagpnl.revalidate();
	        tagpnl.repaint();
		}
		// WindowFocusListener를 추가합니다.
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