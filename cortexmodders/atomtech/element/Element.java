package cortexmodders.atomtech.element;

import static argo.jdom.JsonNodeBuilders.aStringBuilder;

import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import cpw.mods.fml.common.ModMetadata.JsonStringConverter;
import argo.jdom.JsonNode;
import argo.jdom.JsonStringNode;

public class Element {

	public String name;
	public String symbol;
	
	public int atomic_number;
	public int atomic_weight;
	public int mass_number;
	
	public Element(JsonNode node) {
		Map<JsonStringNode, Object> processedFields = Maps.transformValues(node.getFields(), new JsonStringConverter());
		symbol = Strings.nullToEmpty((String)processedFields.get(aStringBuilder("symbol")));
		System.out.println(symbol);
		System.out.println(node.toString());
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
	
	public void setMass(int parInt) {
		this.mass_number = parInt;
	}
	
}
