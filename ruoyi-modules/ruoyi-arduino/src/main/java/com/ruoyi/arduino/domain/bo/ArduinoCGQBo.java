package com.ruoyi.arduino.domain.bo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class ArduinoCGQBo implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty("主键")
    private int id;


    @ApiModelProperty("备注")
    private String remarks;



    @ApiModelProperty("inputStr")
    private String inputStr;


    @ApiModelProperty("ip地址")
    private String ip;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;



    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
