package popup;

import javax.swing.*;

import button.AddMemberBtn;
import frame.MainFrame;

import java.awt.Color;
import java.awt.event.*;

public class MemberAddPopup extends JDialog{
    JTextField idInsertTextField;
    JButton addMemberBtn;
    MainFrame mainFrame;
    

    public MemberAddPopup() {
    	setLayout(null);
        idInsertTextField = new JTextField();
        idInsertTextField.setBounds(34, 57, 276, 41);
        add(idInsertTextField);
        addMemberBtn = new AddMemberBtn(mainFrame);
        addMemberBtn.setBounds(110, 119, 126, 41);
        add(addMemberBtn);
        setSize(346,174);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
