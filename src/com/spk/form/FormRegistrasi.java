package com.spk.form;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.spk.dao.UserDAO;
import com.spk.main.Form;
import com.spk.main.FormManager;
import com.spk.model.User;
import com.spk.service.ServiceUser;
import static com.spk.util.AlertUtils.getOptionAlert;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import raven.modal.Toast;


public class FormRegistrasi extends Form{
    private JLabel imageLogo;
    private JPanel mainPanel;
    private JPanel panelForm;
    
    private JTextField txtUsername;
    private JTextField txtNama;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JComboBox cbxRole;
    private JButton btnRegistrasi;
    
    private final ServiceUser servis = new UserDAO();
   
    
    public FormRegistrasi(){
        init();
    }
    
    private void init(){
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        
        mainPanel = new JPanel(new MigLayout("insets 50", "[][]", "[fill][grow]"));
        mainPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;background:@accentColor");
        
        JPanel panelFormLogo = new JPanel(new MigLayout("fill", "center", "center"));
        panelFormLogo.putClientProperty(FlatClientProperties.STYLE, "background:@accentColor");

        JPanel panelLogo = new JPanel(new MigLayout("wrap", "300", "[]0[]"));
        panelLogo.setOpaque(false);
        
        
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
        
        panelLogo.add(imageLogo, "align center");
        panelLogo.add(lbTitleLogo, "align center");
        panelLogo.add(lbDetail, "align center, gapy 5");
        panelLogo.add(lbDetail2, "align center, gapy 5");
        panelLogo.add(lbCreated, "align center, gapy 5");
        panelFormLogo.add(panelLogo);
        
        panelForm = new JPanel(new MigLayout("wrap, insets 20", "fill, 350:250"));
        panelForm.putClientProperty(FlatClientProperties.STYLE, "arc:20;background:rgb(255,255,255)");
        
        JLabel lbTitleForm = new JLabel("Registrasi", JLabel.CENTER);
        lbTitleForm.putClientProperty(FlatClientProperties.STYLE, "foreground:@accentColor;font:bold +10");
        
        JLabel lbDescription = new JLabel("Please sign in to access the system", JLabel.CENTER);
        lbDescription.putClientProperty(FlatClientProperties.STYLE, "foreground:@accentColor;");
        
        txtNama = new JTextField();
        txtNama.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama");
        txtNama.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        
        txtEmail = new JTextField();
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        txtEmail.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        
        
        txtUsername = new JTextField();
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        
        
        
        txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        
        
        cbxRole = new JComboBox();
        initComboItem(cbxRole);
        setColorCbx();
        
        btnRegistrasi = new JButton("Registrasi");
        btnRegistrasi.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:@accentColor;"      
                + "foreground:rgb(255,255,255);"  
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"            
                + "font:bold 16;");               
        
        panelForm.add(lbTitleForm);
        panelForm.add(lbDescription);
        panelForm.add(new JLabel("Nama"), "gapy 8");
        panelForm.add(txtNama, "hmin 30");
        panelForm.add(new JLabel("Email"), "gapy 8");
        panelForm.add(txtEmail, "hmin 30");
        panelForm.add(new JLabel("Username"), "gapy 8");
        panelForm.add(txtUsername, "hmin 30");
        panelForm.add(new JLabel("Password"), "gapy 8");
        panelForm.add(txtPassword, "hmin 30");
        panelForm.add(new JLabel("Role"), "gapy 8");
        panelForm.add(cbxRole, "hmin 30");
        panelForm.add(btnRegistrasi, "hmin 30, gapy 12");
        
        mainPanel.add(panelFormLogo);
        mainPanel.add(panelForm);
        
        
        add(mainPanel);
        
        btnRegistrasi.addActionListener((e) -> {
           insertData();
        });
    }

    private void initComboItem(JComboBox cbxRole) {
        cbxRole.addItem("Pilih Role Anda");
        cbxRole.addItem("Admin");
        cbxRole.addItem("User");
    }
    
    private void setColorBasedOnSelection(){
        if(cbxRole.getSelectedItem().equals("Pilih Role Anda")){
            cbxRole.putClientProperty(FlatClientProperties.STYLE,"foreground:$Label.disabledForeground");
        }else{
            cbxRole.putClientProperty(FlatClientProperties.STYLE,"foreground:$Textfield.Foreground");
        }
    }
    
    private void setColorCbx(){
        cbxRole.addItemListener((e) -> {
             if(e.getStateChange() == ItemEvent.SELECTED){
                 setColorBasedOnSelection();
             }
        });
        
        cbxRole.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                setColorBasedOnSelection();
            }
        });
        setColorBasedOnSelection();
    }
    
    private boolean validateInput(){
        boolean valid = false;
        String username = txtUsername.getText().trim();
        
        if(txtNama.getText().trim().isEmpty()){
            Toast.show(this, Toast.Type.INFO, "Masukkan nama Anda", getOptionAlert());
        }else if(txtEmail.getText().trim().isEmpty()){
            Toast.show(this, Toast.Type.INFO, "Masukkan email Anda", getOptionAlert());
        }else if(txtUsername.getText().trim().isEmpty()){
            Toast.show(this, Toast.Type.INFO, "Masukkan username Anda", getOptionAlert());
        }else if(txtPassword.getText().trim().isEmpty()){
            Toast.show(this, Toast.Type.INFO, "Masukkan password Anda", getOptionAlert());
        }else if(cbxRole.getSelectedItem().equals("Pilih Role Anda")){
            Toast.show(this, Toast.Type.INFO, "Pilih role Anda", getOptionAlert());
        }else{
            User modelUser = new User();
            modelUser.setUsername(username);
            
            if(servis.validateUsername(modelUser)){
                valid = true;
            }else{
                Toast.show(this, Toast.Type.WARNING, "Username ini sudah dipakai\nSilakan pilih username lain", getOptionAlert());
                
            }
        }       
        return valid;
    }
    
    private void insertData(){
            if(validateInput() == true){
                String nama = txtNama.getText();
                String email = txtEmail.getText();
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                String role = cbxRole.getSelectedItem().toString();
                
                User modelUser = new User();
                modelUser.setNama(nama);
                modelUser.setEmail(email);
                modelUser.setUsername(username);
                modelUser.setPassword(password);
                modelUser.setRole(role);
                
                servis.insertData(modelUser);
                Toast.show(this, Toast.Type.SUCCESS, "Registrasi Akun Berhasil!", getOptionAlert());
                
                resetForm();
                FormManager.logout();

            }
    }
    
    private void resetForm(){
        txtNama.setText("");
        txtEmail.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        cbxRole.setSelectedIndex(0);
    }
}
