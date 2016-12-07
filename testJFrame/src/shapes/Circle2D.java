package shapes;

import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class Circle2D extends Shapes {

	@Override
	public void render() {
		// TODO Auto-generated method stub
		 glBegin(GL_POLYGON);
	        for(int i =0; i <= 20; i++){
	        float angle = (float)(2.0 * Math.PI * i / 20);
	        float x = (float)Math.cos(angle)/2;
	        float y = (float)Math.sin(angle)/2;
	        glVertex2f(x,y);
	        }
	        glEnd();
	}

}
