package monitor.common.exception;
/**
 * 
 * @author lxj
 * description:
 * 数据库异常类
 */
public class DaoException extends BaseException {
	private static final long serialVersionUID = 1L;
	//错误ID
	private int errorId = 0;
	
	
	public DaoException()
    {
    }

	public DaoException(int errorId)
    {
		super(errorId);
		this.errorId =errorId;
    }
	
    public DaoException(String s)
    {
        super(s);
    }
    
    public DaoException(int errorId,String s)
    {
        super(errorId, s);
        this.errorId =errorId;
    }
    
    public DaoException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
    
    public DaoException(int errorId,String s, Throwable throwable)
    {
    	super(errorId, s, throwable);
    	this.errorId =errorId;
    }

    public DaoException(Throwable throwable)
    {
        super(throwable);
    }

    public DaoException(int errorId,Throwable throwable)
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
