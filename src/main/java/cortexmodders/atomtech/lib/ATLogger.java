package cortexmodders.atomtech.lib;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class ATLogger {

    private static Logger logger = Logger.getLogger(ATProperties.ID);

    public static void init() {
        logger.setParent(FMLLog.getLogger());
    }

    public static void log(Level parLevel, String parString) {
        logger.log(parLevel, parString);
    }
    
    public static void info(String parString) {
        logger.log(Level.INFO, parString);
    }
    
    public static void severe(String parString) {
        logger.log(Level.SEVERE, parString);
    }
}
