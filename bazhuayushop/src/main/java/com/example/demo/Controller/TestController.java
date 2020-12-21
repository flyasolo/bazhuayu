package com.example.demo.Controller;

import com.example.demo.Bean.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :yuang
 * @date :2020.12.21
 */

@Api(value = "test")
@RestController
@RequestMapping(value = "test")
public class TestController {

    @ApiOperation(value = "test_hello")
    @ApiImplicitParam(name = "name", value = "String", required = true)
    @GetMapping(value = "test_hello")
    public Response<String> getHello(String name){
        Response<String> response = new Response<>();
        response.setCode(200).setData("hello"+ name).setMessage("success");
        return response;
    }


}
