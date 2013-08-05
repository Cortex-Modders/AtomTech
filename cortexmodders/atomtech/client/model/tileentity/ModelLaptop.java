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
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50),
                SCREEN_WIDTH,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50) + SCREEN_HEIGHT,
            },
            {
                SCREEN_WIDTH,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50),
                SCREEN_WIDTH * 2,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50) + SCREEN_HEIGHT,
            },
            {
                SCREEN_WIDTH * 2,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50),
                SCREEN_WIDTH * 3,
                RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50) + SCREEN_HEIGHT,
            },
        }
    };

    private TexturedQuad currentTexturePosition;

    private int lastState = 0;
    private int lastFrame = 0;
    
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

        int fr = 0;
        int st = 0;
        
        if(tile != null) {
            int state = tile.getState();
            if(state >= 1 && state <= 6) {
                fr = state - 2;
                st = 1;
            }
            else if(state == 7) {
//                System.out.println(state);
                st = 2;
                float powerPercent = tile.getPowerPercentage();
//                System.out.println(powerPercent);
                if(powerPercent >= (2/3) && powerPercent <= 1)
                    fr = 0;
                else if(powerPercent >= (1/3) && powerPercent <= (2/3))
                    fr = 1;
                else if(powerPercent >= 0 && powerPercent <= (1/3))
                    fr = 2;
            }

            setFaceTexture(st, fr);
        }
        
        top.render(f5);
        if(tile != null && tile.hasFlashDrive())
            flashDrive.render(f5);
        base.render(f5);
    }

    private void setFaceTexture(int parState, int parFrame) {//int state, int frame) {
        ModelBox box = (ModelBox)top.cubeList.get(0);

        System.out.println("===Start===");
        for(int ii = 0; ii < box.quadList.length; ii++) {//TexturedQuad i : box.quadList) {
            TexturedQuad i = box.quadList[ii];
            
            int counter = 0;
            for(int tt = 0; tt < i.vertexPositions.length; tt++) {//PositionTextureVertex t : i.vertexPositions) {
                PositionTextureVertex t = i.vertexPositions[tt];
                boolean b = t.texturePositionX == stateTextures[this.lastState][this.lastFrame][0] || t.texturePositionX == stateTextures[this.lastState][this.lastFrame][2];
                boolean b2 = t.texturePositionY == stateTextures[this.lastState][this.lastFrame][1] || t.texturePositionY == stateTextures[this.lastState][this.lastFrame][3];
                if(b && b2)  
                    counter++;
            }
            
            if((counter == 4)) { //|| (this.currentTexturePosition != null && (this.currentTexturePosition.equals(i)))
                System.out.println(parState + " " + parFrame);

                i.vertexPositions[0].texturePositionX = stateTextures[parState][parFrame][2];
                i.vertexPositions[0].texturePositionY = stateTextures[parState][parFrame][1];

                i.vertexPositions[1].texturePositionX = stateTextures[parState][parFrame][0];
                i.vertexPositions[1].texturePositionY = stateTextures[parState][parFrame][1];

                i.vertexPositions[2].texturePositionX = stateTextures[parState][parFrame][0];
                i.vertexPositions[2].texturePositionY = stateTextures[parState][parFrame][3];

                i.vertexPositions[3].texturePositionX = stateTextures[parState][parFrame][2];
                i.vertexPositions[3].texturePositionY = stateTextures[parState][parFrame][3];
                this.currentTexturePosition = i;
                
                box.quadList[ii] = i;
                top.cubeList.remove(0);
                top.cubeList.add(box);
                
                this.lastState = parState;
                this.lastFrame = parFrame;
            }
        }
        System.out.println("===End===");
        
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
    
    private boolean test(PositionTextureVertex f) {
        for(float[][] i : stateTextures) {
            for(float[] j : i) {
                return (f.texturePositionX == j[0] || f.texturePositionX == j[2]) && (f.texturePositionY == j[1] || f.texturePositionY == j[3]);
            }
        }
        return false;
    }
}