package dev.sideproject.momo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080",allowedHeaders = "*")
@RestController
public class ApiController {

    @GetMapping("/api/")
    @ApiOperation(value = "테스트 용도 API")
    public String hello(){
        return "hello";
    }

}
