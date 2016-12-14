package entity;

import java.util.ArrayList;

import myUtil.AxisAlignedBB;
import renderer.RenderChopper;
import renderer.Renderer;

public class EntityChopper extends Entity {
	
	private float PropellerSpeed = 0;
	private float BodyAngle = 0;

	public EntityChopper()
	{
		super();
		setEntityBoundingBox(new AxisAlignedBB(
				this.getPos().x-1.55,this.getPos().y-0.05,this.getPos().z-1.55,
				this.getPos().x+1.55,this.getPos().y+3.05,this.getPos().z+1.55));
		System.out.print(this.getEntityBoundingBox());
	}
	public EntityChopper(float x, float y, float z)
	{
		super();
		this.setPos(x, y, z);
		setEntityBoundingBox(new AxisAlignedBB(
				this.getPos().x-1.55,this.getPos().y-0.05,this.getPos().z-1.55,
				this.getPos().x+1.55,this.getPos().y+3.05,this.getPos().z+1.55));
		System.out.print(this.getEntityBoundingBox());
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		super.Update();
		if(PropellerSpeed>=360.0)PropellerSpeed=0;
		PropellerSpeed+=8.7f;
		if(BodyAngle >= 0.07) BodyAngle -=0.07;
		else if(BodyAngle <= -0.07) BodyAngle +=0.07;
		else if(BodyAngle<=0.01f||BodyAngle>=-0.01f) BodyAngle = 0f;	
	}
	
	public float getBodyAngle()
	{
		return BodyAngle;
	}
	public void setBodyAngle(float newAngle)
	{
		BodyAngle = newAngle;
	}
	public float getPropellerSpeed()
	{
		return PropellerSpeed;
	}

	@Override
	public Renderer getRender(){
		return new RenderChopper(this);
	}

	@Override
	public void setPosition(double x, double y, double z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveEntity(double x, double y, double z) {
		// TODO Auto-generated method stub
		AxisAlignedBB beforeMoved = this.getEntityBoundingBox();
		AxisAlignedBB afterMoved = this.getEntityBoundingBox().offset(x,y,z);
		AxisAlignedBB whose = null;
		boolean canMove = true;
		for(AxisAlignedBB aabb1 : this.getBoundingBoxes()){
			if(beforeMoved.equals(aabb1))continue;
			if(afterMoved.intersectsWith(aabb1)){
				canMove=false;
				whose = aabb1;
				break;
			}
		}
		this.setPos(getPos().add((float)x, (float)y, (float)z));
		this.setEntityBoundingBox(afterMoved);
		if(!canMove){
			for(Entity iterator : this.worldObj.getEntitys()){
				if(iterator.getEntityBoundingBox() == whose){
					System.out.println("[" + iterator +"] Outch!");
					break;
				}
			}
			System.out.println("[" + this +"] Sorry!");
		}
	}

	@Override
	public void playSound(String name, float volume, float pitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getDistanceToEntity(Entity entityIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void setBeenAttacked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canBeCollidedWith() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canAttackWithItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hitByEntity(Entity entityIn) {
		// TODO Auto-generated method stub
		return false;
	}


}
