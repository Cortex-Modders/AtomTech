package net.cortexmodders.atomtech.client.model.tileentity;

import org.lwjgl.opengl.GL11;

import net.cortexmodders.atomtech.tileentity.TileEntityCable;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.ForgeDirection;


public class ModelCable
{
    
    IModelCustom model;
    
    public ModelCable()
    {
        model = AdvancedModelLoader.loadModel("/assets/atomtech/models/ModelCable.tcn");
    }
    
    public void render(final float scale, TileEntityCable tile)
    {
        TileEntity[] connections = tile.getConnections();
        
        int length = tile.getNumConnections();
        
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(0.5F, 1.53125F, 0.5F);
            GL11.glScalef(scale, scale, scale);
            
            if(length == 0)
            {
                renderStraight(ForgeDirection.NORTH);
            }
            else if(length == 1)
            {
                renderSingleConnection(connections);
            }
            else if(length == 2)
            {
                renderAngle(connections);
            }
            else if(length >= 3)
            {   
                renderConjunction(connections);
            }
        }
        GL11.glPopMatrix();
    }
    
    protected void renderPart(ForgeDirection direction)
    {
        model.renderPart(direction.name().toLowerCase() + "-left");
        model.renderPart(direction.name().toLowerCase() + "-right");
    }
    
    protected void renderConnector(ForgeDirection direction)
    {
        model.renderPart("connector-" + direction.name().toLowerCase());
    }
    
    protected void renderCorner(ForgeDirection direction1, ForgeDirection direction2)
    {
        model.renderPart("corner-" + direction1.name().toLowerCase() + "-" + direction2.name().toLowerCase());
    }
    
    protected void renderStraight(ForgeDirection direction)
    {
        ForgeDirection direction2 = direction.getOpposite();
        renderPart(direction);
        renderPart(direction2);
        
        renderConnector(direction.getRotation(ForgeDirection.UP));
        renderConnector(direction.getRotation(ForgeDirection.UP).getOpposite());
    }
    
    protected void renderSingleConnection(TileEntity[] connections)
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
            String dir = connection.name().toLowerCase();
            //System.out.println(dir + "-left");
            model.renderPart(dir + "-left");
            model.renderPart(dir + "-right");
        }
    }
    
    protected void renderAngle(TileEntity[] connections)
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
                renderStraight(to);
            }
            else
            {
                renderPart(to);
                renderPart(from);
                
                ForgeDirection connector1 = to.getOpposite();
                ForgeDirection connector2 = from;
                
                renderConnector(connector1);
                renderConnector(connector2);
                
                renderCorner(to, from.getOpposite());
            }
        }   
    }
    
    protected void renderConjunction(TileEntity[] connections)
    {
        model.renderPart("box");
        
        for(int i = 0; i < connections.length; i++)
        {
            if(connections[i] == null)
                continue;
            
            ForgeDirection direction = ForgeDirection.getOrientation(i);
            renderPart(direction);
        }
        
        
        
    }
}
