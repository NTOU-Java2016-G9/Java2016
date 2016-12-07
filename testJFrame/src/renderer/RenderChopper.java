package renderer;

import org.lwjgl.opengl.GL11;

import entity.*;
import shapes.*;

public class RenderChopper extends Renderer {
	
	private Chopper MyChopper = new Chopper();
	private EntityChopper EChopper;

	public RenderChopper(EntityChopper instanct){
		EChopper = instanct;
	}
	
	@Override
	public void renderer() {
		// TODO Auto-generated method stub
		
		
		
		MyChopper.setSpeed(EChopper.getPropellerSpeed());
		
		GL11.glPushMatrix();
		GL11.glTranslatef(EChopper.getPos().x,EChopper.getPos().y,EChopper.getPos().z);
		GL11.glRotatef(EChopper.getBodyAngle(), 0, 0, -1);
		MyChopper.render();
		GL11.glPopMatrix();
	}
	


}
