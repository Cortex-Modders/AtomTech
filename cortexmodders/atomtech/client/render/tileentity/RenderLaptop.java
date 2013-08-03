package cortexmodders.atomtech.client.render.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cortexmodders.atomtech.CommonProxy;
import cortexmodders.atomtech.client.model.tileentity.ModelLaptop;
import cortexmodders.atomtech.client.render.RenderUtil;
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

        MovingObjectPosition hitPosition = Minecraft.getMinecraft().objectMouseOver;
        if(hitPosition != null)
        {
        	int id = entity.worldObj.getBlockId(hitPosition.blockX, hitPosition.blockY, hitPosition.blockZ);
        	Vec3 p = Vec3.createVectorHelper(hitPosition.hitVec.xCoord - hitPosition.blockX, hitPosition.hitVec.yCoord - hitPosition.blockY, hitPosition.hitVec.zCoord - hitPosition.blockZ);
        	
        	if(id == entity.blockType.blockID && CommonProxy.isVecInsideBB(p, TileEntityLaptop.getBoundingBox())) {
        		//render wireframe
        		GL11.glPushMatrix();
        		
        		GL11.glTranslatef(-0.625F, 0.0F, -0.1875F);
        		
        		float f1 = 0.0635F;
        		AxisAlignedBB frame = AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, f1*2, f1, f1);
        		RenderUtil.renderWireframe(frame);
        		
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
