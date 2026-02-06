import Control.SysController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
        
        // --- 1. เพิ่มโค้ดส่วนนี้เข้าไปเพื่อแก้ภาษาไทย ---
        try {
            // ใช้ Font Tahoma ขนาด 14 (ฟอนต์มาตรฐานที่อ่านไทยออกแน่นอนบน Windows)
            setUIFont(new javax.swing.plaf.FontUIResource("Tahoma", Font.PLAIN, 14));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // -------------------------------------------

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SysController(); 
            }
        });
    }

    // --- 2. ก๊อป Method นี้ไปแปะต่อท้ายด้านล่าง ---
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}