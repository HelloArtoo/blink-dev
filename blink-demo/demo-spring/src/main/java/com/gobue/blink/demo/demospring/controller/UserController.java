package com.gobue.blink.demo.demospring.controller;

import com.gobue.blink.demo.demospring.model.User;
import com.gobue.blink.demo.demospring.model.UserXml;
import com.gobue.blink.demo.demospring.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("用户模块")
@RestController
public class UserController {
    @Autowired
    HelloService helloService;

    @ApiOperation(value = "登录", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", paramType = "query")})
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {


        return "登录成功";
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(helloService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/getuserxml/{id}")
    public ResponseEntity<UserXml> getUserxml(@PathVariable(value = "id") Long id) {
        User user = helloService.getUser(id);
        UserXml userXml = new UserXml(user.getId(), user.getName(), user.getPosition());
        return new ResponseEntity<>(userXml, HttpStatus.OK);
    }
}
