package com.spk.main;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import raven.extras.AvatarIcon;
import raven.modal.drawer.DrawerPanel;
import raven.modal.drawer.data.Item;
import raven.modal.drawer.data.MenuItem;
import raven.modal.drawer.menu.MenuAction;
import raven.modal.drawer.menu.MenuEvent;
import raven.modal.drawer.menu.MenuOption;
import raven.modal.drawer.menu.MenuStyle;
import raven.modal.drawer.renderer.DrawerStraightDotLineStyle;
import raven.modal.drawer.simple.SimpleDrawerBuilder;
import raven.modal.drawer.simple.footer.LightDarkButtonFooter;
import raven.modal.drawer.simple.footer.SimpleFooterData;
import raven.modal.drawer.simple.header.SimpleHeaderData;

public class Menu extends SimpleDrawerBuilder{

    private final int SHADOW_SIZE = 0;
    
    public Menu() {
        super(createSimpleMenuOption());
        LightDarkButtonFooter lightDarkButtonFooter = (LightDarkButtonFooter) footer;
        lightDarkButtonFooter.removeAll();
        setColorTitle();
    }
    
    private void setColorTitle(){
        if(header instanceof JComponent){
            for(Component comp : ((JComponent) header).getComponents()){
                if(comp instanceof JPanel){
                    int labelCount = 0;
                    
                    for(Component subComp : ((JPanel) comp).getComponents()){
                        if(subComp instanceof JLabel){
                            if(labelCount == 0){
                                ((JLabel) subComp).setForeground(Color.WHITE);
                            }else if(labelCount == 1){
                                ((JLabel) subComp).setForeground(Color.GRAY);
                            }
                            labelCount++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon;
        icon = new AvatarIcon(getClass().getResource("/com/spk/images/Logo.png"), 50, 50, 100f);
        icon.setType(AvatarIcon.Type.ROUND);
        icon.setBorder(0, 0);
        
        changeAvatarIconBorderColor(icon);
        
        UIManager.addPropertyChangeListener((evt) -> {
            if(evt.getPropertyName().equals("lookAndFeel")){
                changeAvatarIconBorderColor(icon);
            }
        });
        return new SimpleHeaderData().setIcon(icon).setTitle("Ishafakhri").setDescription("SPK");
    }
    
    private void changeAvatarIconBorderColor(AvatarIcon icon) {
        icon.setBorderColor(new AvatarIcon.BorderColor(UIManager.getColor("Component.accentColor"), 0.7f));
        
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData().setDescription("Version" + Main.APP_VERSION);
    }
    
    public static MenuOption createSimpleMenuOption(){
        String role = "Admin";
        MenuOption simpleMenuOption = new MenuOption();
        
        MenuItem[] adminMenu = new MenuItem[]{
            new Item.Label("MAIN"),
            new Item("Dashboard", "dashboard.svg"),
            new Item.Label("MASTER"),
            new Item("Supplier", "supplier.svg"),
            new Item("Kriteria", "kriteria.svg"),
            new Item.Label("AHP"),
            new Item("Perbandingan Kriteria", "ahp.svg"),
            new Item("Perbandingan Alternatif", "ahp.svg"),
            new Item("Hasil Perhitungan", "hasil.svg"),
            new Item.Label("OTHER"),
            new Item("Management User", "user.svg")
                .subMenu("Data User")
                .subMenu("Profile")
                .subMenu("Pergantian Password"),
            new Item("About", "about.svg"),
            new Item("Logout", "logout.svg")
        };
        
        MenuItem[] userMenu = new MenuItem[]{
            new Item.Label("MAIN"),
            new Item("Dashboard", "dashboard.svg"),
            new Item("About", "about.svg"),
            new Item("Logout", "logout.svg")
        };
        
        MenuItem[] menuToUse;
        switch(role){
            case "Admin":
                menuToUse = adminMenu;
                break;
            case "User":
                menuToUse = userMenu;
                break;
            default:
                menuToUse = new MenuItem[0];
                break;
        }
        
        simpleMenuOption.setMenuStyle(new MenuStyle(){
            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, getDrawerBackgroundStyle());
            }

            @Override
            public void styleMenuItem(JButton menu, int[] index, boolean isMainItem) {
                menu.setForeground(Color.WHITE);
                menu.putClientProperty("Button.selectedBackground", Color.BLUE);
            }
            
            
        });
        simpleMenuOption.getMenuStyle().setDrawerLineStyleRenderer(new DrawerStraightDotLineStyle());
        simpleMenuOption.setMenuItemAutoSelectionMode(MenuOption.MenuItemAutoSelectionMode.SELECT_SUB_MENU_LEVEL);
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int[] ints) {
                Class<?> itemClass = action.getItem().getItemClass();
                
                int i = ints[0];
                if(role.equals("Admin")){
                    if( i == 8 ){
                        action.consume();
                        FormManager.logout();
                        return;
                    }
                    handleFormAction(itemClass, action);
                }else if(role.equals("User")){
                    if( i == 1 ){
                        action.consume();
                        FormManager.logout();
                        return;
                    }
                    handleFormAction(itemClass, action);
                }
            }
            
            private void handleFormAction(Class<?> itemClass, MenuAction action){
                if(itemClass == null || !Form.class.isAssignableFrom(itemClass)){
                    action.consume();   
                    return;
                }
                
                @SuppressWarnings("Unchecked")
                Class<? extends Form> formClass = (Class<? extends Form>) itemClass;
                FormManager.showForm(AllForms.getForm(formClass));
            }
        });
        
        simpleMenuOption.setMenus(menuToUse)
                .setBaseIconPath("com/spk/icons")
                .setIconScale(0.45f);
        return simpleMenuOption;
    }

    @Override
    public int getDrawerWidth() {
        return 270;
    }

    @Override
    public int getDrawerCompactWidth() {
        return 80;
    }

    @Override
    public int getOpenDrawerAt() {
        return 1000;
    }

    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, getDrawerBackgroundStyle());
    }
    
    private static String getDrawerBackgroundStyle(){
        return "background:@accentColor";
    }
    
    
    
}
