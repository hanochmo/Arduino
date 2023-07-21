package com.ruoyi.arduino.service.impl;

import com.ruoyi.arduino.domain.bo.ArduinoCGQBo;
import com.ruoyi.arduino.mapper.UinoSqlMapper;
import com.ruoyi.arduino.service.UinoSqlService;
import com.ruoyi.common.core.utils.ip.IpUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UinoSqlServiceImpl implements UinoSqlService {

    @Autowired
    private UinoSqlMapper uinoSqlMapper;

    /**
     * 新增硬件传的信息到数据表
     *
     * @param param 硬件传的信息
     * @return 结果
     */
    @Override
    public AjaxResult insertMap(ArduinoCGQBo param) {
        if (!IpUtils.internalIp(param.getIp())) {
            String hostIp = IpUtils.getHostIp();
            param.setIp(hostIp);
        }
        int i = uinoSqlMapper.insertMap(param);
        if (i > 0 ) {
            return AjaxResult.success("操作成功");
        }
        return AjaxResult.error("insert失败");
    }

    /**
     * 更新硬件传的信息到数据表
     *
     * @param param 硬件传的信息
     * @return 结果
     */
    @Override
    public AjaxResult updateMap(ArduinoCGQBo param) {
        int i = uinoSqlMapper.updateMap(param);
        if (i > 0 ) {
            return AjaxResult.success("操作成功");
        }
        return AjaxResult.error("update失败");
    }


}
