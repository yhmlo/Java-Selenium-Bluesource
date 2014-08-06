package com.orasi.BluesourceTest.core;
import java.sql.*;

public class DatabaseUtilities {

		//****************************
		// * Database Driver Strings *
		// ***************************

		//Type: IBM DB2
		private static final String connectionDB2= "jdbc:db2://<HOST>:<PORT>/<DB>";
		private static final String driverDB2= "COM.ibm.db2.jdbc.app.DB2Driver";
		public static final String DB2 = "DB2";

		//Type: JDBC-ODBC Bridge
		private static final String connectionJdbcOdbc="jdbc:odbc:<DB>";
		private static final String driverJdbcOdbc= "sun.jdbc.odbc.JdbcOdbcDriver";
		public static final String jdbcOdbc = "JdbcOdbc";

		//Type: Microsoft SQL Server
		private static final String connectionMsSQLServer= "jdbc:weblogic:mssqlserver4:<DB>@<HOST>:<PORT>";
		private static final String driverMsSQLServer = "weblogic.jdbc.mssqlserver4.Driver";
		public static final String msSqlServer = "MSSQLServer";

		//Type: Microsoft SQL Server (JTurbo Driver)
		private static final String connectionMsSQLServerJTurbo= "jdbc:JTurbo://<HOST>:<PORT>/<DB>";
		private static final String driverMsSQLServerJTurbo= "com.ashna.jturbo.driver.Driver";
		public static final String msSQLServerJTurbo = "MSSQLServerJTurbo";

		//Type: Microsoft SQL Server (Sprinta Driver)
		private static final String connectionMsSQLServerSprinta= "jdbc:inetdae:<HOST>:<PORT>?database=<DB>";
		private static final String driverMsSQLServerSprinta= "com.inet.tds.TdsDriver";	
		public static final String msSQLServerSprinta = "MSSQLServerSprinta";

		//Type: Microsoft SQL Server 2000 (Microsoft Driver)
		private static final String connectionMsSQLServer2000= "jdbc:microsoft:sqlserver://<HOST>:<PORT>[;DatabaseName=<DB>]";
		private static final String driverMsSQLServer2000= "com.microsoft.jdbc.sqlserver.SQLServerDriver";
		public static final String msSQLServer2000 = "MSSQLServer2000";

		//Type: MySQL (MM.MySQL Driver)
		private static final String connectionMySQL= "jdbc:mysql://<HOST>:<PORT>/<DB>";
		private static final String driverMySQL= "org.gjt.mm.mysql.Driver";
		public static final String mySQL = "MYSQL";

		//Type: Oracle OCI 8i
		private static final String connectionOracle8i= "jdbc:oracle:oci8:@<SID>";
		private static final String driverOracle8i= "oracle.jdbc.driver.OracleDriver";
		public static final String oracle8i = "Oracle8i";

		//Type: Oracle OCI 9i
		private static final String connectionOracle9i= "jdbc:oracle:oci:@<SID>";
		private static final String driverOracle9i= "oracle.jdbc.driver.OracleDriver";
		public static final String oracle9i = "Oracle9i";

		//Type: Oracle Thin SID
		private static final String connectionOracleThinSID= "jdbc:oracle:thin:@<HOST>:<PORT>:<SID>";	 
		private static final String connectionOracleThinService= "jdbc:oracle:thin:@<HOST>:<PORT>/<SERVICE>";
		private static final String driverOracleThin= "oracle.jdbc.driver.OracleDriver";
		public static final String oracleThinSID = "OracleThinSID";
		public static final String oracleThinService = "OracleThinService";

		//Type: PointBase Embedded Server
		private static final String connectionPointBase= "jdbc:pointbase://embedded[:<PORT>]/<DB>";
		private static final String driverPointBase= "com.pointbase.jdbc.jdbcUniversalDriver";
		public static final String pointBase = "PointBase";

		//Type: Cloudscape
		private static final String connectionCloudscape= "jdbc:cloudscape:<DB>";
		private static final String driverCloudscape= "COM.cloudscape.core.JDBCDriver";
		public static final String cloudscape = "Cloudscape";	

		//Type: Cloudscape RMI
		private static final String connectionCloudscapeRmi= "jdbc:rmi://<HOST>:<PORT>/jdbc:cloudscape:<DB>";
		private static final String driverCloudscapeRmi= "RmiJdbc.RJDriver";
		public static final String cloudscapeRMI = "CloudscapeRMI";

		//Type: Firebird (JCA/JDBC Driver)
		private static final String connectionFirebird= "jdbc:firebirdsql:[//<HOST>[:<PORT>]/]<DB>";
		private static final String driverFirebird= "org.firebirdsql.jdbc.FBDriver";
		public static final String firebird = "Firebird";

		 //Type: IDS Server
		private static final String connectionIdsServer= "jdbc:ids://<HOST>:<PORT>/conn?dsn='<DB>'";
		private static final String driverIdsServer= "ids.sql.IDSDriver";
		public static final String idsServer = "IDSServer";

		//Type: Informix Dynamic Server
		private static final String connectionInformixDynamic= "jdbc:informix-sqli://<HOST>:<PORT>/<DB>:INFORMIXSERVER=<DB>";
		private static final String driverInformixDynamic= "com.informix.jdbc.IfxDriver";
		public static final String informix = "Informix";

		//Type: InstantDB (v3.13 and earlier)
		private static final String connectionInstantDB_v3_13 = "jdbc:idb:<DB>";
		private static final String driverInstantDB_v3_13 = "jdbc.idbDriver";
		public static final String instantDB_v3_13 = "InstantDb_v3_13";

		//Type: InstantDB (v3.14 and later)
		private static final String connectionInstantDB_v3_14 = "jdbc:idb:<DB>";
		private static final String driverInstantDB_v3_14 = "org.enhydra.instantdb.jdbc.idbDriver";
		public static final String instantDB_v3_14 = "InstantDB_v3_14";

		//Type: Interbase (InterClient Driver)
		private static final String connectionInterbase= "jdbc:interbase://<HOST>/<DB>";
		private static final String driverInterbase= "interbase.interclient.Driver";
		public static final String interbase = "Interbase";

		//Type: Hypersonic SQL (v1.2 and earlier)
		private static final String connectionHypersonicSQL_v1_2 = "jdbc:HypersonicSQL:<DB>";
		private static final String driverHypersonicSQL_v1_2 = "hSql.hDriver";
		public static final String hypersonicSQL_v1_2 = "Hypersonic_v1_2";

		//Type: Hypersonic SQL (v1.3 and later)
		private static final String connectionHypersonicSQL_v1_3= "jdbc:HypersonicSQL:<DB>";
		private static final String driverHypersonicSQL_v1_3 = "org.hsql.jdbcDriver";
		public static final String hypersonicSQL_v1_3 = "HypersonicSQL_v1_3";

		//Type: PostgreSQL (v6.5 and earlier)
		private static final String connectionPostgreSQL_v6_5 = "jdbc:postgresql://<HOST>:<PORT>/<DB>";
		private static final String driverPostgreSQL_v6_5 = "postgresql.Driver";
		public static final String postgreSQL_v6_5 = "PostgreSQL_v6_5";

		//Type: PostgreSQL (v7.0 and later)
		private static final String connectionPostgreSQL_v7 = "jdbc:postgresql://<HOST>:<PORT>/<DB>";
		private static final String driverPostgreSQL_v7 = "org.postgresql.Driver";
		public static final String postgreSQL_v7 = "PostgreSQL_v7";

		//Type: Sybase (jConnect 4.2 and earlier)
		private static final String connectionSysbase_v4_2 = "jdbc:sybase:Tds:<HOST>:<PORT>";
		private static final String driverSysbase_v4_2 = "com.sybase.jdbc.SybDriver";
		public static final String sysbase_v4_2 = "Sysbase_v4_2";

		//Type: Sybase (jConnect 5.2)
		private static final String connectionSysbase_v5_2 = "jdbc:sybase:Tds:<HOST>:<PORT>";
		private static final String driverSysbase_v5_2 = "com.sybase.jdbc2.jdbc.SybDriver";
		public static final String sysbase_v5_2 = "Sysbase_v5_2";


		public static ResultSet getResultSet(String databaseType, String host, String port, String service, String username, String password, String query) {
		    loadDriver(databaseType);

		    Connection connection = Connect(databaseType, host, port, service, username, password);

		    return(runQuery(connection, query));
		}


		private static void loadDriver(String databaseType){			
			try{
				  Class.forName(getDriver(databaseType));
			} catch(ClassNotFoundException cnfe) {
			      System.err.println("Error loading driver: " + cnfe);
			}		 
		}


		private static Connection Connect(String databaseType, String host, String port, String service, String username, String password){	
			try{
				Connection connection = DriverManager.getConnection(getConnectionString(databaseType, host, port, service), username, password);	
				return connection;
			}catch(SQLException sqle) {
			      System.err.println("Error connecting: " + sqle);
			      return(null);	     		    
			}
		}


		private static ResultSet runQuery(Connection connection, String query) {
		    try {
		      Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,  ResultSet.HOLD_CURSORS_OVER_COMMIT);
		      ResultSet resultSet = statement.executeQuery(query);

		      return(resultSet);

		    } catch(SQLException sqle) {
		      System.err.println("Error executing query: " + sqle);
		      return(null);	     
		    } 
		}

		private static String getDriver(String db){
			switch (db.toUpperCase()){
			case "CLOUDSCAPE":
				return driverCloudscape;
			case "CLOUDSCAPE_RMI":
				return driverCloudscapeRmi;
			case "DB2":
				return driverDB2;
			case "FIREBIRD":
				return driverFirebird;
			case "HYPERSONIC_v1_2":
				return driverHypersonicSQL_v1_2;
			case "HYPERSONIC_v1_3":
				return driverHypersonicSQL_v1_3;
			case "IDSSERVER":
				return driverIdsServer;
			case "INFORMIX":
				return driverInformixDynamic;
			case "INSTANTDB_V3_13":
				return driverInstantDB_v3_13;
			case "INSTANTDB_V3_14":
				return driverInstantDB_v3_14;
			case "INTERBASE":
				return driverInterbase;
			case "JDBCODBC":
				return driverJdbcOdbc;
			case "MSSQLSERVER":
				return driverMsSQLServer;
			case "MSSQLSERVER2000":
				return driverMsSQLServer2000;
			case "MSSQLSERVERJTURBO":
				return driverMsSQLServerJTurbo;
			case "MSSQLSERVERSPRINTA":
				return driverMsSQLServerSprinta;
			case "MYSQL":
				return driverMySQL;
			case "ORACLE8I":
				return driverOracle8i;
			case "ORACLE9I":
				return driverOracle9i;
			case "ORACLETHINSID": case "ORACLETHINSERVICE":
				return driverOracleThin;
			case "POINTBASE":
				return driverPointBase;
			case "POSTGRESQL_V6_5":
				return driverPostgreSQL_v6_5;
			case "POSTGRESQL_V7":
				return driverPostgreSQL_v7;
			case "SYSBASE_V4_2":
				return driverSysbase_v4_2;
			case "SYSBASE_V5_2":
				return driverSysbase_v5_2;		
			default:
				return null;
			}					
		}

		private static String getConnectionString(String database, String host, String port, String db){
			String strConnect = null;
			switch (database.toUpperCase()){
			case "CLOUDSCAPE":
				strConnect = connectionCloudscape;
				break;
			case "CLOUDSCAPE_RMI":
				strConnect = connectionCloudscapeRmi;
				break;
			case "DB2":
				strConnect = connectionDB2;
				break;
			case "FIREBIRD":
				strConnect = connectionFirebird;
				break;
			case "HYPERSONIC_v1_2":
				strConnect = connectionHypersonicSQL_v1_2;
				break;
			case "HYPERSONIC_v1_3":
				strConnect = connectionHypersonicSQL_v1_3;
				break;
			case "IDSSERVER":
				strConnect = connectionIdsServer;
				break;
			case "INFORMIX":
				strConnect = connectionInformixDynamic;
				break;
			case "INSTANTDB_V3_13":
				strConnect = connectionInstantDB_v3_13;
				break;
			case "INSTANTDB_V3_14":
				strConnect = connectionInstantDB_v3_14;
				break;
			case "INTERBASE":
				strConnect = connectionInterbase;
				break;
			case "JDBCODBC":
				strConnect = connectionJdbcOdbc;
				break;
			case "MSSQLSERVER":
				strConnect = connectionMsSQLServer;
				break;
			case "MSSQLSERVER2000":
				strConnect = connectionMsSQLServer2000;
				break;
			case "MSSQLSERVERJTURBO":
				strConnect = connectionMsSQLServerJTurbo;
				break;
			case "MSSQLSERVERSPRINTA":
				strConnect = connectionMsSQLServerSprinta;
				break;
			case "MYSQL":
				strConnect = connectionMySQL;
				break;
			case "ORACLE8I":
				strConnect = connectionOracle8i;
				break;
			case "ORACLE9I":
				strConnect = connectionOracle9i;
				break;
			case "ORACLETHINSID": 
				strConnect = connectionOracleThinSID;
				break;
			case "ORACLETHINSERVICE":
				strConnect = connectionOracleThinService;
				break;
			case "POINTBASE":
				strConnect = connectionPointBase;
				break;
			case "POSTGRESQL_V6_5":
				strConnect = connectionPostgreSQL_v6_5;
				break;
			case "POSTGRESQL_V7":
				strConnect = connectionPostgreSQL_v7;
				break;
			case "SYSBASE_V4_2":
				strConnect = connectionSysbase_v4_2;
				break;
			case "SYSBASE_V5_2":
				strConnect = connectionSysbase_v5_2;
				break;		
			default:
				//return null;
			}		

			strConnect = strConnect.replace("<HOST>", host);
			strConnect = strConnect.replace("<PORT>", port);
			strConnect = strConnect.replace("<SERVICE>", db);
			strConnect = strConnect.replace("<DB>", db);				
			strConnect = strConnect.replace("<SID>", db);

			return strConnect;
		}
}
