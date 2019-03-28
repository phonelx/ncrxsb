/**
 * 
 */
package monitor.common.interceptor;

import monitor.common.BaseAction;
import monitor.user.bean.dto.UserDto;
import monitor.user.service.IAuthorityService;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 * @author skallen
 *
 */
@SuppressWarnings("static-access")
public class AuthorityInterceptor extends BaseAction implements Interceptor {
	private IAuthorityService authService = null;
	private static final long serialVersionUID = -7861777969812964605L;
	public IAuthorityService getAuthService() {
		return authService;
	}
	public void setAuthService(IAuthorityService authService) {
		this.authService = authService;
	}
	
	public String intercept(ActionInvocation invacation) throws Exception {
		String request_uri = getRequest().getRequestURI();
		String ctxPath  = getRequest().getContextPath();
		
		String currentURI=request_uri.substring(ctxPath.length());
        UserDto user = (UserDto) getSession().getAttribute("user");
		if(authService.checkPublicAction(currentURI)){
            if(currentURI.lastIndexOf("login.do")!=-1
                    ||currentURI.lastIndexOf("accessPage.do")!=-1||currentURI.lastIndexOf("loginOther.do")!=-1
                    ||currentURI.lastIndexOf("checkSysStatus.do")!=-1||user!=null) {
			    return invacation.invoke();
            } else {
                return this.LOGIN;
            }
		}else if(authService.checkPublicJsp(currentURI)){
			return invacation.invoke();
		}else{
			if(user==null){
				return this.LOGIN;
			}
			if("/showMainIndex.do".equals(currentURI)||"/gotoStandardQuery.do".equals(currentURI)){//特殊情况,必须放到这里，不能放到DAO里面
				return invacation.invoke();
			}
			
			if(user!=null){
				if(authService.checkRight(currentURI, user.getRoleSqu())){
					return invacation.invoke();
				}
			}
			System.out.println("无权限url!: "+currentURI);
			return "noright";
		}
	}

	public void destroy() {

	}

	public void init() {

	}
}
