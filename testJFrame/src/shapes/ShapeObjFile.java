package shapes;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.joml.Vector3f;

import com.owens.oobjloader.builder.Build;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.builder.FaceVertex;
import com.owens.oobjloader.builder.Material;
import com.owens.oobjloader.lwjgl.DisplayModel;
import com.owens.oobjloader.lwjgl.TextureLoader;
import com.owens.oobjloader.lwjgl.VBO;
import com.owens.oobjloader.lwjgl.VBOFactory;
import com.owens.oobjloader.parser.Parse;

import utility.Model;
import utility.OBJLoader;

public class ShapeObjFile extends Shapes {

	public ShapeObjFile(String filename, boolean byid, boolean fix){
		if(byid){
			isNonId = false;
			isFixNormal = false;
			objIndex = ShapeObjFile.Index;
			ShapeObjFile.Index++;
			loadById(filename);
		}else{
			isNonId = true;
			isFixNormal = fix;
			objIndex = -1;
			loadByNonId(filename);
		}
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		if(isNonId)scene.render();
		else glCallList(objId);
	}
	private String defaultTextureMaterial;
	private int currentTextureID;
	private DisplayModel scene;
	private void loadByNonId(String FileName) {
        scene = null;
        
        scene = new DisplayModel();
        Build builder = new Build();
        Parse obj = null;
        try {
            obj = new Parse(builder, FileName);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<Face>> facesByTextureList = createFaceListsByMaterial(builder);
       
        TextureLoader textureLoader = new TextureLoader();
        int defaultTextureID = 0;
        if (defaultTextureMaterial != null) {
            defaultTextureID = setUpDefaultTexture(textureLoader, defaultTextureMaterial);
        }
        if (defaultTextureID == -1) {
            BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
            Graphics g = img.getGraphics();
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, 256, 256);
            g.setColor(Color.RED);
            for (int loop = 0; loop < 256; loop++) {
                g.drawLine(loop, 0, loop, 255);
                g.drawLine(0, loop, 255, loop);
            }
            defaultTextureID = textureLoader.convertToTexture(img);
        }
        currentTextureID = -1;
        for (ArrayList<Face> faceList : facesByTextureList) {
            if (faceList.isEmpty()) continue;
            currentTextureID = getMaterialID(faceList.get(0).material, defaultTextureID, builder, textureLoader);
            ArrayList<Face> triangleList = splitQuads(faceList);
            if(this.isFixNormal)calcMissingVertexNormals(triangleList);

            if (triangleList.size() <= 0) continue;
            VBO vbo = VBOFactory.build(currentTextureID, triangleList);
            scene.addVBO(vbo);
        }
	}
	private void loadById(String FileName) {
		objId = glGenLists(objIndex);
        glNewList(objId, GL_COMPILE);
        {
            Model m = null;
            try {
                m = OBJLoader.loadModel(new File(FileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            glBegin(GL_TRIANGLES);
            for (Model.Face face : m.getFaces()) {
                Vector3f n1 = m.getNormals().get(face.getNormalIndices()[0] - 1);
                glNormal3f(n1.x, n1.y, n1.z);
                Vector3f v1 = m.getVertices().get(face.getVertexIndices()[0] - 1);
                glVertex3f(v1.x, v1.y, v1.z);
                Vector3f n2 = m.getNormals().get(face.getNormalIndices()[1] - 1);
                glNormal3f(n2.x, n2.y, n2.z);
                Vector3f v2 = m.getVertices().get(face.getVertexIndices()[1] - 1);
                glVertex3f(v2.x, v2.y, v2.z);
                Vector3f n3 = m.getNormals().get(face.getNormalIndices()[2] - 1);
                glNormal3f(n3.x, n3.y, n3.z);
                Vector3f v3 = m.getVertices().get(face.getVertexIndices()[2] - 1);
                glVertex3f(v3.x, v3.y, v3.z);
            }
            glEnd();
        }
        glEndList();
    }
	private final boolean isNonId;
	private final boolean isFixNormal;
	private int objId;
	private final int objIndex;
	private static int Index = 1;
	private static ArrayList<ArrayList<Face>> createFaceListsByMaterial(Build builder) {
        ArrayList<ArrayList<Face>> facesByTextureList = new ArrayList<ArrayList<Face>>();
        Material currentMaterial = null;
        ArrayList<Face> currentFaceList = new ArrayList<Face>();
        for (Face face : builder.faces) {
            if (face.material != currentMaterial) {
                if (!currentFaceList.isEmpty()) {
                    facesByTextureList.add(currentFaceList);
                }
                currentMaterial = face.material;
                currentFaceList = new ArrayList<Face>();
            }
            currentFaceList.add(face);
        }
        if (!currentFaceList.isEmpty()) {
            facesByTextureList.add(currentFaceList);
        }
        return facesByTextureList;
    }
    private static void calcMissingVertexNormals(ArrayList<Face> triangleList) {
        for (Face face : triangleList) {
            face.calculateTriangleNormal();
            for (int loopv = 0; loopv < face.vertices.size(); loopv++) {
                FaceVertex fv = face.vertices.get(loopv);
                if (face.vertices.get(0).n == null) {
                    FaceVertex newFv = new FaceVertex();
                    newFv.v = fv.v;
                    newFv.t = fv.t;
                    newFv.n = face.faceNormal;
                    face.vertices.set(loopv, newFv);
                }
            }
        }
    }
    private static int setUpDefaultTexture(TextureLoader textureLoader, String defaultTextureMaterial) {
        int defaultTextureID = -1;
        try {
            defaultTextureID = textureLoader.load(defaultTextureMaterial);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return defaultTextureID;
    }
    private static int getMaterialID(Material material, int defaultTextureID, Build builder, TextureLoader textureLoader) {
        int currentTextureID;
        if (material == null) {
            currentTextureID = defaultTextureID;
        } else if (material.mapKdFilename == null) {
            currentTextureID = defaultTextureID;
        } else {
            try {
                File objFile = new File(builder.objFilename);
                File mapKdFile = new File(objFile.getParent(), material.mapKdFilename);
                currentTextureID = textureLoader.load(mapKdFile.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
                currentTextureID = defaultTextureID;
            }
        }
        return currentTextureID;
    }
    private static ArrayList<Face> splitQuads(ArrayList<Face> faceList) {
        ArrayList<Face> triangleList = new ArrayList<Face>();
        int countTriangles = 0;
        int countQuads = 0;
        int countNGons = 0;
        for (Face face : faceList) {
            if (face.vertices.size() == 3) {
                countTriangles++;
                triangleList.add(face);
            } else if (face.vertices.size() == 4) {
                countQuads++;
                FaceVertex v1 = face.vertices.get(0);
                FaceVertex v2 = face.vertices.get(1);
                FaceVertex v3 = face.vertices.get(2);
                FaceVertex v4 = face.vertices.get(3);
                Face f1 = new Face();
                f1.map = face.map;
                f1.material = face.material;
                f1.add(v1);
                f1.add(v2);
                f1.add(v3);
                triangleList.add(f1);
                Face f2 = new Face();
                f2.map = face.map;
                f2.material = face.material;
                f2.add(v1);
                f2.add(v3);
                f2.add(v4);
                triangleList.add(f2);
            } else {
                countNGons++;
            }
        }
        int texturedCount = 0;
        int normalCount = 0;
        for (Face face : triangleList) {
            if ((face.vertices.get(0).n != null)
                && (face.vertices.get(1).n != null)
                && (face.vertices.get(2).n != null)) {
                normalCount++;
            }
            if ((face.vertices.get(0).t != null)
                && (face.vertices.get(1).t != null)
                && (face.vertices.get(2).t != null)) {
                texturedCount++;
            }
        }
        return triangleList;
    }
}
