package org.openjump.core.ui.plugin.datastore.drillgis;

import com.vividsolutions.jump.workbench.plugin.Extension;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;
/**
 * @description
 *  - this class loads the PlugIn into Jump <p>
 *  - class has to be called "Extension" on the end of classname
 *    to use the PlugIn in Jump
 * 
 *  @author potocki 
 */
public class DrillgisDatastorePlugInExtension extends Extension{

	/**
	 * calls PlugIn using class method xplugin.initialize() 
	 */
	public void configure(PlugInContext context) throws Exception{
		new DrillgisDatastorePlugIn().initialize(context);
	}	
}