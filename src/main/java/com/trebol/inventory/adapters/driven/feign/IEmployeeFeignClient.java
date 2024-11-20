package com.trebol.inventory.adapters.driven.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employees-trebol", url = "http://localhost:8080/employee")
public interface IEmployeeFeignClient {

    @GetMapping("/{id}")
    String getEmployee(@PathVariable("id") String id);

}
