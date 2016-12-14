package entity;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;
import myUtil.AxisAlignedBB;
import renderer.Renderer;
import world.World;

public abstract class Entity {
	public boolean isDead = false;
	public World worldObj;
	/*TODO SHOULD NEW A CLASS*/
	public List<AxisAlignedBB> getBoundingBoxes(){
		
		return worldObj.getBoundingBoxes(this);
	}
	
	public Entity()
	{
		this.boundingBox = new AxisAlignedBB(0,0,0,0,0,0);
		//Entity.AABBPool.add(boundingBox);

	}
	private Vector3f Pos = new Vector3f();
	private Vector3f Speed = new Vector3f();
	private Vector3f Acc = new Vector3f();
    /** Axis aligned bounding box. */
    private AxisAlignedBB boundingBox;
	public void Update(){
		Speed.add(Acc);
		Pos.add(Speed);
	}
	public Vector3f getPos(){
		return Pos;
	}
	public void setPos(Vector3f newPos){
		Pos = newPos;
	}
	public void setPos(float x, float y, float z){
		Pos.x=x;
		Pos.y=y;
		Pos.z=z;
	}
	public abstract Renderer getRender();
	public void setDead(){
		isDead = true;
	}
	public abstract void setPosition(double x, double y, double z);
	public abstract void moveEntity(double x, double y, double z);
	public abstract void playSound(String name, float volume, float pitch);
	 public abstract AxisAlignedBB getCollisionBoundingBox();
	 public abstract float getDistanceToEntity(Entity entityIn);
	 protected abstract void setBeenAttacked();
	public abstract boolean canBeCollidedWith();
	public abstract AxisAlignedBB getCollisionBox(Entity entityIn);
	public abstract boolean canAttackWithItem();
	 public abstract boolean hitByEntity(Entity entityIn);
	 //public boolean isEntityInvulnerable(DamageSource source);
	 public AxisAlignedBB getEntityBoundingBox()
	 {
	     return this.boundingBox;
	 }
	 public void setEntityBoundingBox(AxisAlignedBB bb){
		 this.boundingBox = bb;
	 }
	  
}
