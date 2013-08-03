package cortexmodders.atomtech.client.render.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cortexmodders.atomtech.client.model.tileentity.ModelLaptop;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderLaptop extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
    private final int renderId;

    private ModelLaptop model;

    private static final ResourceLocation texture = new ResourceLocation("atomtech", "textures/models/laptop.png");
    private static final ResourceLocation broken_texture = new ResourceLocation("atomtech", "textures/models/laptop_broken.png");
    
    public RenderLaptop(int id) {
        renderId = id;
        model = new ModelLaptop();
    }

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
        TileEntityLaptop laptop;
        if(entity instanceof TileEntityLaptop)
            laptop = (TileEntityLaptop)entity;
        else
            return;

        ResourceLocation currentTexture = texture;
        if(laptop.isBroken())
            currentTexture = broken_texture;

        //Binds the texture
        this.func_110628_a(currentTexture);

        GL11.glPushMatrix();

        short rotate = 0;
        int i = laptop.getBlockMetadata();
        switch(i) {
        case 0: rotate = 0; break;
        case 1: rotate = 90; break;
        case 2: rotate = 180; break;
        case 3: rotate = -90; break;
        }

        // Move to corrent place.
        GL11.glTranslatef((float)x, (float)y, (float)z);
        // Move to center of block.
        GL11.glTranslatef(0.5F, 0.0625F, 0.5F);
        // Rotate because of Techne dumbness.
        GL11.glRotatef(180F, 0F, 0F, 1F);
        // Rotate based on block metadata.
        GL11.glRotatef(rotate, 0.0F, 1.0F, 0.0F);

        model.render(laptop, laptop.lidAngleX, 0f, 0f, 0f, 0f, 0.0625F);

        MovingObjectPosition position = Minecraft.getMinecraft().objectMouseOver;
        int j = 0;
        Vec3 positionOnBlock = null;
        if(position != null) {
            j = entity.worldObj.getBlockId(position.blockX, position.blockY, position.blockZ);
            positionOnBlock = Vec3.createVectorHelper(position.hitVec.xCoord - position.blockX, position.hitVec.yCoord - position.blockY, position.hitVec.zCoord - position.blockZ);
            if(positionOnBlock.xCoord == 1.0 &&
               positionOnBlock.yCoord <= 0.125 &&
               positionOnBlock.zCoord >= 0.3125 && positionOnBlock.zCoord <= 0.375) {
                System.out.println(positionOnBlock.xCoord + " " + positionOnBlock.yCoord + " " + positionOnBlock.zCoord);
        
        
//        if(j == entity.blockType.blockID &&)
        /*
        if(position != null
                && j == entity.blockType.blockID
                && laptop. ) {
                */
        
            //render wireframe
            GL11.glPushMatrix();
            GL11.glPolygonMode( GL11.GL_FRONT, GL11.GL_LINE );
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
            GL11.glLineWidth(2.0F);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(false);

            GL11.glTranslatef(-0.625F, 0.0F, -0.1875F);

            float f1 = 0.0635F;

            GL11.glBegin(GL11.GL_QUADS);
                // right       
                GL11.glVertex3f(0.0f, f1, f1);
                GL11.glVertex3f(0.0f, 0.0F, f1);
                GL11.glVertex3f(0.0F, 0.0F, 0.0F);
                GL11.glVertex3f(0.0f, f1, 0.0F);

                // left
                GL11.glVertex3f(f1*2, f1, f1);
                GL11.glVertex3f(f1*2, 0.0F, f1);
                GL11.glVertex3f(f1*2, 0.0F, 0.0F);
                GL11.glVertex3f(f1*2, f1, 0.0F);

                // front
                GL11.glVertex3f(f1*2, f1, 0.0f);
                GL11.glVertex3f(f1*2, 0.0F, 0.0f);
                GL11.glVertex3f(0.0F, 0.0F, 0.0F);
                GL11.glVertex3f(0.0F, f1, 0.0F);

                // back
                GL11.glVertex3f(f1*2, f1, f1);
                GL11.glVertex3f(f1*2, 0.0F, f1);
                GL11.glVertex3f(0.0F, 0.0F, f1);
                GL11.glVertex3f(0.0F, f1, f1);

                // top
                GL11.glVertex3f(f1*2, f1, 0.0f);
                GL11.glVertex3f(f1*2, f1, f1);
                GL11.glVertex3f(0.0F, f1, f1);
                GL11.glVertex3f(0.0F, f1, 0.0F);


                // bottom
                GL11.glVertex3f(f1*2, 0.0F, 0.0f);
                GL11.glVertex3f(f1*2, 0.0F, f1);
                GL11.glVertex3f(0.0F, 0.0F, f1);
                GL11.glVertex3f(0.0F, 0.0F, 0.0F);
            GL11.glEnd();

            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPolygonMode( GL11.GL_FRONT, GL11.GL_FILL );
            GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        // TODO: ADD INVENTORY RENDER!
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {
        return false;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }

}
