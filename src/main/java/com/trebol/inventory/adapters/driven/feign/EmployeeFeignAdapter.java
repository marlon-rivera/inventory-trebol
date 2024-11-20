package com.trebol.inventory.adapters.driven.feign;

import com.trebol.inventory.domain.exception.EmployeeNotFoundException;
import com.trebol.inventory.domain.spi.IEmployeeClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeFeignAdapter implements IEmployeeClient {

    private final IEmployeeFeignClient delegate;

    @Override
    public String getEmployeeName(String id) {
        String fullName = "";
        try{
            fullName = delegate.getEmployee(id);
        }catch (FeignException.NotFound ex){
            throw new EmployeeNotFoundException();
        }
        return fullName;
    }
}
