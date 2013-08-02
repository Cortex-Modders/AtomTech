package cortexmodders.atomtech.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModelLaptop extends ModelBase {

    ModelRenderer base;
    ModelRenderer top;

    public ModelLaptop() {
        textureWidth = 128;
        textureHeight = 64;
        setTextureOffset("base.base-left", 0, 14);
        setTextureOffset("base.base-right", 76, 14);
        setTextureOffset("base.base", 24, 14);

        top = new ModelRenderer(this, 24, 0);
        top.addBox(-7F, 0F, 0F, 14, 1, 12);
        top.setRotationPoint(0F, 0F, 6F);
        top.setTextureSize(64, 32);
        top.mirror = true;
        setRotation(top, 3.141593F, 0F, 0F);
        base = new ModelRenderer(this, "base");
        base.setRotationPoint(0F, 0F, 0F);
        setRotation(base, 0F, 0F, 0F);
        base.mirror = true;
        base.addBox("base-left", -8F, -1F, -5F, 1, 2, 11);
        base.addBox("base-right", 7F, -1F, -5F, 1, 2, 11);
        base.addBox("base", -7F, 0F, -6F, 14, 1, 12);
    }

    public void render(TileEntity entity, float lidAngleX, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(lidAngleX, f1, f2, f3, f4, f5);
        base.render(f5);
        top.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float lidAngleX, float f1, float f2, float f3, float f4, float f5) {
        top.rotateAngleX = (float) Math.toRadians(lidAngleX);
    }

}