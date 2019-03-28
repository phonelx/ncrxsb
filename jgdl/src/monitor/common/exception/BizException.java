package monitor.common.exception;
/**
 * 
 * @author lxj
 * description:
 * 业务逻辑异常类
 */
public class BizException extends BaseException {
	private static final long serialVersionUID = 1L;
	//错误ID
	private int errorId = 0;
	
	
	public BizException()
    {
    }

	public BizException(int errorId)
    {
		super(errorId);
		this.errorId =errorId;
    }
	
    public BizException(String s)
    {
        super(s);
    }
    
    public BizException(int errorId,String s)
    {
        super(errorId, s);
        this.errorId =errorId;
    }
    
    public BizException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
    
    public BizException(int errorId,String s, Throwable throwable)
    {
    	super(errorId, s, throwable);
    	this.errorId =errorId;
    }

    public BizException(Throwable throwable)
    {
        super(throwable);
    }

    public BizException(int errorId,Throwable throwable)
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
