package net.cortexmodders.atomtech.lib;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class ATLogger
{
    
    private static Logger logger = Logger.getLogger(ATProperties.ID);
    
    public static void info(final String parString)
    {
        logger.log(Level.INFO, parString);
    }
    
    public static void init()
    {
        logger.setParent(FMLLog.getLogger());
    }
    
    public static void log(final Level parLevel, final String parString)
    {
        logger.log(parLevel, parString);
    }
    
    public static void severe(final String parString)
    {
        logger.log(Level.SEVERE, parString);
    }
}
