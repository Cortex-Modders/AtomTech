package net.cortexmodders.atomtech.element;

import net.cortexmodders.atomtech.lib.ATLogger;
import argo.jdom.JsonNode;

public class Element
{
    
    public static String fixString(String s)
    {
        if (s.contains("n/a"))
            return "0";
        s = s.replaceAll("\\]", "").replaceAll("\\[", "");
        return s;
    }
    
    // keep this at false unless in dev!
    private boolean debug = true;
    public String name;
    
    public String symbol;
    public int atomic_number = 0;
    
    public float atomic_weight = 0;
    
    /**
     * Creates new Element from scratch.
     * 
     */
    public Element()
    {
        
    }
    
    /**
     * Creates new Element from Jsnon node.
     * 
     * @param parString
     * @param parNode
     */
    public Element(final String parString, final JsonNode parNode)
    {
//        Map<JsonStringNode, Object> processedFields = Maps.transformValues(parNode.getFields(), new JsonStringConverter());
        this.name = parString;
//        this.symbol = Strings.nullToEmpty((String) processedFields.get(aStringBuilder("symbol")));
//        this.atomic_number = Integer.parseInt(fixString((String) processedFields.get(aStringBuilder("atomic_number"))));
//        this.atomic_weight = Float.parseFloat(fixString((String) processedFields.get(aStringBuilder("atomic_weight"))));
        if (this.debug)
            ATLogger.info("New element: " + this.name);
    }
    
    public void setAtomicNumber(final int parInt)
    {
        this.atomic_number = parInt;
    }
    
    public void setAtomicWeight(final int parInt)
    {
        this.atomic_weight = parInt;
    }
    
    public void setName(final String parString)
    {
        this.name = parString;
    }
    
    public void setSymbol(final String parString)
    {
        this.symbol = parString;
    }
}
