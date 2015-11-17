package org.openjump.core.ui.plugin.datastore.drillgis;

import com.vividsolutions.jump.datastore.DataStoreDriver;
import com.vividsolutions.jump.workbench.plugin.AbstractPlugIn;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;
import java.sql.Driver;
/**
 * @author potocki
 */
public class DrillgisDatastorePlugIn extends AbstractPlugIn {

    public DrillgisDatastorePlugIn() {
    }

    public void initialize(PlugInContext context) throws Exception {
        try {
            context.getWorkbenchContext().getRegistry().createEntry(DataStoreDriver.REGISTRY_CLASSIFICATION,
                new DrillgisDataStoreDriver());
            System.out.println("Drillgis Data Store added");
        } catch (Exception e) {
            System.out.println("drillgis driver not found: " + e.toString() + ". Drillgis Data Store NOT added");
        }

    }
}
