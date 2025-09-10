package com.spk.main;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import raven.modal.Drawer;
import raven.modal.demo.icons.SVGIconUIColor;
public class MainForm extends JPanel{
    private JPanel mainPanel;
    
    public MainForm(){
        init();
    }
    
    public void init(){
        setLayout(new MigLayout("fillx, wrap, insets 0, gap 0", "[fill]", "[][fill, grow][][]"));
        add(createHeader());
        add(createMain());
        add(new JSeparator(), "height 2");
        add(createFooter());
        
    }
    
    private JPanel createHeader(){
        JPanel panel = new JPanel(new MigLayout("insets 3", "[]push[]push", "[fill]"));
        
        JToolBar toolbar = new JToolBar();
        
        JButton buttonDrawer = new JButton(new FlatSVGIcon("raven/modal/demo/icons/menu.svg", 0.5f));
        
        buttonDrawer.addActionListener((e) -> {
            if(Drawer.isOpen()){
                Drawer.showDrawer();
            }else{
                Drawer.toggleMenuOpenMode();
            }
        });
        
        toolbar.add(buttonDrawer);
        panel.add(toolbar);
        return panel;
    }
    
    private JPanel createFooter(){
        JPanel panel = new JPanel(new MigLayout("insets 1 n 1 n, al trailing center, gapx 10, height 30!", "[]push[]push", "fill"));
        panel.putClientProperty(FlatClientProperties.STYLE, "background:tint($Panel.background, 20%)");
        
        JLabel lbAppVersion = new JLabel("Sistem Penunjang Keputusan Pemilihan Supplier Terbaik" + Main.APP_VERSION);
        lbAppVersion.putClientProperty(FlatClientProperties.STYLE, "foreground:$Label.disabledForeground");
        lbAppVersion.setIcon(new SVGIconUIColor("raven/modal/demo/icons/git.svg", 1f, "Label.disabledForeground"));
        
        panel.add(lbAppVersion);
        
        String javaVendor = System.getProperty("java.vendor");
        if(javaVendor.equals("Oracle Coorporation")){
            javaVendor= "";
        }
        
        String java = javaVendor + " v" + System.getProperty("java.version").trim();
        String st = "Running on: Java %s";
        JLabel lbJava = new JLabel(String.format(st, java));
        lbJava.putClientProperty(FlatClientProperties.STYLE, "foreground:$Label.disabledForeground");
        lbJava.setIcon(new SVGIconUIColor("raven/modal/demo/icons/java.svg", 1f, "Label.disabledForeground"));
        
        panel.add(lbJava);
        
        JLabel date = new JLabel();
        date.putClientProperty(FlatClientProperties.STYLE, "foreground:$Label.disabledForeground");
        Timer timer = new Timer(1, (e) -> {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            date.setText(df.format(new Date()));
        });
        
        panel.add(date);
        timer.start();
        return panel;
    }
    
    private Component createMain(){
        mainPanel = new JPanel(new BorderLayout());
        return mainPanel;
    }
    
    public void setForm(Form form){
        mainPanel.removeAll();
        mainPanel.add(form);
        mainPanel.repaint();
        mainPanel.revalidate();
        
        if(mainPanel.getComponentOrientation().isLeftToRight() != form.getComponentOrientation().isLeftToRight()){
            applyComponentOrientation(mainPanel.getComponentOrientation());
        }
    }
}
