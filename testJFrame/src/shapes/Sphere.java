package shapes;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_STRIP;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.PI;
public class Sphere extends Shapes {
	private static ShapeObjFile SphereObject;
	private static final String SPHERE_FILE="res/models/ball.obj";
	
	public Sphere(){
		if(null == SphereObject){
			 SphereObject = new ShapeObjFile(SPHERE_FILE,true,false);
		}
	}
	
	@Override
	public void render() {
		SphereObject.render();
	}

}
