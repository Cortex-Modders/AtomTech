package cortexmodders.atomtech;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.logging.Level;

import argo.jdom.JdomParser;
import argo.jdom.JsonNode;
import argo.jdom.JsonRootNode;
import argo.jdom.JsonStringNode;
import argo.saj.InvalidSyntaxException;

import com.google.common.base.Throwables;

import cortexmodders.atomtech.element.Element;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.MetadataCollection;

public class CommonProxy
{

	private static JdomParser parser = new JdomParser();
	
	public void addElementsJson() {
		InputStream is = getClass().getResourceAsStream("/cortexmodders/atomtech/elements.json");
		InputStreamReader reader = new InputStreamReader(is);
		try
		{
			JsonRootNode root = parser.parse(reader);
			if (root.hasElements())
			{
//				return parse10ModInfo(root);
			}
			else
			{
				for(Entry<JsonStringNode, JsonNode> n : root.getFields().entrySet()) {
					new Element(n.getValue());
				}
			}
		}
		catch (InvalidSyntaxException e)
		{
			System.out.println("invalid json");
		}
		catch (Exception e)
		{
			throw Throwables.propagate(e);
		}

	}
}