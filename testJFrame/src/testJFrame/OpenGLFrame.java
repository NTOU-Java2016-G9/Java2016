package testJFrame;


import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_NORMALIZE;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_SPECULAR;
import static org.lwjgl.opengl.GL11.GL_LIGHT1;
import static org.lwjgl.opengl.GL11.GL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_SHININESS;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_LOCAL_VIEWER;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_SPOT_DIRECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glLightfv;
import static org.lwjgl.opengl.GL11.glMaterialfv;
import static org.lwjgl.opengl.GL11.glMaterialf;
import static org.lwjgl.opengl.GL11.glLightModeli;
import static org.lwjgl.opengl.GL11.glLightModelfv;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glLoadMatrixf;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glPolygonMode;




import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import shapes.*;
import utility.Model;
import utility.OBJLoader;
import entity.*;
import renderer.*;
import world.*;

import java.awt.Point;
import javax.swing.SwingUtilities;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.camera.FreeCamera;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.awt.AWTGLCanvas;
import org.lwjgl.opengl.awt.GLData;
import java.util.ArrayList;


@SuppressWarnings({ "serial" })
public class OpenGLFrame extends AWTGLCanvas {

	private World MyWorld;
	
	private static final String MODEL_BUNNY = "res/models/bunny.obj";
	private static final String MODEL_MITSIHA = "res/models/mitsiha/mitsiha.obj";
    private static int BunnyObject;

    
	int width;
    int height;
    int fbWidth;
    int fbHeight;
    float mouseX, mouseY;
    
    
    private Vector3f tmp;
    private Matrix4f mat;
    private long lastTime;
    private FloatBuffer fb;
    private float accFactor;
    private float rotateZ;
    private float PosX = 0f;
    private float PosY = 1.5f;
    private float PosZ = 50f;
    private EntityChopper MyChopperEntity;
   
    //private ArrayList<Entity> entityPool;
    
    private static ShapeObjFile test_bunny;
    private static ShapeObjFile MitsihaObject;
    
    
    
    FreeCamera cam = new FreeCamera();
    {
        cam.position.set(PosX, PosY, PosZ);
        cam.rotation.set(0.0f, 0.0f, 0.0f);
    }
	 private boolean up = false;
	 private boolean down = false;
	 private boolean left = false;
	 private boolean right = false;
	public OpenGLFrame( GLData data ) {
		
		super(data);
		MyWorld = new World();
		
		instance = this;
		 MyChopperEntity = new EntityChopper();
		 
		 MyWorld.entitySpawnInWorld(MyChopperEntity);
		 MyWorld.entitySpawnInWorld( new EntityChopper(5,5,0));
	       MouseAdapter mouseAdapter = new MouseAdapter ()
	        {
	    	   private short keyPressed = 0;
	    	   
	    	   private Point iniPos; 
	            public void mousePressed ( MouseEvent e )
	            {
	            	/*if(e.getButton()==MouseEvent.BUTTON1||e.getButton()==MouseEvent.BUTTON3){
	            		iniPos = e.getPoint();
	            		keyPressed = (short) e.getButton();
	            	}
	            	System.out.println(e.getButton()+"is pressed");
	            	// e.getPoint (), e.getPoint () ;
	            	*/
	            }

	            public void mouseMoved ( MouseEvent e )
	            {
	                  
	            }
	           
	            public void mouseReleased ( MouseEvent e )
	            {
					/*
					  	keyPressed = 0;
						mouseY=0;
						mouseX=0;
					*/
	            }
	            
	          
				public void mouseDragged(MouseEvent e)
	            {
	            	/*
	            	 * System.out.println(e.getButton());
	            	float WayX = (e.getPoint().x- iniPos.x);
	            	float WayY = (e.getPoint().y- iniPos.y);
	            	iniPos = e.getPoint();
	            	
	            	if(this.keyPressed==1)
	            	{
		            	System.out.println("point:" + e.getPoint () + ", " + iniPos);
		            	mouseY = 0.1f*WayX;
		            	mouseX = 0.1f*WayY;
	            	}
	            	if(this.keyPressed==3)
	            	{
	            		System.out.println("point:" + e.getPoint () + ", " + iniPos);
	            		PosX += 0.01f*WayX;
		            	PosY += 0.01f*WayY;
	            	}
	            	*/
	            }
	            public void mouseWheelMoved(MouseWheelEvent e)
	            {
	            	/*
	            	 * 	System.out.println(e.getWheelRotation());
	            	 *	PosZ += 0.25f*e.getWheelRotation();
	            	 */
	            }
	        };
	        
	        KeyAdapter keyAdapter = new KeyAdapter (){

	        	public void keyPressed( KeyEvent e)
	        	{
	        	int key1 = e.getKeyCode();
	            char key2 = e.getKeyChar();
	           
	             switch (key2)
	             {
		             case 'w':
		             {
		            	 up = true;
		            	 down = false;
		            	 break;
		             }
		             case 's':
		             {
		            	 down = true;
		            	 up = false;
		            	 break;
		             }
		             case 'a':
		             {
		            	 left = true;
		            	 right = false;
		            	 break;
		             }
		             case 'd':
		             {
		            	 right = true;
		            	 left = false;
		            	 break;
		             }
		             default:
		             	{
		          
		             	}
	             }
	             switch (key1)
		           {
		           	case 32:
	            	MyWorld.entitySpawnInWorld(new EntityBullet(MyChopperEntity.getPos().x+2f,
											            		MyChopperEntity.getPos().y+1.5f,
											            		MyChopperEntity.getPos().z));
	            	break;
		           }
	        	}
	        	public void keyReleased( KeyEvent e )
	        	{
	        		int key1 = e.getKeyCode();
		            char key2 = e.getKeyChar();
		            
		            switch (key2)
		             {
			             case 'w':
			             {
			            	 up = false;
			            	 break;
			             }
			             case 's':
			             {
			            	 down = false;
			            	 break;
			             }
			             case 'a':
			             {
			            	 left = false;
			            	 break;
			             }
			             case 'd':
			             {
			            	 right = false;
			            	 break;
			             }
			             case ' ':
			             default:
			             	{
			          
			             	}
			           
		             }
		            
		            
	        	}

	        	 
	        };
	        addKeyListener( keyAdapter );
	        addMouseListener ( mouseAdapter );
	        addMouseWheelListener(mouseAdapter);
	        addMouseMotionListener ( mouseAdapter );
	        
	}
	
	@Override
	public void initGL() {
		// TODO Auto-generated method stub
        //System.out.println("OpenGL version: " + effective.majorVersion + "." + effective.minorVersion + " (Profile: " + effective.profile + ")");
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
        // Enable depth testing
        glEnable(GL_DEPTH_TEST);
        glLineWidth(1.4f);

        // Remember the current time.
        lastTime = System.nanoTime();

        tmp = new Vector3f();
        mat = new Matrix4f();
        // FloatBuffer for transferring matrices to OpenGL
        fb = BufferUtils.createFloatBuffer(16);
        gllightInit();
        //loadBunny();
        //test_bunny = new ShapeObjFile(MODEL_BUNNY,true,false);
        //MitsihaObject = new ShapeObjFile(MODEL_MITSIHA,false,false);
	}

	@Override
	public void paintGL() {
		// TODO Auto-generated method stub
		width = getWidth();
		height = getHeight();
		fbWidth = width;
		fbHeight = height;
        // Update camera input
        cam.linearAcc.zero();
        accFactor = 6.0f;
        rotateZ = 0.0f;
        cam.position.set(PosX, PosY, PosZ);
        
        //cam.rotation.rotateXYZ(mouseX,mouseY,rotateZ);
        
        //cam.linearAcc.fma(accFactor, new Vector3f(PosX, PosY, PosZ));
        
        //cam.rotation.set(mouseX, mouseY, rotateZ);
        //cam.angularVel.set(0, 1, 0);
        //cam.angularVel.set(mouseX, mouseY, rotateZ);
        cam.angularVel.set(mouseX, mouseY, rotateZ);
        /* Compute delta time */
        long thisTime = System.nanoTime();
        float diff = (float) ((thisTime - lastTime) / 1E9);
        lastTime = thisTime;
        /* And let the camera make its update */
        cam.update(diff);
        TestJFrame.showThePos.setText("CamPos( x, y, z)=(" + cam.position.x+" , "+cam.position.y+" , "+cam.position.z+")" +
        								"Entity count = " + MyWorld.getEntitys().size());
        move();
        glViewport(0, 0, fbWidth, fbHeight);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glMatrixMode(GL_PROJECTION);
        glLoadMatrixf(mat.setPerspective((float) Math.toRadians(45), (float) width / height, 0.01f, 100.0f).get(fb));
        
        glPushMatrix();
	    	glLoadIdentity();
	    	/*----position light1 (fixed) in eye coord sys ---*/
	    	glLightfv(GL_LIGHT1, GL_POSITION, lit1_position);  /*fixed position---*/
	    	/*---draw the light source ---*/
	    	glTranslatef(lit1_position[0], lit1_position[1], lit1_position[2]);
	    	glDisable(GL_LIGHTING);
	    	glDisable(GL_CULL_FACE);
	    	glColor3f(1.0f, 1.0f, 0.0f);
	    	//glutSolidSphere(0.3, 12, 12);
    	glPopMatrix();
    	
    	glEnable(GL_CULL_FACE);
        /*
         * Obtain the camera's view matrix
         */
        glMatrixMode(GL_MODELVIEW);
        glLoadMatrixf(cam.apply(mat.identity()).get(fb));

        glPushMatrix();
	    	//glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
	    
	    	glTranslatef(0.0f, 0.0f, 0.0f);          /* Move to [8, 0, 8] */
	    	glRotatef(90f, 0.0f, 1.0f, 0.0f);  /* Rotate about y-axis */
	    
	    	glEnable(GL_LIGHTING);      /* Turn on lighting */
	    	/*----Redefine position and direction of light0-----*/
	    	glLightfv(GL_LIGHT0, GL_POSITION, lit_position);
	    	glLightfv(GL_LIGHT0, GL_SPOT_DIRECTION, lit_direction);
    	glPopMatrix();
        renderGrid();
       // System.out.println("Something before");
        
        
       //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
       glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
       
       
       
       /*for( Entity iterator : MyWorld.getEntitys() ){
        	if(iterator==null)continue;
    	    iterator.Update();
        	iterator.getRender().renderer();
        }
        */
      2323232 for( int i=0;i<MyWorld.getEntitys().size();i++ ){
       	MyWorld.getEntitys().get(i).Update();
       	MyWorld.getEntitys().get(i).getRender().renderer();
       }
       
        glPushMatrix();
        
        TestJFrame.showThePos.setText(TestJFrame.showThePos.getText() + " Ang=" + String.format("%.3f",MyChopperEntity.getBodyAngle()));
    	/*
    	// no light now.
    	glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
    	glMaterialf(GL_FRONT, GL_SHININESS, mat_shininess);
    	*/
    	glTranslatef(0.0f, 6.0f, 0.0f);
    	glScalef(0.5f, 0.5f, 0.5f);
    	glRotatef(10, 0.0f, 1.0f, 0.0f);
    	//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    	//glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    	//glCallList(BunnyObject);
    	//test_bunny.render();
    	
    	glPopMatrix();
    	glPushMatrix();
    		glScalef(0.5f,0.5f,0.5f);
    		//MitsihaObject.render();
    	glPopMatrix();
       // System.out.println("Something after");
        swapBuffers();
	}
	
	private void move()
	{
		if(up){
			MyChopperEntity.moveEntity(0, 0.087f, 0);
		}
		if(down){
			MyChopperEntity.moveEntity(0, -0.087f, 0);
		}
		
		if(left){
			MyChopperEntity.moveEntity(-0.087f, 0, 0);
			if(MyChopperEntity.getBodyAngle()>-5.0f) 
			{
				MyChopperEntity.setBodyAngle(MyChopperEntity.getBodyAngle()-0.1f);
			}
		}
		if(right){
			MyChopperEntity.moveEntity(0.087f, 0, 0);
			if(MyChopperEntity.getBodyAngle()<5.0f)
			{
				MyChopperEntity.setBodyAngle(MyChopperEntity.getBodyAngle()+0.1f);
			}
		}
	}
    
    void renderGrid() {
        glBegin(GL_LINES);
        glColor3f(0.2f, 0.2f, 0.2f);
        for (int i = -20; i <= 20; i++) {
            glVertex3f(-20.0f, 0.0f, i);
            glVertex3f(20.0f, 0.0f, i);
            glVertex3f(i, 0.0f, -20.0f);
            glVertex3f(i, 0.0f, 20.0f);
        }
        glEnd();
    }
    private final OpenGLFrame instance;
    
    public Runnable renderLoop = new Runnable() {
		public void run() {
			if (!instance.isValid())
				return;
			instance.render();
			SwingUtilities.invokeLater(this);
		}
	};
	/*----Define material properties for cube -----*/
	float  mat_ambient[] = {0.3f, 0.3f, 0.3f, 1.0f};
	float  mat_diffuse[] = {0.9f, 0.9f, 0.9f, 1.0f};
	float  mat_specular[] = {0.9f, 0.9f, 0.9f, 1.0f};
	float  mat_shininess = 32.0f;
	float  flr_diffuse[] = {0.20f, 0.60f, 0.3f, 1.0f};
	float  flr_shininess = 4.0f;
	/*----Define light source properties -------*/
	float  lit_position[] = {0.0f, 25.0f, 0.0f, 1.0f};
	float  lit_direction[] = {0.0f, -1.0f, 0.0f, 0.0f};
	float  lit_diffuse[] = {0.8f, 0.8f, 0.8f, 1.0f};
	float  lit_specular[] = {0.7f, 0.7f, 0.7f, 1.0f};
	float  lit_cutoff=45.0f;
	float  lit1_position[] = {10f, 25f, 10f, 0.0f};
	float  lit1_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
	float  global_ambient[]={0.1f, 0.1f, 0.4f, 1.0f};
	void  gllightInit()
	{
	  glClear(GL_COLOR_BUFFER_BIT| GL_DEPTH_BUFFER_BIT);
	  glShadeModel(GL_SMOOTH);
	  glEnable(GL_DEPTH_TEST);  /*Enable depth buffer for shading computing */
	  glEnable(GL_NORMALIZE);   /*Enable mornalization  */
	  glEnable(GL_LIGHTING);    /*Enable lighting effects */
	  glEnable(GL_LIGHT0);      /*Turn on light0 */
	  /*-----Define light0 ---------*/
	  GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_SPOT_CUTOFF, lit_cutoff);
	  glLightfv(GL_LIGHT0, GL_DIFFUSE, lit_diffuse);
	  glLightfv(GL_LIGHT0, GL_SPECULAR, lit_specular);
	  /*-----Define light1 ---------*/
	  glLightfv(GL_LIGHT1, GL_DIFFUSE, lit1_diffuse);
	  glLightfv(GL_LIGHT1, GL_SPECULAR, lit_specular);
	  glEnable(GL_LIGHT1);      /*Turn on light1 */
	  /*-----Define some material properties shared by every one ---*/
	  glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
	  glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	  glMaterialf(GL_FRONT, GL_SHININESS, mat_shininess);
	  /*-----Define some global lighting status -----*/
	  glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE); /* local viewer */
	  glLightModelfv(GL_LIGHT_MODEL_AMBIENT, global_ambient); /*global ambient*/
	  /*-----Enable face culling -----*/
	  glCullFace(GL_BACK);
	  glEnable(GL_CULL_FACE);
	}
}
