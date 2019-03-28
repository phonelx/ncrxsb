package monitor.mathUtil;

/**
 * 水平管线侧向及纵向抗震支架间距计算类
 * @ClassName:GAP_Util
 * @dateTime: 2017-11-21 下午7:22:16
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public class GAP_Util {
	
	
	
	public double getGAP(Integer pipelineType, Integer projectType, Integer directionType){
		//抗震支吊架最大间距
		double maxGAP = getMaxGAP(pipelineType, projectType, directionType);
		
		//Horizontal seismic influence coefficient 水平地震力综合系数 HSIC
		double HSIC = getHSIC();
		
		//Angle adjustment factor of aseismatic brace  抗震支撑角度调整系数  AaFoAB
		double AaFoAB = 0;
		
		return maxGAP / (HSIC * AaFoAB);
	}
	
	/**
	  * getHSIC:(水平地震力综合系数αek).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-11-21 下午7:56:57
	  * @return double
	  * @since JDK 1.7
	*/
	public double getHSIC(){
		
		double maxHSIC = 0.0;
		//功能系数
		double function_coefficient = 0.0;
		//类别系数
		double category_coefficient = 0.0;
		//状态系数
		double status_coefficient = 0.0;
		//位置系数
		double position_coefficient = 0.0;
		
		return maxHSIC * function_coefficient * category_coefficient * status_coefficient * position_coefficient;
	}

	/**
	  * getMaxGAP:(抗震支吊架最大间距).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-11-21 下午7:33:09
	  * @param pipelineType 管道类型
	  * @return double
	  * @since JDK 1.7
	*/
	public double getMaxGAP(Integer pipelineType, Integer projectType, Integer directionType){
		double result = 0.0;
		switch (pipelineType) {
		case 1:	result = 24.0;	break;
		case 2:	result = 12.0;	break;
		case 3:	result = 12.0;	break;
		case 4:	result = 18.0;	break;
		case 5:	result = 9.0;	break;
		case 6:	result = 24.0;	break;
		case 7:	result = 12.0;	break;
		default: break;
		}
		if (result != 0) {
			if (projectType == 0) {	//改建工程
				result = result / 2;
			}
			if (directionType == 0) {	//方向侧向
				result = result / 2;
			}
		}
		return result;
	}
}
