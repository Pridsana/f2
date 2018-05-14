
package prid;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class spacecraft {
    private int num;
    private int num2;
    
    public spacecraft(int x, int y) {
       num = x;
       num2 = y;
    }

   
    public void draw(Graphics2D g) {
        
        g.setColor(Color.pink);
                
	g.fillRect(num, num2, 150, 10);
    }

	
}