package com.ruoyi.arduino.controller;

import com.ruoyi.arduino.SpringContextUtil;
import com.ruoyi.arduino.domain.bo.ArduinoCGQBo;
import com.ruoyi.arduino.mapper.UinoSqlMapper;
import com.ruoyi.arduino.service.UinoSqlService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/arduino")
public class ArduinoCGQController extends BaseController {

    @Autowired
    private UinoSqlService uinoSqlService;



    @PostMapping("/insertMap")
    public AjaxResult insertMap(@RequestBody ArduinoCGQBo arduinoCGQBo){
        return uinoSqlService.insertMap(arduinoCGQBo);
    }


    @PostMapping("/updateMap")
    public AjaxResult updateMap(@RequestBody ArduinoCGQBo arduinoCGQBo){
        return uinoSqlService.updateMap(arduinoCGQBo);
    }

    @GetMapping("/ceshi")
    public AjaxResult ceshi(){
        return AjaxResult.success("1111111");
    }



}
