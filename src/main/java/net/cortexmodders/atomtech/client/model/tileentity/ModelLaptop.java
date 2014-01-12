package net.cortexmodders.atomtech.client.model.tileentity;

import net.cortexmodders.atomtech.client.render.RenderUtil;
import net.cortexmodders.atomtech.tileentity.TileEntityLaptop;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLaptop extends ModelBase
{
    
    // fields
    ModelRenderer top;
    ModelRenderer flashDrive;
    ModelRenderer base;
    
    private static final float TEXTURE_SIZE = RenderUtil.textureToGLCoordinates(128);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 // 0.0078125
    private static final float SCREEN_WIDTH = RenderUtil.toGLCoordinate(TEXTURE_SIZE, 14);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            // 0.109375
    private static final float SCREEN_HEIGHT = RenderUtil.toGLCoordinate(TEXTURE_SIZE, 12);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            // 0.09375
    // textures. first dimension: state. second: frame. third: frame minX, minY,
    // maxX, maxY
    private static final float[][][] stateTextures = { { { RenderUtil.toGLCoordinate(TEXTURE_SIZE, 36), // 0.28125
    0F, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 36) + SCREEN_WIDTH, // 0.390625
    SCREEN_HEIGHT, // 0.09375
    }, }, { { 0F, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26), SCREEN_WIDTH * 2, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT, }, { SCREEN_WIDTH * 2, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26), SCREEN_WIDTH * 3, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT, }, { SCREEN_WIDTH * 3, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26), SCREEN_WIDTH * 4, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT, }, { SCREEN_WIDTH * 4, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26), SCREEN_WIDTH * 5, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT, }, { SCREEN_WIDTH * 5, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26), SCREEN_WIDTH * 6, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT, }, { 0F, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT, SCREEN_WIDTH, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 26) + SCREEN_HEIGHT * 2, }, }, { { 0F, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50), SCREEN_WIDTH, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50) + SCREEN_HEIGHT, }, { SCREEN_WIDTH, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50), SCREEN_WIDTH * 2, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50) + SCREEN_HEIGHT, }, { SCREEN_WIDTH * 2, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50), SCREEN_WIDTH * 3, RenderUtil.toGLCoordinate(TEXTURE_SIZE, 50) + SCREEN_HEIGHT, }, } };
    
    public ModelLaptop()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.setTextureOffset("base.base-left", 0, 13);
        this.setTextureOffset("base.base-right", 0, 0);
        this.setTextureOffset("base.base", 24, 13);
        
        this.top = new ModelRenderer(this, 24, 0);
        this.top.addBox(-7F, 0F, 0F, 14, 1, 12);
        this.top.setRotationPoint(0F, 0F, 6F);
        this.top.setTextureSize(128, 128);
        this.top.mirror = true;
        this.setRotation(this.top, 1.396263F, 0F, 0F);
        this.flashDrive = new ModelRenderer(this, 76, 0);
        this.flashDrive.addBox(-10F, 0F, -3F, 2, 1, 1);
        this.flashDrive.setRotationPoint(0F, 0F, 0F);
        this.flashDrive.setTextureSize(128, 128);
        this.flashDrive.mirror = true;
        this.setRotation(this.flashDrive, 0F, 0F, 0F);
        this.base = new ModelRenderer(this, "base");
        this.base.setRotationPoint(0F, 0F, 0F);
        this.setRotation(this.base, 0F, 0F, 0F);
        this.base.mirror = true;
        this.base.addBox("base-left", -8F, -1F, -5F, 1, 2, 11);
        this.base.addBox("base-right", 7F, -1F, -5F, 1, 2, 11);
        this.base.addBox("base", -7F, 0F, -6F, 14, 1, 12);
    }
    
    public void render(final TileEntityLaptop tile, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        
        if (tile != null)
        {
            int state = tile.getState();
            if (state >= 1 && state <= 6)
            {
            }
            else if (state == 7)
            {
                float powerPercent = tile.getPowerPercentage();
                // System.out.println(powerPercent);
                if (powerPercent >= 2 / 3 && powerPercent <= 1)
                {
                }
                else if (powerPercent >= 1 / 3 && powerPercent <= 2 / 3)
                {
                }
                else if (powerPercent >= 0 && powerPercent <= 1 / 3)
                {
                }
            }
            
            this.setFaceTexture(2, 0);
        }
        
        this.top.render(f5);
        if (tile != null && tile.hasFlashDrive())
            this.flashDrive.render(f5);
        this.base.render(f5);
    }
    
    private void setFaceTexture(final int parState, final int parFrame)
    {
        // ModelBox box = (ModelBox)top.cubeList.get(0);
        //
        // // System.out.println("===Start===");
        // //loops through the quads
        // for(int quadIndex = 0; quadIndex < box.quadList.length; quadIndex++)
        // {//TexturedQuad i : box.quadList) {
        // TexturedQuad i = box.quadList[quadIndex];
        //
        // int counter = 0;
        // //loops throught the quad vertexes.
        // for(int vertexIndex = 0; vertexIndex < i.vertexPositions.length;
        // vertexIndex++) {//PositionTextureVertex t : i.vertexPositions) {
        // PositionTextureVertex vertex = i.vertexPositions[vertexIndex];
        // //minX and maxX
        // boolean b = vertex.texturePositionX ==
        // stateTextures[this.lastState][this.lastFrame][0] ||
        // vertex.texturePositionX ==
        // stateTextures[this.lastState][this.lastFrame][2];
        // //minY and maxY
        // boolean b2 = vertex.texturePositionY ==
        // stateTextures[this.lastState][this.lastFrame][1] ||
        // vertex.texturePositionY ==
        // stateTextures[this.lastState][this.lastFrame][3];
        // if(b && b2)
        // counter++;
        // }
        //
        // if((counter == 4)) { //|| (this.currentTexturePosition != null &&
        // (this.currentTexturePosition.equals(i)))
        // // System.out.println(parState + " " + parFrame);
        // System.out.println("-S-");
        // //max, min, min, max
        // System.out.println( i.vertexPositions[0].texturePositionX + " " +
        // i.vertexPositions[1].texturePositionX + " " +
        // i.vertexPositions[2].texturePositionX + " " +
        // i.vertexPositions[3].texturePositionX);
        // //max, min, min, max
        // System.out.println( i.vertexPositions[0].texturePositionY + " " +
        // i.vertexPositions[1].texturePositionY + " " +
        // i.vertexPositions[2].texturePositionY + " " +
        // i.vertexPositions[3].texturePositionY);
        // System.out.println("-E-");
        // ((ModelBox)top.cubeList.get(0)).quadList[quadIndex].vertexPositions[0].texturePositionX
        // = stateTextures[parState][parFrame][2];
        // ((ModelBox)top.cubeList.get(0)).quadList[quadIndex].vertexPositions[0].texturePositionY
        // = stateTextures[parState][parFrame][1];
        //
        // ((ModelBox)top.cubeList.get(0)).quadList[quadIndex].vertexPositions[1].texturePositionX
        // = stateTextures[parState][parFrame][0];
        // ((ModelBox)top.cubeList.get(0)).quadList[quadIndex].vertexPositions[1].texturePositionY
        // = stateTextures[parState][parFrame][1];
        //
        // ((ModelBox)top.cubeList.get(0)).quadList[quadIndex].vertexPositions[2].texturePositionX
        // = stateTextures[parState][parFrame][0];
        // ((ModelBox)top.cubeList.get(0)).quadList[quadIndex].vertexPositions[2].texturePositionY
        // = stateTextures[parState][parFrame][3];
        //
        // ((ModelBox)top.cubeList.get(0)).quadList[quadIndex].vertexPositions[3].texturePositionX
        // = stateTextures[parState][parFrame][2];
        // ((ModelBox)top.cubeList.get(0)).quadList[quadIndex].vertexPositions[3].texturePositionY
        // = stateTextures[parState][parFrame][3];
        // this.currentTexturePosition = i;
        //
        // // box.quadList[quadIndex] = i;
        // // top.cubeList.remove(0);
        // // top.cubeList.add(box);
        //
        // this.lastState = parState;
        // this.lastFrame = parFrame;
        // }
        // }
        // System.out.println("===End===");
        
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.top.rotateAngleX = (float) Math.toRadians(f);
    }
}
