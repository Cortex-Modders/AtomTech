package cortexmodders.atomtech.client.model.tileentity;

import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLaptop extends ModelBase
{
	//fields
	ModelRenderer top;
	ModelRenderer flashDrive;
	ModelRenderer base;

	public ModelLaptop()
	{
		textureWidth = 128;
		textureHeight = 64;
		setTextureOffset("base.base-left", 0, 13);
		setTextureOffset("base.base-right", 0, 0);
		setTextureOffset("base.base", 24, 13);

		top = new ModelRenderer(this, 24, 0);
		top.addBox(-7F, 0F, 0F, 14, 1, 12);
		top.setRotationPoint(0F, 0F, 6F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 1.396263F, 0F, 0F);
		flashDrive = new ModelRenderer(this, 76, 0);
		flashDrive.addBox(-10F, 0F, -3F, 2, 1, 1);
		flashDrive.setRotationPoint(0F, 0F, 0F);
		flashDrive.setTextureSize(128, 64);
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
		top.render(f5);
		if(tile.hasFlashDrive())
			flashDrive.render(f5);
		base.render(f5);
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