package renderer;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import org.lwjgl.opengl.GL11;

import myUtil.AxisAlignedBB;

public abstract class Renderer {
	public abstract void renderer();
	public static void renderAABB(AxisAlignedBB MyBB) {
		GL11.glColor3f(1.0f,1.0f,1.0f);
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.minX, (float)MyBB.minY, (float)MyBB.minZ);
		glVertex3f((float)MyBB.maxX, (float)MyBB.minY, (float)MyBB.minZ);
		glEnd();
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.maxX, (float)MyBB.minY, (float)MyBB.minZ);
		glVertex3f((float)MyBB.maxX, (float)MyBB.minY, (float)MyBB.maxZ);
		glEnd();
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.maxX, (float)MyBB.minY, (float)MyBB.maxZ);
		glVertex3f((float)MyBB.minX, (float)MyBB.minY, (float)MyBB.maxZ);
		glEnd();
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.minX, (float)MyBB.minY, (float)MyBB.maxZ);
		glVertex3f((float)MyBB.minX, (float)MyBB.minY, (float)MyBB.minZ);
		glEnd();
		
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.minX, (float)MyBB.maxY, (float)MyBB.minZ);
		glVertex3f((float)MyBB.maxX, (float)MyBB.maxY, (float)MyBB.minZ);
		glEnd();
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.maxX, (float)MyBB.maxY, (float)MyBB.minZ);
		glVertex3f((float)MyBB.maxX, (float)MyBB.maxY, (float)MyBB.maxZ);
		glEnd();
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.maxX, (float)MyBB.maxY, (float)MyBB.maxZ);
		glVertex3f((float)MyBB.minX, (float)MyBB.maxY, (float)MyBB.maxZ);
		glEnd();
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.minX, (float)MyBB.maxY, (float)MyBB.maxZ);
		glVertex3f((float)MyBB.minX, (float)MyBB.maxY, (float)MyBB.minZ);
		glEnd();
		
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.minX, (float)MyBB.minY, (float)MyBB.minZ);
		glVertex3f((float)MyBB.minX, (float)MyBB.maxY, (float)MyBB.minZ);
		glEnd();
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.maxX, (float)MyBB.minY, (float)MyBB.minZ);
		glVertex3f((float)MyBB.maxX, (float)MyBB.maxY, (float)MyBB.minZ);
		glEnd();
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.maxX, (float)MyBB.minY, (float)MyBB.maxZ);
		glVertex3f((float)MyBB.maxX, (float)MyBB.maxY, (float)MyBB.maxZ);
		glEnd();
		glBegin(GL_LINES);
		glVertex3f((float)MyBB.minX, (float)MyBB.minY, (float)MyBB.maxZ);
		glVertex3f((float)MyBB.minX, (float)MyBB.maxY, (float)MyBB.maxZ);
		glEnd();
	}
}
