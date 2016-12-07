package entity;

import myUtil.AxisAlignedBB;
import renderer.RenderChopper;
import renderer.Renderer;

public class EntityChopper extends Entity {
	
	private float PropellerSpeed = 0;
	private float BodyAngle = 0;

	public EntityChopper()
	{
		super();
		setEntityBoundingBox(new AxisAlignedBB(this.getPos().x,this.getPos().y,this.getPos().z,
				this.getPos().x+2,this.getPos().y+2,this.getPos().z));
		System.out.print(this.getBoundingBoxes());
	}
	public EntityChopper(float x, float y, float z)
	{
		super();
		this.setPos(x, y, z);
		setEntityBoundingBox(new AxisAlignedBB(this.getPos().x,this.getPos().y,this.getPos().z,
				this.getPos().x+2,this.getPos().y+2,this.getPos().z));
		System.out.print(this.getBoundingBoxes());
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
		boolean canMove = true;
		for(AxisAlignedBB aabb1 : this.getBoundingBoxes()){
			if(beforeMoved.equals(aabb1))continue;
			if(afterMoved.intersectsWith(aabb1)){
				canMove=false;
				break;
			}
		}
		if(canMove){
			this.setPos(getPos().add((float)x, (float)y, (float)z));
			this.setEntityBoundingBox(afterMoved);
		}else{
			System.out.print("Outch!");
			//hurt
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
