package test.seeyoui.kensite.modCarrier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.StringUtils;

public class ExcuteSql {

	public static Connection oracleConn(Upgrade upgrade) {
		Connection c = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			c = DriverManager.getConnection(upgrade.getTargetJdbcUrl(),
					upgrade.getTargetJdbcUserName(),
					upgrade.getTargetJdbcPassWord());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static void excuteSql(Upgrade upgrade) {
		Connection conn = oracleConn(upgrade);
		String fileName = "src/test/resources/carrier/upgrade/create.oracle.sql";
		FileReader fr;
		try {
			Statement stmt = conn.createStatement();
			fr = new FileReader(fileName);
			BufferedReader bf = new BufferedReader(fr);
			String line = null;
			while ((line = bf.readLine())   !=   null) {
				if(StringUtils.isBlank(line)) {
					continue;
				}
				if(line.endsWith(";")) {
					line = line.substring(0, line.length()-1);
				}
				System.out.println(line);
				stmt.execute(line);
				System.out.println("=============");
			}
			bf.close();
			fr.close();
			DBUtils.close(conn, stmt, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
