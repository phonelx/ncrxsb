package monitor.dept.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import monitor.dept.bean.entity.DeptBean;
import monitor.dept.bean.entity.ServiceUpdate;
import monitor.dept.dao.IDeptDao;
import monitor.dept.service.IDeptService;
import monitor.user.bean.vo.PageInfoVo;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

@WebService
public class DeptServiceImpl implements IDeptService {
	private IDeptDao deptdao;

	String version = "";

	@Override
	public PageInfoVo listDept(PageInfoVo pageVo, String searchKey) throws SQLException {
		return deptdao.listDept(pageVo, searchKey);
	}

	@Override
	public int addDept(DeptBean dept) throws SQLException {
		return deptdao.addDept(dept);
	}

	@Override
	public int delDept(String bmdm) throws SQLException {
		return deptdao.delDept(bmdm);
	}

	@Override
	public void editDept(DeptBean dept) throws SQLException {
		deptdao.editDept(dept);
	}

	@Override
	public void clearDept() {
		deptdao.clearDept();
	}

	@Override
	public List<Map<String, Object>> getAllUser() throws SQLException {

		return deptdao.getAlluser();
	}

	@Override
	public PageInfoVo getUserBySqu(String bmdm, PageInfoVo pageVo) throws SQLException {
		return deptdao.getUserBySqu(bmdm, pageVo);
	}

	public void setDeptdao(IDeptDao deptdao) {
		this.deptdao = deptdao;
	}

	@Override
	public int deptDataUpdate(ServiceUpdate sync) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		String nameSpaceUri = "http://zzjg.sc/GARYGL/orgService.ws";
		List<DeptBean> list = null;
		int k = Integer.parseInt(sync.getStart());
		while (k != -1 && k <= Integer.parseInt(sync.getEnd())) {
			list = new ArrayList<DeptBean>();
			try {
				Service service = new Service();
				Call call = null;
				call = (Call) service.createCall();
				call.setOperationName(new QName(nameSpaceUri, "downloadDatas"));
				call.setTargetEndpointAddress(nameSpaceUri);
				String param = "<?xml version='1.0' encoding='UTF-8'?><Message><MessageHeader><Version>1.0</Version><SystemID>D0AEBCB0980F4BBE9E22A1B5DE47225F</SystemID><ServiceName>"
						+ sync.getServiceType()
						+ "</ServiceName></MessageHeader><MessageBody Type='downloadDatas_Ack'><CheckTime>"
						+ date + "</CheckTime><pageno>" + k + "</pageno></MessageBody></Message>";
				String ret = (String) call.invoke(new Object[] { param });
				k++;
				Document doc = null;
				doc = DocumentHelper.parseText(ret);
				Element rootElt = doc.getRootElement();
				Iterator DataScopes = rootElt.elementIterator("MessageBody");
				while (DataScopes.hasNext()) {
					Element recordEle = (Element) DataScopes.next();
					Iterator DataScope = recordEle.elementIterator("Datas");
					while (DataScope.hasNext()) {
						Element itemEle = (Element) DataScope.next();
						List attrList = itemEle.attributes();
						for (int c = 0; c < attrList.size(); c++) {
							Attribute item = (Attribute) attrList.get(c);
							if (item.getName().equals("num")
									&& item.getValue().equals("0")) {
								k = -1;
							}
						}

						Iterator ScopeItems = itemEle.elementIterator("Data");
						while (ScopeItems.hasNext()) {
							DeptBean loadDept = new DeptBean();
							Element sitem = (Element) ScopeItems.next();
							loadDept.setBmdm(sitem.elementTextTrim("orgcode"));
							loadDept.setUpjgdm(sitem.elementTextTrim("uporgid"));
							loadDept.setBmmc(sitem.elementTextTrim("orgname"));
							loadDept.setJglx(sitem.elementTextTrim("orgtype"));
							loadDept.setJgjc(sitem.elementTextTrim("orgshortname"));
							loadDept.setSfgp(sitem.elementTextTrim("islisted"));
							loadDept.setJgzt(sitem.elementTextTrim("state"));
							loadDept.setOldCode(sitem.elementTextTrim("oldorgid"));
							loadDept.setLastUpTime(sitem.elementTextTrim("updatetime"));
							loadDept.setOldOrgName(sitem.elementTextTrim("oldorgname"));
							list.add(loadDept);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

			if (k != -1) {
				deptdao.upDataDept(list);
				list = null;
			} else {
				break;
			}
		}

		return 0;
	}

	public String getDeptData(String sendId) {
		StringBuffer result = new StringBuffer();
		int num = Integer.parseInt(parseXml(sendId));
		if (num == 1) {
			String nameSpaceUri = "http://zzjg.sc/GARYGL/orgService.ws";
			List<DeptBean> list = null;
			int k = 1;
			while (k != -1 && k <= 100) {
				list = new ArrayList<DeptBean>();
				try {
					Service service = new Service();
					Call call = null;
					call = (Call) service.createCall();
					call.setOperationName(new QName(nameSpaceUri,"downloadDatas"));
					call.setTargetEndpointAddress(nameSpaceUri);
					String param = "<?xml version='1.0' encoding='UTF-8'?><Message><MessageHeader><Version>1.0</Version><SystemID>D0AEBCB0980F4BBE9E22A1B5DE47225F</SystemID><ServiceName>ORGS_INC</ServiceName></MessageHeader><MessageBody Type='downloadDatas_Ack'><CheckTime>"
							+ version + "</CheckTime><pageno>" + k + "</pageno></MessageBody></Message>";
					String ret = (String) call.invoke(new Object[] { param });
					k++;
					Document doc = null;
					doc = DocumentHelper.parseText(ret);
					Element rootElt = doc.getRootElement();
					Iterator DataScopes = rootElt.elementIterator("MessageBody");
					while (DataScopes.hasNext()) {
						Element recordEle = (Element) DataScopes.next();
						Iterator DataScope = recordEle.elementIterator("Datas");
						while (DataScope.hasNext()) {
							Element itemEle = (Element) DataScope.next();
							List attrList = itemEle.attributes();
							for (int c = 0; c < attrList.size(); c++) {
								Attribute item = (Attribute) attrList.get(c);
								if (item.getName().equals("num")
										&& item.getValue().equals("0")) {
									k = -1;
								}
							}
							Iterator ScopeItems = itemEle.elementIterator("Data");
							while (ScopeItems.hasNext()) {
								DeptBean loadDept = new DeptBean();
								Element sitem = (Element) ScopeItems.next();
								loadDept.setBmdm(sitem.elementTextTrim("orgcode"));
								loadDept.setUpjgdm(sitem.elementTextTrim("uporgid"));
								loadDept.setBmmc(sitem.elementTextTrim("orgname"));
								loadDept.setJglx(sitem.elementTextTrim("orgtype"));
								loadDept.setJgjc(sitem.elementTextTrim("orgshortname"));
								loadDept.setSfgp(sitem.elementTextTrim("islisted"));
								loadDept.setJgzt(sitem.elementTextTrim("state"));
								loadDept.setOldCode(sitem.elementTextTrim("oldorgid"));
								loadDept.setLastUpTime(sitem.elementTextTrim("updatetime"));
								loadDept.setOldOrgName(sitem.elementTextTrim("oldorgname"));

								list.add(loadDept);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}

				if (k != -1) {
					deptdao.otherDataDept(list);

					list = null;
				} else {
					break;
				}
			}
			result.append("<Message>");
			result.append("<MessageHeader>");
			result.append("<Version>1.0</Version>");
			result.append("<SystemID>ORGM</SystemID>");// 必填 参考4.7业务系统接入码，区分大小写
			result.append("<ServiceName>SENDMESSAGE</ServiceName>");// 必填
																	// 固定为：SENDMESSAGE
			result.append("</MessageHeader>");
			result.append("<MessageBody Type=“ServiceName_Ack”>");
			result.append("<ErrorMsg></ErrorMsg>"); // 若失败，必填失败原因最大500位字符
			result.append("<State>1</State>"); // 必填“1”代表接口调用成功，“0”代表接口调用失败
			result.append("</MessageBody>");
			result.append("</Message>");

			return result.toString();
		} else {
			result.append("<Message>");
			result.append("<MessageHeader>");
			result.append("<Version>1.0</Version>");
			result.append("<SystemID>ORGM</SystemID>");// 必填 参考4.7业务系统接入码，区分大小写
			result.append("<ServiceName>SENDMESSAGE</ServiceName>");// 必填
																	// 固定为：SENDMESSAGE
			result.append("</MessageHeader>");
			result.append("<MessageBody Type=“ServiceName_Ack”>");
			result.append("<ErrorMsg>功能研发中</ErrorMsg>"); // 若失败，必填失败原因最大500位字符
			result.append("<State>0</State>"); // 必填“1”代表接口调用成功，“0”代表接口调用失败
			result.append("</MessageBody>");
			result.append("</Message>");

			return result.toString();
		}
	}

	// 机构通知解析
	public String parseXml(String strXml) {
		version = "";
		String result = "";
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(strXml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		Iterator rootScopes = root.elementIterator("MessageBody");
		while (rootScopes.hasNext()) {
			Element itemEle = (Element) rootScopes.next();
			result = itemEle.elementTextTrim("versiontype");
			version = itemEle.elementTextTrim("CheckTime");
		}

		return result;
	}

	/**
	 * @throws SQLException 
	 * @see mcs.dept.service.IDeptService#listDeptByBmdm()
	 */
	@Override
	public List<DeptBean> listDeptByBmdm(String pd) throws SQLException {
		return deptdao.listDeptByBmdm(pd);
	}

	@Override
	public int countParentNotes(String pd) {
		return deptdao.countParentNotes(pd);
	}

	@Override
	public JSONObject searchUser(String param) {
		if(param.equals("关键字检索")){
			param="1=1";
		}else{
			param="SFZHM LIKE '%"+param+"%' OR USERTITLE LIKE '%"+param+"%'";
		}
		
		List<Map<String, Object>> listMap=deptdao.searchUser(param);
		int count=deptdao.searchUserCount(param);
		
		JSONObject json=new JSONObject();
		json.put("total", count);// 总记录数
//		json.put("pageSize", 10);// 每页显示多少条 
//		json.put("pageNumber", pageNo);// 当前页码
		json.put("rows", listMap);
		
		return json;
	}

	@Override
	public void addUserToDept(String bmdm, String[] array) throws SQLException {
		deptdao.addUserToDept(bmdm, array);
	}
}
