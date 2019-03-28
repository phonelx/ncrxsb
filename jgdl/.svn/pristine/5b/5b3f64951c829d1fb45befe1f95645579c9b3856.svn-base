package monitor.common.exception;
/**
 * 
 * @author lxj
 * description：
 * 		运行维护系统基础异常类
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	//错误ID
	private int errorId = 0;
	
	/**
	 * @param  :
	 */
	public BaseException()
    {
		super();
    }
	
	/**
	 * @param  : errorId 错误消息ID
	 */
	public BaseException(int errorId)
    {
		super();
		this.errorId = errorId;
    }
	
	/**
	 * @param  : string 错误消息
	 */
    public BaseException(String s)
    {
        super(s);
    }
    
    /**
     * @param  : errorId 错误消息ID
	 * @param  : string 错误消息
	 */
    public BaseException(int errorId,String s)
    {
        super(s);
        this.errorId = errorId;
    }
    
    /**
     * @param  : string 错误消息
	 * @param  : throwable 异常
	 */
    public BaseException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
    
    /**
     * @param  : errorId 错误消息ID
	 * @param  : string 错误消息
	 * @param  : throwable 异常
	 */
    public BaseException(int errorId,String s, Throwable throwable)
    {
        super(s, throwable);
        this.errorId = errorId;
    }

    /**
	 * @param  : throwable 异常
	 */
    public BaseException(Throwable throwable)
    {
        super(throwable);
    }

    /**
     * @param  : errorId 错误消息ID
	 * @param  : throwable 异常
	 */
    public BaseException(int errorId,Throwable throwable)
    {
        super(throwable);
        this.errorId = errorId;
    }
    
	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
}
