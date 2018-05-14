 
package prid;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip2 extends Sprite{

	int step = 8;
	
	public SpaceShip2(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 380 - width)
			x = 380 - width;
	}

}