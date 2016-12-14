package renderer;

import org.lwjgl.opengl.GL11;

import entity.EntityBullet;
import shapes.Bullet;

public class RenderBullet extends Renderer {
	
	private Bullet MyBullet = new Bullet();
	private EntityBullet EBullet;

	public RenderBullet(EntityBullet instanct){
		EBullet = instanct;
	}
	
	@Override
	public void renderer() {
		// TODO Auto-generated method stub
		renderAABB(EBullet.getEntityBoundingBox());
		GL11.glPushMatrix();
		GL11.glTranslatef(EBullet.getPos().x,EBullet.getPos().y,EBullet.getPos().z);
		MyBullet.render();
		GL11.glPopMatrix();
	}

}
