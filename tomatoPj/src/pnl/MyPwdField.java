package pnl;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class MyPwdField extends JPasswordField implements FocusListener {
	   private String defaultText;
	   private boolean isDefaultTextDisplayed;

	   public MyPwdField(String defaultText, JButton btn) {
	      super();
	      this.defaultText = defaultText;
	      this.isDefaultTextDisplayed = true;

	      setForeground(Color.GRAY);
	      setText(defaultText);

	      addFocusListener(this);
	      addKeyListener(new KeyAdapter() {
	         @Override
	         public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	               btn.doClick();
	            }
	         }
	      });
	   }

	   @Override
	   public void focusGained(FocusEvent e) {
	      if (isDefaultTextDisplayed) {
	         setText("");
	         setForeground(Color.BLACK);
	         isDefaultTextDisplayed = false;
	      }
	   }

	   @Override
	   public void focusLost(FocusEvent e) {
	      if (getText().isEmpty()) {
	         setText(defaultText);
	         setForeground(Color.GRAY);
	         isDefaultTextDisplayed = true;
	      }
	   }
	}