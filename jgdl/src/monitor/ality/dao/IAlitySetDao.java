package monitor.ality.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import monitor.ality.bean.entity.AlityBean;
import monitor.user.bean.vo.PageInfoVo;



/**
 *@ClassName
 *@dataTime 2017-10-13-下午1:50:36
 *@version
 *@author:唐青
 *@since
 */
public interface IAlitySetDao {

	List<Map<String, Object>> fethParentMenu(String squ)throws SQLException;

	PageInfoVo fethAlityMenu(String pSqu, PageInfoVo pageVo, String query)throws SQLException;

	int fetchMaxOrder(String pSqu);

	int urlHendiadys(String urlName);

	int delParentMenu(String squ);

	int addParentMenu(String mainName);

	void editParentMenu(String mainName, String squ);

	int addAlityMenu(AlityBean ality);

	void editAlityMenu(AlityBean ality);

	int delAlityMenu(String squ);

	PageInfoVo fetchAlityUrl(String squ, PageInfoVo pageVo)throws SQLException;

	void delAlityUrl(String squ, String doSqu);

	void editAlityUrl(AlityBean ality, String squ);

	int addAlityUrl(AlityBean ality, String squ);

}

