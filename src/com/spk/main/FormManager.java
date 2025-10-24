package com.spk.main;

import com.spk.dao.UserDAO;
import com.spk.form.FormLogin;
import com.spk.form.FormRegistrasi;
import com.spk.service.ServiceUser;
import javax.swing.JFrame;
import raven.modal.Drawer;
import raven.modal.demo.utils.UndoRedo;


public class FormManager {
    public static final UndoRedo<Form> FORMS = new UndoRedo<>();
    private static MainForm mainForm;
    private static JFrame frame;
    private static String loggedInUser;
    private static FormLogin formLogin;

    public static void install(JFrame f){
        frame = f;
        
        ServiceUser servis = new UserDAO();
        
        if(!servis.isUserExist()){
            registrasi();
        }else{
            logout();
        }
    }
    
    public static void showForm(Form form){
        if(form != FORMS.getCurrent()){
            FORMS.add(form);
            form.formCheck();
            form.formOpen(); 
            mainForm.setForm(form);
        }
    }
    
    public static void login(){
        loggedInUser = "";
        Drawer.installDrawer(frame, new Menu());
        Drawer.setVisible(true);
        
        frame.getContentPane().removeAll();
        frame.getContentPane().add(getMainForm());
        frame.repaint();
        frame.revalidate();
    }
    
    
    public static void logout(){
        if(loggedInUser == null){
            Drawer.installDrawer(frame, new Menu());
        }
        
        Drawer.setVisible(false);
        frame.getContentPane().removeAll();
        FormLogin login = getLogin();
        login.formCheck();
        frame.getContentPane().add(login);
        FORMS.clear();
        frame.repaint();
        frame.revalidate();     
    }
    
    public static void registrasi(){
        if(loggedInUser == null){
            Drawer.installDrawer(frame, new Menu());
        }
        
        Drawer.setVisible(false);
        frame.getContentPane().removeAll();
        FormRegistrasi formRegistrasi = new FormRegistrasi();
//        formRegistrasi.formCheck();
        frame.getContentPane().add(formRegistrasi);
        FORMS.clear();
        frame.repaint();
        frame.revalidate();     
    }
    
    public static JFrame getJFrame(){
        return frame;
    }
    
    private static MainForm getMainForm(){
        if(mainForm == null){
            mainForm = new MainForm();
        }
        return mainForm;
    }
    
    private static FormLogin getLogin(){
        if(formLogin == null){
            formLogin = new FormLogin();
        }
        return formLogin;
    }
}
