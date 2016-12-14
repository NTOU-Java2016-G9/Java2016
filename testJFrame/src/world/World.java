package world;

import entity.Entity;
import myUtil.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;

public class World {
	private ArrayList<Entity> Entities;
	
	public World (){
		Entities = new ArrayList<Entity>();
		
	}
	
	public void entitySpawnInWorld(Entity newEntity){
		Entities.add(newEntity);
		newEntity.worldObj = this;
	}
	public void entityRemoveFromWorld(Entity deadEntity){
		Entities.remove(deadEntity);
		deadEntity.worldObj = null;
	}
	public ArrayList<AxisAlignedBB> getBoundingBoxes(Entity entityIn){
		 ArrayList<AxisAlignedBB> tempBB = new ArrayList<AxisAlignedBB>();
		 for( Entity iterator : Entities ){
			 if(entityIn != iterator){
				 tempBB.add(iterator.getEntityBoundingBox());
			 }
		 }
		 
		 return tempBB;
	}
	
	public ArrayList<Entity> getEntitys(){
		return Entities;
	}
}
