package cortexmodders.atomtech.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import cortexmodders.atomtech.client.render.RenderUtil;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;

public class ModelLaptop extends ModelBase
{
    //fields
    ModelRenderer top;
    ModelRenderer flashDrive;
    ModelRenderer base;

    private static final float TEXTURE_SIZE = RenderUtil.textureToGLCoordinates(128);
    private static final float SCREEN_WIDTH = RenderUtil.toGLCoordinate(TEXTURE_SIZE, 14);
    private static final float SCREEN_HEIGHT = RenderUtil.toGLCoordinate(TEXTURE_SIZE, 12);
    private static final float SCREEN_MIN_X = 36 * (1F / 128F);
    private static final float SCREEN_MAX_X = SCREEN_MIN_X + SCREEN_WIDTH;// - (1F / 128F);
    private static final float SCREEN_MIN_Y = 0;
    private static final float SCREEN_MAX_Y = SCREEN_MIN_Y + (12 * (1F / 128F));
    
    // textures. first dimension: state. second: frame. third: frame minX, minY, maxX, maxY
    private static final float[][][] stateTextures = { 
        {
            {
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 36),
                0F,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 36) + SCREEN_WIDTH,
                SCREEN_HEIGHT,
            },
        },
        {
            {
                0F,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26),
                SCREEN_WIDTH*2,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT,
            },
            {
                SCREEN_WIDTH*2,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26),
                SCREEN_WIDTH*3,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT,
            },
            {
                SCREEN_WIDTH*3,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26),
                SCREEN_WIDTH*4,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT,
            },
            {
                SCREEN_WIDTH*4,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26),
                SCREEN_WIDTH*5,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT,
            },
            {
                SCREEN_WIDTH*5,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26),
                SCREEN_WIDTH*6,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT,
            },
            {
                0F,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT,
                SCREEN_WIDTH,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT*2,
            },
        },
        {
            {
                0F,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 62),
                SCREEN_WIDTH,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 62) + SCREEN_HEIGHT,
            },
            {
                SCREEN_WIDTH,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 62),
                SCREEN_WIDTH * 2,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 62) + SCREEN_HEIGHT,
            },
            {
                SCREEN_WIDTH * 2,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 62),
                SCREEN_WIDTH * 3,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 62) + SCREEN_HEIGHT,
            },
        }
    };
    
    private TexturedQuad currentTexturePosition;

    public ModelLaptop()
    {
        textureWidth = 128;
        textureHeight = 128;
        setTextureOffset("base.base-left", 0, 13);
        setTextureOffset("base.base-right", 0, 0);
        setTextureOffset("base.base", 24, 13);

        top = new ModelRenderer(this, 24, 0);
        top.addBox(-7F, 0F, 0F, 14, 1, 12);
        top.setRotationPoint(0F, 0F, 6F);
        top.setTextureSize(128, 128);
        top.mirror = true;
        setRotation(top, 1.396263F, 0F, 0F);
        flashDrive = new ModelRenderer(this, 76, 0);
        flashDrive.addBox(-10F, 0F, -3F, 2, 1, 1);
        flashDrive.setRotationPoint(0F, 0F, 0F);
        flashDrive.setTextureSize(128, 128);
        flashDrive.mirror = true;
        setRotation(flashDrive, 0F, 0F, 0F);
        base = new ModelRenderer(this, "base");
        base.setRotationPoint(0F, 0F, 0F);
        setRotation(base, 0F, 0F, 0F);
        base.mirror = true;
        base.addBox("base-left", -8F, -1F, -5F, 1, 2, 11);
        base.addBox("base-right", 7F, -1F, -5F, 1, 2, 11);
        base.addBox("base", -7F, 0F, -6F, 14, 1, 12);
    }

    public void render(TileEntityLaptop tile, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);

        byte state = tile.getState();
        setFaceTexture();

        top.render(f5);
        if(tile != null && tile.hasFlashDrive())
            flashDrive.render(f5);
        base.render(f5);
    }
    
    private void setFaceTexture(int state, int frame) {
        ModelBox box = (ModelBox)top.cubeList.get(0);
        for(TexturedQuad i : box.quadList) {
            int counter = 0;
            for(int j = 0; j < i.vertexPositions.length; j++) {
                PositionTextureVertex t = i.vertexPositions[j];
                boolean b = t.texturePositionX == SCREEN_MAX_X || t.texturePositionX == SCREEN_MIN_X;
                boolean b2 = t.texturePositionY == SCREEN_MAX_Y || t.texturePositionY == SCREEN_MIN_Y;
                if(i.equals(currentTexturePosition) || b && b2 )
                    counter++;
                
                if(counter == 4) {
//                    System.out.println(t.vector3D.xCoord + " " + t.vector3D.yCoord + " " + t.vector3D.zCoord + " " + j);

//                    int frame = 2;
//                    int state = 1;
 //                   if(frame > 6)
 //                       frame = 0;
                    i.vertexPositions[0].texturePositionX = stateTextures[state][frame][2];
                    i.vertexPositions[0].texturePositionY = stateTextures[state][frame][1];
                    
                    i.vertexPositions[1].texturePositionX = stateTextures[state][frame][0];
                    i.vertexPositions[1].texturePositionY = stateTextures[state][frame][1];
                    
                    i.vertexPositions[2].texturePositionX = stateTextures[state][frame][0];
                    i.vertexPositions[2].texturePositionY = stateTextures[state][frame][3];
                    
                    i.vertexPositions[3].texturePositionX = stateTextures[state][frame][2];
                    i.vertexPositions[3].texturePositionY = stateTextures[state][frame][3];
                    
                    currentTexturePosition = i;
//                    frame++;
                }
            }
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        top.rotateAngleX = (float) Math.toRadians(f);
    }
}