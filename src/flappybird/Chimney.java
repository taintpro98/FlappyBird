package flappybird;

import pkg2dgamesframework.Objects;

public class Chimney extends Objects{
	public Chimney(int x, int y, int w, int h) {
		super(x,y,w,h);
		
	}
	
	public void update() {
		setPosX(getPosX()-2);
	}
}
