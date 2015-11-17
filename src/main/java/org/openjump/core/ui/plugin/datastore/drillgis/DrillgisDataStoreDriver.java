package org.openjump.core.ui.plugin.datastore.drillgis;

import java.sql.*;

import com.vividsolutions.jump.datastore.*;

import com.vividsolutions.jump.parameter.ParameterList;
import com.vividsolutions.jump.parameter.ParameterListSchema;

/**
 * A driver for supplying {@link DrillgisDSConnection}s
 */
public class DrillgisDataStoreDriver
    implements DataStoreDriver
{
  public static final String DRIVER_NAME = "DrillGis";
  public static final String JDBC_CLASS = "org.apache.drill.jdbc.Driver";
  public static final String URL_PREFIX = "jdbc:drill:drillbit=";

  public static final String PARAM_Server = "Server";

  private static final String[] paramNames = new String[] {
    PARAM_Server
    };
  private static final Class[] paramClasses = new Class[]
  {
    String.class,
    };
  private final ParameterListSchema schema = new ParameterListSchema(paramNames, paramClasses);

  public DrillgisDataStoreDriver() {
  }

  public String getName()
  {
    return DRIVER_NAME;
  }
  public ParameterListSchema getParameterListSchema()
  {
    return schema;
  }
  public DataStoreConnection createConnection(ParameterList params)
      throws Exception
  {
    String host = params.getParameterString(PARAM_Server);

    String url
        = String.valueOf(new StringBuffer(URL_PREFIX).append
        (host));

    Driver driver = (Driver) Class.forName(JDBC_CLASS).newInstance();
    DriverManager.registerDriver(driver);

    // mmichaud 2013-08-27 workaround for ticket #330
    String savePreferIPv4Stack = System.getProperty("java.net.preferIPv4Stack");
    String savePreferIPv6Addresses = System.getProperty("java.net.preferIPv6Addresses");
    System.setProperty("java.net.preferIPv4Stack", "true");
    System.setProperty("java.net.preferIPv6Addresses", "false");

    Connection conn = DriverManager.getConnection(url);

    if (savePreferIPv4Stack == null) {
        System.getProperties().remove("java.net.preferIPv4Stack");
    } else {
        System.setProperty("java.net.preferIPv4Stack", savePreferIPv4Stack);
    }
    if (savePreferIPv6Addresses == null) {
        System.getProperties().remove("java.net.preferIPv6Addresses");
    } else {
        System.setProperty("java.net.preferIPv6Addresses", savePreferIPv6Addresses);
    }
    return new DrillgisDSConnection(conn);
  }
  public boolean isAdHocQuerySupported() {
      return true;
  }
}