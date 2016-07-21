/*
 *
 *  *Copyright 2009-2012 Evun Technology.
 *  *This software is the confidential and proprietary information of Evun Technology.
 *  *("Confidential Information").  You shall not disclose such Confidential Information and shall use it only in
 *  *accordance with the terms of the license agreement you entered into with evun.cn.
 *  *
 *
 */
/**
 *  easyui datebox 验证结束时间 < 起始时间，参考policy.jsp
 */
$.extend($.fn.validatebox.defaults.rules, {
    dateValid: {
        validator: function (value, param) { //参数value为当前文本框的值，也就是endDate
            var startTime = $(param[0]).datetimebox('getValue');//获取起始时间的值
            var start = $.fn.datebox.defaults.parser(startTime);
            var end = $.fn.datebox.defaults.parser(value);
            return end >= start;
        },
        message: '结束时间要大于开始时间!'
    }
});