package shapes;

import org.lwjgl.opengl.GL11;

public class Bullet extends Shapes {

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Shapes MyCube = new Cube();
		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		MyCube.render();
		GL11.glPopMatrix();
	}

}
