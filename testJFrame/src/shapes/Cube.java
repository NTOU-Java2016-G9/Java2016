package shapes;

import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glNormal3f;
import org.joml.Vector3f;

public class Cube extends Shapes {
	
	private static final Vector3f[]  points = { new Vector3f( -1f, -1f, -1f ),new Vector3f (1f, -1f, -1f),
			new Vector3f( 1f, 1f, -1f ),new Vector3f( -1f, 1f, -1f ),
			new Vector3f( -1f, -1f, 1f),new Vector3f( 1f, -1f, 1f ),
			new Vector3f( 1f, 1f, 1f  ),new Vector3f( -1f, 1f, 1f )};
			/* face of box, each face composing of 4 vertices */
	private static final int [][] face = { { 0, 3, 2, 1 },{ 0, 1, 5, 4 },{ 1, 2, 6, 5 },
			{ 4, 5, 6, 7 },{ 2, 3, 7, 6 },{ 0, 4, 7, 3 } };
			/* indices of the box faces */
	/*----Define normals of faces ----*/
	Vector3f [] normal = {new Vector3f(0, 0, -1), new Vector3f(0, -1, 0), new Vector3f(1, 0, 0),
			new Vector3f(0, 0, 1), new Vector3f(0, 1, 0), new Vector3f(-1, 0, 0)};
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		int    i;
		//System.out.println("Something cube");
		for (i = 0; i<6; i++) {
			glNormal3f(normal[i].x,normal[i].y,normal[i].z);
			glBegin(GL_POLYGON);  /* Draw the face */
			glVertex3f(points[face[i][0]].x,points[face[i][0]].y,points[face[i][0]].z);
			glVertex3f(points[face[i][1]].x,points[face[i][1]].y,points[face[i][1]].z);
			glVertex3f(points[face[i][2]].x,points[face[i][2]].y,points[face[i][2]].z);
			glVertex3f(points[face[i][3]].x,points[face[i][3]].y,points[face[i][3]].z);
			glEnd();
		}
		
	}

}
