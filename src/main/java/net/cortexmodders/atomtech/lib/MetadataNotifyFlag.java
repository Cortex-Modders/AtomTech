package net.cortexmodders.atomtech.lib;


public enum MetadataNotifyFlag
{
    
    BLOCK_UPDATE(1),
    SEND_CLIENT_UPDATE(2),
    PREVENT_RERENDER(4);
    
    private int flag;
    MetadataNotifyFlag(int parFlag)
    {
        this.flag = parFlag;
    }
    
    public static int getFlag(MetadataNotifyFlag flag)
    {
        return flag.flag;
    }
    
}
