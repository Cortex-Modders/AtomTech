package net.cortexmodders.atomtech.client.model.tileentity;

import java.util.HashMap;

import net.cortexmodders.atomtech.lib.ATLogger;
import net.cortexmodders.atomtech.tileentity.TileEntityCable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;


public class ModelCable extends ModelBase
{
    
    private HashMap<String, ModelRenderer> parts;
    
    public ModelCable()
    {
        textureWidth = 32;
        textureHeight = 32;
        
        parts = new HashMap<String, ModelRenderer>();
        
        ModelRenderer south_left = new ModelRenderer(this, 0, 2);
        south_left.addBox(-8F, -1F, -1.5F, 7, 1, 1);
        south_left.setRotationPoint(0F, 0F, 0F);
        south_left.setTextureSize(64, 32);
        south_left.mirror = true;
        setRotation(south_left, 0F, 1.570796F, 0F);
        
        parts.put("south-left", south_left);
        
        ModelRenderer south_right = new ModelRenderer(this, 0, 0);
        south_right.addBox(-8F, -1F, 0.5F, 7, 1, 1);
        south_right.setRotationPoint(0F, 0F, 0F);
        south_right.setTextureSize(64, 32);
        south_right.mirror = true;
        setRotation(south_right, 0F, 1.570796F, 0F);
        
        parts.put("south-right", south_right);
        
        ModelRenderer north_left = new ModelRenderer(this, 0, 2);
        north_left.addBox(1F, -1F, -1.5F, 7, 1, 1);
        north_left.setRotationPoint(0F, 0F, 0F);
        north_left.setTextureSize(64, 32);
        north_left.mirror = true;
        setRotation(north_left, 0F, 1.570796F, 0F);
        
        parts.put("north-left", north_left);
        
        ModelRenderer north_right = new ModelRenderer(this, 0, 0);
        north_right.addBox(1F, -1F, 0.5F, 7, 1, 1);
        north_right.setRotationPoint(0F, 0F, 0F);
        north_right.setTextureSize(64, 32);
        north_right.mirror = true;
        setRotation(north_right, 0F, 1.570796F, 0F);
        
        parts.put("north-right", north_right);
        
        ModelRenderer east_left = new ModelRenderer(this, 0, 0);
        east_left.addBox(-8F, -1F, -1.5F, 7, 1, 1);
        east_left.setRotationPoint(0F, 0F, 0F);
        east_left.setTextureSize(64, 32);
        east_left.mirror = true;
        setRotation(east_left, 0F, 0F, 0F);
        
        parts.put("east-left", east_left);
        
        ModelRenderer east_right = new ModelRenderer(this, 0, 2);
        east_right.addBox(-8F, -1F, 0.5F, 7, 1, 1);
        east_right.setRotationPoint(0F, 0F, 0F);
        east_right.setTextureSize(64, 32);
        east_right.mirror = true;
        setRotation(east_right, 0F, 0F, 0F);
        
        parts.put("east-right", east_right);
        
        ModelRenderer west_left = new ModelRenderer(this, 0, 0);
        west_left.addBox(1F, -1F, -1.5F, 7, 1, 1);
        west_left.setRotationPoint(0F, 0F, 0F);
        west_left.setTextureSize(64, 32);
        west_left.mirror = true;
        setRotation(west_left, 0F, 0F, 0F);
        
        parts.put("west-left", west_left);
        
        ModelRenderer west_right = new ModelRenderer(this, 0, 2);
        west_right.addBox(1F, -1F, 0.5F, 7, 1, 1);
        west_right.setRotationPoint(0F, 0F, 0F);
        west_right.setTextureSize(64, 32);
        west_right.mirror = true;
        setRotation(west_right, 0F, 0F, 0F);
        
        parts.put("west-right", west_right);
        
        ModelRenderer corner_south_west = new ModelRenderer(this, 0, 0);
        corner_south_west.addBox(0.5F, -1F, 0.5F, 1, 1, 1);
        corner_south_west.setRotationPoint(0F, 0F, 0F);
        corner_south_west.setTextureSize(32, 32);
        corner_south_west.mirror = true;
        setRotation(corner_south_west, 0F, 0F, 0F);
        
        parts.put("corner-south-west", corner_south_west);
        
        ModelRenderer corner_south_east = new ModelRenderer(this, 0, 2);
        corner_south_east.addBox(-1.5F, -1F, 0.5F, 1, 1, 1);
        corner_south_east.setRotationPoint(0F, 0F, 0F);
        corner_south_east.setTextureSize(32, 32);
        corner_south_east.mirror = true;
        setRotation(corner_south_east, 0F, 0F, 0F);
        
        parts.put("corner-south-east", corner_south_east);
        
        ModelRenderer corner_north_west = new ModelRenderer(this, 0, 0);
        corner_north_west.addBox(0.5F, -1F, -1.5F, 1, 1, 1);
        corner_north_west.setRotationPoint(0F, 0F, 0F);
        corner_north_west.setTextureSize(32, 32);
        corner_north_west.mirror = true;
        setRotation(corner_north_west, 0F, 0F, 0F);
        
        parts.put("corner-north-west", corner_north_west);
        
        ModelRenderer corner_north_east = new ModelRenderer(this, 0, 0);
        corner_north_east.addBox(-1.5F, -1F, -1.5F, 1, 1, 1);
        corner_north_east.setRotationPoint(0F, 0F, 0F);
        corner_north_east.setTextureSize(32, 32);
        corner_north_east.mirror = true;
        setRotation(corner_north_east, 0F, 0F, 0F);
        
        parts.put("corner-north-east", corner_north_east);
        
        ModelRenderer connector_south = new ModelRenderer(this, 0, 2);
        connector_south.addBox(-1.5F, -1F, 0.5F, 3, 1, 1);
        connector_south.setRotationPoint(0F, 0F, 0F);
        connector_south.setTextureSize(32, 32);
        connector_south.mirror = true;
        setRotation(connector_south, 0F, 0F, 0F);
        
        parts.put("connector-south", connector_south);
        
        ModelRenderer connector_north = new ModelRenderer(this, 0, 0);
        connector_north.addBox(-1.5F, -1F, -1.5F, 3, 1, 1);
        connector_north.setRotationPoint(0F, 0F, 0F);
        connector_north.setTextureSize(32, 32);
        connector_north.mirror = true;
        setRotation(connector_north, 0F, 0F, 0F);
        
        parts.put("connector-north", connector_north);
        
        ModelRenderer connector_west = new ModelRenderer(this, 0, 0);
        connector_west.addBox(-1.5F, -1F, 0.5F, 3, 1, 1);
        connector_west.setRotationPoint(0F, 0F, 0F);
        connector_west.setTextureSize(32, 32);
        connector_west.mirror = true;
        setRotation(connector_west, 0F, 1.570796F, 0F);
        
        parts.put("connector-west", connector_west);
        
        ModelRenderer connector_east = new ModelRenderer(this, 0, 2);
        connector_east.addBox(-1.5F, -1F, -1.5F, 3, 1, 1);
        connector_east.setRotationPoint(0F, 0F, 0F);
        connector_east.setTextureSize(32, 32);
        connector_east.mirror = true;
        setRotation(connector_east, 0F, 1.570796F, 0F);
        
        parts.put("connector-east", connector_east);
        
        ModelRenderer crossover_south = new ModelRenderer(this, 0, 0);
        crossover_south.addBox(-2.5F, -2F, -1.5F, 3, 1, 1);
        crossover_south.setRotationPoint(0F, 0F, 0F);
        crossover_south.setTextureSize(32, 32);
        crossover_south.mirror = true;
        setRotation(crossover_south, 0F, 0F, 0F);
        
        parts.put("crossover-south", crossover_south);
        
        ModelRenderer crossover_north = new ModelRenderer(this, 0, 2);
        crossover_north.addBox(-0.5F, -2F, 0.5F, 3, 1, 1);
        crossover_north.setRotationPoint(0F, 0F, 0F);
        crossover_north.setTextureSize(32, 32);
        crossover_north.mirror = true;
        setRotation(crossover_north, 0F, 0F, 0F);
        
        parts.put("crossover-north", crossover_north);
        
        ModelRenderer box = new ModelRenderer(this, 0, 4);
        box.addBox(-2.5F, -2F, -2.5F, 5, 2, 5);
        box.setRotationPoint(0F, 0F, 0F);
        box.setTextureSize(32, 32);
        box.mirror = true;
        setRotation(box, 0F, 0F, 0F);
        
        parts.put("box", box);
    }
    
    public void render(TileEntityCable tile, final float scale)
    {
        TileEntity[] connections = tile.getConnections();
        
        int length = tile.getNumConnections();
        
        //GL11.glRotatef(180, -1F, 0F, 1F);
        GL11.glTranslatef(0.5F, 0F, 0.5F);
        GL11.glScalef(-1F, -1F, 1F);
        //GL11.glRotatef(180, 1F, 0F, 0F);
        
        if(length == 0)
        {
            renderStraight(ForgeDirection.NORTH, scale);
        }
        else if(length == 1)
        {
            renderSingleConnection(connections, scale);
        }
        else if(length == 2)
        {
            renderAngle(connections, scale);
        }
        else if(length >= 3)
        {   
            renderConjunction(connections, scale);
        }
        
    }
    
    protected void renderPart(String name, float scale)
    {
        ModelRenderer part = parts.get(name);
        if(part != null)
            part.render(scale);
        else
            ATLogger.severe("Cable part " + name + " was not found!");
        part = null;
    }
    
    protected void renderWire(ForgeDirection direction, float scale)
    {
        renderPart(direction.name().toLowerCase() + "-left", scale);
        renderPart(direction.name().toLowerCase() + "-right", scale);
    }
    
    protected void renderConnector(ForgeDirection direction, float scale)
    {
        renderPart("connector-" + direction.name().toLowerCase(), scale);
    }
    
    protected void renderCorner(ForgeDirection direction1, ForgeDirection direction2, float scale)
    {
        renderPart("corner-" + direction1.name().toLowerCase() + "-" + direction2.name().toLowerCase(), scale);
    }
    
    protected void renderCrossover(ForgeDirection to, ForgeDirection from, float scale)
    {
        renderPart("crossover-" + to.getOpposite().name().toLowerCase(), scale);
        renderConnector(to, scale);
        renderCorner(to, from.getOpposite(), scale);
    }
    
    protected void renderStraight(ForgeDirection direction, float scale)
    {
        renderWire(direction, scale);
        renderWire(direction.getOpposite(), scale);
        
        renderConnector(direction.getRotation(ForgeDirection.UP), scale);
        renderConnector(direction.getRotation(ForgeDirection.UP).getOpposite(), scale);
    }
    
    protected void renderSingleConnection(TileEntity[] connections, float scale)
    {
        ForgeDirection connection = ForgeDirection.UNKNOWN;
        for(int i = 0; i < connections.length; i++)
        {
            if(connections[i] == null)
                continue;
            
            ForgeDirection direction = ForgeDirection.getOrientation(i);
            connection = direction;
            break;
        }
        
        if(connection != ForgeDirection.UNKNOWN)
        {
            renderWire(connection, scale);
        }
    }
    
    protected void renderAngle(TileEntity[] connections, float scale)
    {   
        ForgeDirection to = ForgeDirection.UNKNOWN;
        ForgeDirection from = ForgeDirection.UNKNOWN;
        
        for(int i = 0; i < connections.length; i++)
        {
            if(connections[i] == null)
                continue;
            
            ForgeDirection direction = ForgeDirection.getOrientation(i);
            if(to == ForgeDirection.UNKNOWN)
                to = direction;
            else
                from = direction;
            
        }
        
        if(to != ForgeDirection.UNKNOWN && from != ForgeDirection.UNKNOWN)
        {
            if(to.getOpposite() == from)
            {
                renderStraight(to, scale);
            }
            else
            {
                renderWire(to, scale);
                renderWire(from, scale);
                
                if((to == ForgeDirection.SOUTH || to == ForgeDirection.NORTH) && from == to.getRotation(ForgeDirection.UP))
                {
                    renderCrossover(to, from, scale);
                    renderConnector(from, scale);
                }
                else
                {
                    renderConnector(to.getOpposite(), scale);
                    renderConnector(from.getOpposite(), scale);
                    
                    renderCorner(to, from, scale);
                }
            }
        }   
    }
    
    protected void renderConjunction(TileEntity[] connections, float scale)
    {
        renderPart("box", scale);
        
        for(int i = 0; i < connections.length; i++)
        {
            if(connections[i] == null)
                continue;
            
            ForgeDirection direction = ForgeDirection.getOrientation(i);
            renderWire(direction, scale);
        }
        
        
        
    }
    
    private static void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
