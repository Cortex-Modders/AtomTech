package cortexmodders.atomtech.element;

import static argo.jdom.JsonNodeBuilders.aStringBuilder;

import java.util.Map;

import argo.jdom.JsonNode;
import argo.jdom.JsonStringNode;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import cortexmodders.atomtech.lib.ATLogger;
import cpw.mods.fml.common.ModMetadata.JsonStringConverter;

public class Element {

    // keep this at false unless in dev!
    private boolean debug = true;

    public String name;
    public String symbol;

    public int atomic_number = 0;
    public float atomic_weight = 0;

    /**
     * Creates new Element from Jsnon node.
     * 
     * @param parString
     * @param parNode
     */
    public Element(String parString, JsonNode parNode) {
        Map<JsonStringNode, Object> processedFields = Maps.transformValues(parNode.getFields(), new JsonStringConverter());
        name = parString;
        symbol = Strings.nullToEmpty((String) processedFields.get(aStringBuilder("symbol")));
        atomic_number = Integer.parseInt(fixString((String) processedFields.get(aStringBuilder("atomic_number"))));
        atomic_weight = Float.parseFloat(fixString((String) processedFields.get(aStringBuilder("atomic_weight"))));
        if (debug)
            ATLogger.info("New element: " + name);
    }

    /**
     * Creates new Element from scratch.
     * 
     */
    public Element() {

    }

    public void setName(String parString) {
        this.name = parString;
    }

    public void setSymbol(String parString) {
        this.symbol = parString;
    }

    public void setAtomicNumber(int parInt) {
        this.atomic_number = parInt;
    }

    public void setAtomicWeight(int parInt) {
        this.atomic_weight = parInt;
    }

    public static String fixString(String s) {
        if (s.contains("n/a"))
            return "0";
        s = s.replaceAll("\\]", "").replaceAll("\\[", "");
        return s;
    }
}
