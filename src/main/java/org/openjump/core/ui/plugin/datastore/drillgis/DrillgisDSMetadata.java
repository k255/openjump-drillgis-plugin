package org.openjump.core.ui.plugin.datastore.drillgis;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jump.datastore.DataStoreMetadata;
import com.vividsolutions.jump.datastore.GeometryColumn;
import com.vividsolutions.jump.datastore.PrimaryKeyColumn;
import com.vividsolutions.jump.datastore.SpatialReferenceSystemID;
import com.vividsolutions.jump.datastore.jdbc.JDBCUtil;
import com.vividsolutions.jump.datastore.jdbc.ResultSetBlock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DrillgisDSMetadata implements DataStoreMetadata {

	public DrillgisDSMetadata(DrillgisDSConnection drillgisDSConnection) {
	}

	public String[] getDatasetNames() {
		// TODO Auto-generated method stub
		return new String[]{"CA-cities.csv"};
	}

	public List<GeometryColumn> getGeometryAttributes(String datasetName) {
		final List<GeometryColumn> geometryAttributes = new ArrayList<GeometryColumn>();
		geometryAttributes.add(new GeometryColumn("geom", 4326)) ;
		return geometryAttributes;
	}

	public List<PrimaryKeyColumn> getPrimaryKeyColumns(String datasetName) {
		final List<PrimaryKeyColumn> identifierColumns = new ArrayList<PrimaryKeyColumn>();
		identifierColumns.add(new PrimaryKeyColumn("id"));
		return identifierColumns;
	}

	public Envelope getExtents(String datasetName, String attributeName) {
		return new Envelope(45.1, 13.2, 44.1, 11.1);
	}

}