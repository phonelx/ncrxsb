package monitor.common.exception;
/**
 * 
 * @author lxj
 * description：
 * 系统异常类 
 */
public class SysException extends BaseException {
	private static final long serialVersionUID = 1L;
	//错误ID
	private int errorId = 0;
	
	
	public SysException()
    {
    }

	public SysException(int errorId)
    {
		super(errorId);
		this.errorId =errorId;
    }
	
    public SysException(String s)
    {
        super(s);
    }
    
    public SysException(int errorId,String s)
    {
        super(errorId, s);
        this.errorId =errorId;
    }
    
    public SysException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
    
    public SysException(int errorId,String s, Throwable throwable)
    {
    	super(errorId, s, throwable);
    	this.errorId =errorId;
    }

    public SysException(Throwable throwable)
    {
        super(throwable);
    }

    public SysException(int errorId,Throwable throwable)
    {
    	super(errorId, throwable);
    	this.errorId =errorId;
    }
    
	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
}
