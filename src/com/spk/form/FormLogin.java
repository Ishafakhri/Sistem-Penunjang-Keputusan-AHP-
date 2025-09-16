package com.spk.form;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.spk.main.Form;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Image;
import net.miginfocom.swing.MigLayout;


public class FormLogin extends Form{
    private JLabel imageLogo;
    private JPanel mainPanel;
    private JPanel panelForm;
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    
    public FormLogin(){
        init();
    }
    
    private void init(){
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        
        mainPanel = new JPanel(new MigLayout("insets 50", "[][]", "[fill][grow]"));
        mainPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;background:@accentColor");
        
        JPanel panelLogo = new JPanel(new MigLayout("wrap", "300", "[]0[]"));
        panelLogo.putClientProperty(FlatClientProperties.STYLE, "background:@accentColor");
        
        imageLogo = new JLabel();
        imageLogo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/com/spk/images/Logo.png"))
                .getImage()
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        
        JLabel lbTitleLogo = new JLabel("SPK AHP Supplier Terbaik");
        lbTitleLogo.putClientProperty(FlatClientProperties.STYLE, "foreground:rgb(255,255,255);font:bold italic +14 Roboto");
       
        JLabel lbDetail = new JLabel("Sistem Pendukung Keputusan");
        lbDetail.putClientProperty(FlatClientProperties.STYLE, "foreground:rgb(255,255,255);font:bold 16");
        
        JLabel lbDetail2 = new JLabel("Supplier Terbaik Menggunakan Metode AHP");
        lbDetail2.putClientProperty(FlatClientProperties.STYLE, "foreground:rgb(255,255,255);font:bold 16");
        
        JLabel lbCreated = new JLabel("Ishafakhri Akbar");
        lbCreated.putClientProperty(FlatClientProperties.STYLE, "foreground:rgb(140,140,140);font:bold 12");
        
        panelLogo.add(imageLogo, "align center, gapy 30, gap 25px 0px");
        panelLogo.add(lbTitleLogo, "align center, gapy 5, gap 25px 0px");
        panelLogo.add(lbDetail, "align center, gapy 5, gap 25px 0px");
        panelLogo.add(lbDetail2, "align center, gapy 5, gap 25px 0px");
        panelLogo.add(lbCreated, "align center, gapy 5, gap 25px 0px");
        
        panelForm = new JPanel(new MigLayout("wrap, insets 20", "fill, 200:250"));
        panelForm.putClientProperty(FlatClientProperties.STYLE, "arc:20;background:rgb(255,255,255)");
        
        JLabel lbTitleForm = new JLabel("Login", JLabel.CENTER);
        lbTitleForm.putClientProperty(FlatClientProperties.STYLE, "foreground:@accentColor;font:bold +10");
        
        JLabel lbDescription = new JLabel("Please sign in to access the system", JLabel.CENTER);
        lbDescription.putClientProperty(FlatClientProperties.STYLE, "foreground:@accentColor;");
        
        JLabel lbUsername = new JLabel("Username");
        lbUsername.putClientProperty(FlatClientProperties.STYLE, "foreground:@accentColor;");
        
        txtUsername = new JTextField();
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("com/spk/icons/username.svg", 20, 20));
        txtUsername.putClientProperty(FlatClientProperties.STYLE, "arc:10;");
        
        JLabel lbPassword = new JLabel("Password");
        lbPassword.putClientProperty(FlatClientProperties.STYLE, "foreground:@accentColor");
        txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("com/spk/icons/password.svg", 20, 20));
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:10;"
                + "showRevealButton:true;"
                + "showCapsLock:true");
        
        
        btnLogin = new JButton("Login");
        btnLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:@accentColor;"      
                + "foreground:rgb(255,255,255);"  
                + "arc:10;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"            
                + "font:bold 16;");               
        
        panelForm.add(lbTitleForm);
        panelForm.add(lbDescription);
        panelForm.add(lbUsername, "gapy 8");
        panelForm.add(txtUsername, "hmin 30");
        panelForm.add(lbPassword, "gapy 8");
        panelForm.add(txtPassword, "hmin 30");
        panelForm.add(btnLogin, "hmin 30, gapy 12");
        
        mainPanel.add(panelForm);
        mainPanel.add(panelLogo);
        
        add(mainPanel);
             
    }
}
