package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	
	private DBUtil(){};

		private static String mysql;
		private static String url;
		private static String user;
		private static String psw;

		static {
			try {
				Properties setting = new Properties();
				InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("DBConfig.properties");
				setting.load(in);
				mysql = setting.getProperty("driveName");
				url = setting.getProperty("url");
				user = setting.getProperty("user");
				psw = setting.getProperty("password");

				Class.forName(mysql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public static Connection getCon() {
			Connection con = null;
			try {
				con = DriverManager.getConnection(url, user, psw);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

		public static void closeAll(Connection conn, ResultSet rss, Statement stt) {
			if (rss != null) {
				{
					try {
						rss.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (stt != null) {
					try {
						stt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

		public static int update(String sql, Object[] paramList) {
			Connection conn = null;
			PreparedStatement stm = null;
			int result = 0;
			try {
				conn = DBUtil.getCon();
				stm = conn.prepareStatement(sql);
				if (paramList != null) {
					for (int i = 0; i < paramList.length; i++) {
						stm.setObject(i + 1, paramList[i]);
					}
				}
				result = stm.executeUpdate();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				DBUtil.closeAll(conn, null, stm);
			}

			return result;
		}
	}

