package net.cortexmodders.atomtech.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSmallBattery extends ModelBase
{
    
    ModelRenderer base;
    ModelRenderer contactUpperLeft;
    ModelRenderer contactUpperRight;
    ModelRenderer contactLowerRight;
    ModelRenderer contactLowerLeft;
    ModelRenderer battLeft;
    ModelRenderer battRight;
    
    public ModelSmallBattery()
    {
        this.textureWidth = 32;
        this.textureHeight = 32;
        
        this.base = new ModelRenderer(this, 0, 0);
        this.base.addBox(-4.5F, -3.5F, -2F, 9, 7, 2);
        this.base.setRotationPoint(0F, 0F, 0F);
        this.base.setTextureSize(32, 32);
        this.base.mirror = true;
        this.setRotation(this.base, -1.570796F, -1.570796F, 0F);
        this.contactUpperLeft = new ModelRenderer(this, 16, 9);
        this.contactUpperLeft.addBox(1F, -3.5F, 2.5F, 1, 2, 1);
        this.contactUpperLeft.setRotationPoint(0F, 0F, 0F);
        this.contactUpperLeft.setTextureSize(32, 32);
        this.contactUpperLeft.mirror = true;
        this.setRotation(this.contactUpperLeft, 0F, 0F, 0F);
        this.contactUpperRight = new ModelRenderer(this, 16, 9);
        this.contactUpperRight.addBox(-2F, -3.5F, 2.5F, 1, 2, 1);
        this.contactUpperRight.setRotationPoint(0F, 0F, 0F);
        this.contactUpperRight.setTextureSize(32, 32);
        this.contactUpperRight.mirror = true;
        this.setRotation(this.contactUpperRight, 0F, 0F, 0F);
        this.contactLowerRight = new ModelRenderer(this, 16, 9);
        this.contactLowerRight.addBox(-2F, -3.5F, -3.5F, 1, 2, 1);
        this.contactLowerRight.setRotationPoint(0F, 0F, 0F);
        this.contactLowerRight.setTextureSize(32, 32);
        this.contactLowerRight.mirror = true;
        this.setRotation(this.contactLowerRight, 0F, 0F, 0F);
        this.contactLowerLeft = new ModelRenderer(this, 16, 9);
        this.contactLowerLeft.addBox(1F, -3.5F, -3.5F, 1, 2, 1);
        this.contactLowerLeft.setRotationPoint(0F, 0F, 0F);
        this.contactLowerLeft.setTextureSize(32, 32);
        this.contactLowerLeft.mirror = true;
        this.setRotation(this.contactLowerLeft, 0F, 0F, 0F);
        this.battLeft = new ModelRenderer(this, 0, 9);
        this.battLeft.addBox(-3F, 0.5F, -4F, 6, 2, 2);
        this.battLeft.setRotationPoint(0F, 0F, 0F);
        this.battLeft.setTextureSize(32, 32);
        this.battLeft.mirror = true;
        this.setRotation(this.battLeft, -1.570796F, -1.570796F, 0F);
        this.battRight = new ModelRenderer(this, 0, 9);
        this.battRight.addBox(-3F, -2.5F, -4F, 6, 2, 2);
        this.battRight.setRotationPoint(0F, 0F, 0F);
        this.battRight.setTextureSize(32, 32);
        this.battRight.mirror = true;
        this.setRotation(this.battRight, -1.570796F, -1.570796F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float scale)
    {
        this.base.render(scale);
        this.contactUpperLeft.render(scale);
        this.contactUpperRight.render(scale);
        this.contactLowerRight.render(scale);
        this.contactLowerLeft.render(scale);
        this.battLeft.render(scale);
        this.battRight.render(scale);
    }
    
    public void render(float scale)
    {
        this.render(null, 0, 0, 0, 0, 0, scale);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
}
