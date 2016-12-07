package shapes;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glLoadMatrixf;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glMaterialfv;
import static org.lwjgl.opengl.GL11.glMaterialf;

import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_SPECULAR;
import static org.lwjgl.opengl.GL11.GL_SHININESS;

import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glScalef;

public class Blade extends Shapes {

	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		glMaterialfv(GL_FRONT,GL_DIFFUSE,new float[]{0.5f, 0.5f, 1.0f, 1.0f});
		glMaterialfv(GL_FRONT,GL_SPECULAR,new float[]{0.1f, 0.1f, 0.1f, 1.0f});
		glMaterialf(GL_FRONT,GL_SHININESS,32);
		glNormal3f(0,0,1);
		glBegin(GL_POLYGON);
		glVertex3f(0.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, 4.0f, 0.0f);
		glVertex3f(1.0f, 8.0f, 0.0f);
		glVertex3f(-1.0f, 8.0f, 0.0f);
		glVertex3f(-1.0f, 4.0f, 0.0f);
		glEnd();
		glNormal3f(0,0,-1);
		glBegin(GL_POLYGON);
		glVertex3f(-1.0f, 4.0f, 0.0f);
		glVertex3f(-1.0f, 8.0f, 0.0f);
		glVertex3f(1.0f, 8.0f, 0.0f);
		glVertex3f(1.0f, 4.0f, 0.0f);
		glVertex3f(0.0f, 0.0f, 0.0f);
		glEnd();
	}

}
