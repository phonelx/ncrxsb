package monitor.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import monitor.common.dao.BaseDAO;

/**
 * @ClassName
 * @dataTime 2018-6-23-下午4:20:39
 * @version
 * @author:唐青
 * @since
 */
public class UpdateCalculate extends BaseDAO {
	Connection conn = null;
	PreparedStatement ps = null;

	public void updateInfo(int type, String squ,Connection conn) {
		try {
			//conn = datasource.getConnection();
			String str = "";
			if (type == 1) {// 项目
				str = "PROJECTSQU";
			} else if (type == 2) {// 子单位
				str = "CHILDSQU";
			} else if (type == 3) {// 部位
				str = "SITESQU";
			} else if (type == 4) {// 支架
				str = "ZJSQU";
			} else if (type == 5) {// 组合部件
				str = "ZHSQU";
			} else if (type == 6) {// 零部件
				str = "BJSQU";
			}
			String sql = "UPDATE PROJECT_CALCULATEINFO SET STATUS=1 WHERE "
					+ str + " = '" + squ + "' ";		
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
