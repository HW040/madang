package com.green.madang.manager.customer;

import com.green.madang.manager.customer.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper mapper;

    public int postCustomer(CustomerPostReq p) {
        return mapper.insCustomer(p);
    }

    public List<CustomerGetRes> getCustomerList(CustomerGetReq p) {

        p.setStartIdx((p.getPage() -1) * p.getSize());
        //page 로직 처리

        return mapper.selCustomerList(p);
    }
    public int putCustomer(CustomerPutReq p) {
        return mapper.updCustomer(p);
    }

    public int deleteCustomer(CustomerDelete p) {
        return mapper.deleteCustomer(p);
    }
}
