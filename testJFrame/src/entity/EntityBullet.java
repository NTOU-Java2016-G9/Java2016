package entity;

import myUtil.AxisAlignedBB;
import renderer.RenderBullet;
import renderer.Renderer;

public class EntityBullet extends Entity {
	
	public EntityBullet()
	{
		super();
		setEntityBoundingBox(new AxisAlignedBB(
				this.getPos().x-0.25,this.getPos().y-0.25,this.getPos().z-0.25,
				this.getPos().x+0.25,this.getPos().y+0.25,this.getPos().z+0.25));
		System.out.print(this.getEntityBoundingBox());
	}
	public EntityBullet(float x, float y, float z)
	{
		super();
		this.setPos(x, y, z);
		setEntityBoundingBox(new AxisAlignedBB(
				this.getPos().x-0.5,this.getPos().y-0.5,this.getPos().z-0.5,
				this.getPos().x+0.5,this.getPos().y+0.5,this.getPos().z+0.5));
		System.out.print(this.getEntityBoundingBox());
	}
	
	@Override
	public void Update()
	{
		this.moveEntity(0.1f,0.0f, 0.0f);
		if(this.getPos().x >=25)
		{
			dead();
		}
	}
	
	@Override
	public Renderer getRender() {
		// TODO Auto-generated method stub
	 return new RenderBullet(this);
	}

	@Override
	public void setPosition(double x, double y, double z) {
		// TODO Auto-generated method stub

	}

	@Override
		// TODO Auto-generated method stub
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
						this.dead();
						break;
					}
				}
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
