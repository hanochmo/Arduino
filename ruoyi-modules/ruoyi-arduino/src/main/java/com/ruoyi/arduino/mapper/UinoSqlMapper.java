package com.ruoyi.arduino.mapper;


import com.ruoyi.arduino.domain.bo.ArduinoCGQBo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UinoSqlMapper {


    /**
     * 新增硬件传的信息到数据表
     *
     * @param param 硬件传的信息
     * @return 结果
     */
    public int insertMap(ArduinoCGQBo param);


    /**
     * 更新硬件传的信息到数据表
     *
     * @param param 硬件传的信息
     * @return 结果
     */
    public int updateMap(ArduinoCGQBo param);
}
