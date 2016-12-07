package shapes;

import static org.lwjgl.opengl.GL11.GL_QUAD_STRIP;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glRotatef;

public class Cylinder extends Shapes {
	private static final Circle2D MyCircle = new Circle2D();
	@Override
	public void render() {
		// TODO Auto-generated method stub
		/* draw inside radius cylinder */
		//glRotatef(180.0f,0.0f,0.0f,1.0f);
		glBegin(GL_QUAD_STRIP);
		for ( int i = 20; i >= 0; i-- ) {
			float angle = i * 2.0f * (float)Math.PI / 20;
			glNormal3f(-(float)Math.cos(angle), -(float)Math.sin(angle), 0.0f);
			glVertex3f(0.5f * (float)Math.cos(angle), 0.5f * (float)Math.sin(angle), -1.0f);
			glVertex3f(0.5f * (float)Math.cos(angle), 0.5f * (float)Math.sin(angle), 1.0f);
		}
		glEnd();
		glTranslatef(0.0f,0.0f,-1.0f);
		MyCircle.render();
		glTranslatef(0.0f,0.0f, 2.0f);
		MyCircle.render();

	}

}
