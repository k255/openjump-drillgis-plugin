package org.openjump.core.ui.plugin.datastore.drillgis;

import java.sql.Connection;
import java.sql.SQLException;

import com.vividsolutions.jump.I18N;
import com.vividsolutions.jump.datastore.AdhocQuery;
import com.vividsolutions.jump.datastore.DataStoreConnection;
import com.vividsolutions.jump.datastore.DataStoreException;
import com.vividsolutions.jump.datastore.DataStoreMetadata;
import com.vividsolutions.jump.datastore.Query;
import com.vividsolutions.jump.io.FeatureInputStream;

public class DrillgisDSConnection implements DataStoreConnection {

    private DrillgisDSMetadata dbMetadata;
    private Connection connection;

    public DrillgisDSConnection(Connection conn) {
        connection = conn;
        dbMetadata = new DrillgisDSMetadata(this);
    }

    public Connection getConnection() {
        return connection;
    }

    public DataStoreMetadata getMetadata() {
        return dbMetadata;
    }

    public FeatureInputStream execute(Query query) throws Exception {
    	DrillgisFeatureInputStream ifs;
    	if (query instanceof AdhocQuery) {
            String queryString = ((AdhocQuery)query).getQuery();
            ifs = new DrillgisFeatureInputStream(connection, queryString, ((AdhocQuery)query).getPrimaryKey());
            if (ifs.getFeatureSchema().getGeometryIndex() < 0) {
                throw new Exception(I18N.get(this.getClass().getName()+".resultset-must-have-a-geometry-column"));
            }
        	return ifs;
        }
        throw new IllegalArgumentException(I18N.get(this.getClass().getName()+".unsupported-query-type"));
    }


    public void close() throws DataStoreException {
        try {
            connection.close();
        }
        catch (Exception ex) { throw new DataStoreException(ex); }
    }

    public boolean isClosed() throws DataStoreException {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            throw new DataStoreException(e);
        }
    }

}