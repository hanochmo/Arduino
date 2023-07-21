package com.ruoyi.arduino.service;

import com.ruoyi.arduino.domain.bo.ArduinoCGQBo;
import com.ruoyi.common.core.web.domain.AjaxResult;

public interface UinoSqlService {

    /**
     * 新增硬件传的信息到数据表
     *
     * @param param 硬件传的信息
     * @return 结果
     */
    public AjaxResult insertMap(ArduinoCGQBo param);


    /**
     * 更新硬件传的信息到数据表
     *
     * @param param 硬件传的信息
     * @return 结果
     */
    public AjaxResult updateMap(ArduinoCGQBo param);


}
