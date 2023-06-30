package pnl.commonpnl;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import frame.MainFrame;
import utility.Utility;

public class TopMainPnl extends JPanel {
	private TopCanbanSelectedPnl topCanbanSelectedPnl;
	private TopTodoSelectedPnl topTodoSelectedPnl;
    private CardLayout cardLayout; // CardLayout 객체를 생성

	public TopMainPnl(MainFrame mainFrame) {
		setOpaque(false);
        cardLayout = new CardLayout(); // CardLayout 객체를 초기화
		setLayout(cardLayout); // setLayout에 cardLayout 객체를 넣어줌
		
		topCanbanSelectedPnl = new TopCanbanSelectedPnl(mainFrame,this);
		topTodoSelectedPnl = new TopTodoSelectedPnl(mainFrame,this);
		
		add(topCanbanSelectedPnl,"SelectedCanban");
		add(topTodoSelectedPnl,"SelectedTodo");	
		
	}

	public void showCard(String cardName) {
		cardLayout.show(this, cardName); // CardLayout 객체를 사용하여 카드를 전환		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920,135);
	}
}
