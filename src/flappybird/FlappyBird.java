package flappybird;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;

public class FlappyBird extends GameScreen{
	
	private BufferedImage birds;
//	private BufferedImage chimney;
	private Animation bird_anim;
	public static float g = 0.1f;
	private Bird bird;
	private Ground ground;
//	private Chimney cnObject;
	private ChimneyGroup chimneyGroup;
	
	private int BEGIN_SCREEN = 0;
	private int GAMEPLAY_SCREEN = 1;
	private int GAMEOVER_SCREEN = 2;
	private int CurrentScreen = BEGIN_SCREEN;
	
	public FlappyBird (){
		super(800, 600);
		
		try {
			birds = ImageIO.read(new File("///home/batman/eclipse-workspace/JavaApplication/assets/bird_sprite.png"));
			
//			chimney = ImageIO.read(new File("///home/batman/eclipse-workspace/JavaApplication/assets/chimney.png"));

		} catch (IOException ex) {}
		
		bird_anim = new Animation(70);
		AFrameOnImage f;
		f = new AFrameOnImage(0, 0, 60, 60);
		bird_anim.AddFrame(f);
		f = new AFrameOnImage(60, 0, 60, 60);
		bird_anim.AddFrame(f);
		f = new AFrameOnImage(120, 0, 60, 60);
		bird_anim.AddFrame(f);
		f = new AFrameOnImage(60, 0, 60, 60);
		bird_anim.AddFrame(f);
		
		bird = new Bird(350, 350, 50, 50);
		
		ground = new Ground();
		
		chimneyGroup = new ChimneyGroup();
		
		BeginGame();
	}
	
	public static void main(String[] args) {
		new FlappyBird();
	}
	
	private void resetGame() {
		bird.setPos(350, 250);
		bird.setVt(0);
	}

	@Override
	public void GAME_UPDATE(long deltaTime) {
		// TODO Auto-generated method stub
		
		if(CurrentScreen == BEGIN_SCREEN) {
			resetGame();
		} else if(CurrentScreen == GAMEPLAY_SCREEN) {

			bird_anim.Update_Me(deltaTime);
			bird.update(deltaTime);
			ground.Update();
			chimneyGroup.update();
			if(bird.getPosY() + bird.getH() > ground.getYGround()) {
				CurrentScreen = GAMEOVER_SCREEN;
			}
			
		} else {
			
		}
		
		
	}

	@Override
	public void GAME_PAINT(Graphics2D g2) {
		// TODO Auto-generated method stub
//		g2.setColor(Color.blue);
//		g2.fillRect(69, 69, 69, 69);
		
		chimneyGroup.paint(g2);
		ground.Paint(g2);
		
		if(bird.getIsFlying())
			bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, -1);
		else 
			bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, 0);
		
		if(CurrentScreen == BEGIN_SCREEN) {
			g2.setColor(Color.red);
			g2.drawString("Press space to begin", 200, 300);
		}
		if(CurrentScreen == GAMEOVER_SCREEN) {
			g2.setColor(Color.red);
			g2.drawString("Press space to turn back begin screen", 200, 300);
		}
		
	}

	@Override
	public void KEY_ACTION(KeyEvent e, int Event) {
		// TODO Auto-generated method stub
		if(Event == KEY_PRESSED) {
			if(CurrentScreen == BEGIN_SCREEN) {
				CurrentScreen = GAMEPLAY_SCREEN;
			} else if(CurrentScreen == GAMEPLAY_SCREEN) {
				bird.fly();
			} else if(CurrentScreen == GAMEOVER_SCREEN){
				CurrentScreen = BEGIN_SCREEN;
			}
		}
		
	}
	
}
