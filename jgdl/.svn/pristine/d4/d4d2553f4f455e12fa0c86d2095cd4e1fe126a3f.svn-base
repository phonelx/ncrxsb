var toastImpl;
/**
 * @class Ext.ux.Toast
 * Passive popup box (a toast) singleton
 * @singleton
 */
Ext.ux.Toast = function() {
    var msgCt;

    function createBox(t, s){
        return ['<div class="msg" style="width:300px;">',
                '<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
                '<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><A onclick="closeBox();return false;" href="http://192.168.0.22/#"><img align="right" style="align:right" src="resource/images/common/dialogclose.gif"/></A><h3>', t, '</h3>', s, '</div></div></div>',
                '<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
                '</div>'].join('');
    }
    
    return {
		/**
		 * Shows popup
		 * @member Ext.ux.Toast
		 * @param {String} title
		 * @param {String} format
		 */
        msg : function(title, format){
            if(!msgCt){//msgCt为undefined时进入
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div',style:'position:absolute;z-index:10000'}, true);
                var s = String.format.apply(String, Array.prototype.slice.call(arguments, 1));
	          	var m = Ext.DomHelper.append(msgCt, {html:createBox(title, s)}, true);
	           	
	          	msgCt.alignTo(document, 't-t');
	//            m.slideIn('t').pause(20000).ghost("t", {remove:true});
	            m.slideIn('t',{//第一个参数设置从屏幕滑进位置，'l'：从左滑进；'t'：从上滑下...
	            	duration:2//持续时间
	            });
	            toastImpl = m;
            }
        },
        closed:function(){
        	toastImpl.ghost("t", {remove:true});
        	msgCt = null;
        }
	}

}();
