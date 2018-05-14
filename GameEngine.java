
package prid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        private ArrayList<Enemy2> enemies2 = new ArrayList<Enemy2>();
	private SpaceShip v;
        private SpaceShip2 n;
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	private double num = 0;
	public GameEngine(GamePanel gp, SpaceShip v,SpaceShip2 n) {
		this.gp = gp;
		this.v = v;
                this.n = n;
		
		gp.sprites.add(v);
                gp.sprites.add(n);
		
		timer = new Timer(80, new ActionListener() {
                    
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
                                num++;
                                if(num >= 40){
                                    difficulty += 0.001;
                                }
                         
			}
		});
		timer.setRepeats(true);
                        	
	}
        
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
                Enemy2 e2 = new Enemy2((int)(Math.random()*390), 30);
		gp.sprites.add(e);
                gp.sprites.add(e2);
		enemies.add(e);
                enemies2.add(e2);
	}
	
	private void process(){
		if(Math.random() < difficulty){
                    
                        
			generateEnemy();
                        
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
                Iterator<Enemy2> e2_iter = enemies2.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 10;
			}
                        
                while(e2_iter.hasNext()){
			Enemy2 e2 = e2_iter.next();
			e2.proceed();
			
			if(!e2.isAlive()){
				e2_iter.remove();
				gp.sprites.remove(e2);
				score += 20;
			}
                                
                           
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
                Rectangle2D.Double nr = n.getRectangle();
                
                
		Rectangle2D.Double er;
		for(Enemy e1 : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
                                return;
                        }
                        else if(er.intersects(nr)){
                                die();
                                return;
                        }
                                
				
		}
                for(Enemy2 e2 : enemies2){
			er = e2.getRectangle();
			if(er.intersects(vr)){
				die();
                                return;
                        }
                        else if(er.intersects(nr)){
                                die();
                                return;
                        }
		}
	}
        }
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
                case KeyEvent.VK_Z:
			n.move(-1);
			break;   
                case KeyEvent.VK_C:
			n.move(1);
			break;
                        
		case KeyEvent.VK_A:
			difficulty += 0.1;
			break;
		}
	}

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
