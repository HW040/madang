package com.green.madang.manager.customer;

import com.green.madang.common.model.MyResponse;
import com.green.madang.manager.customer.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("manager/customer")
@Tag(name = "고객", description = "고객 관리")
public class CustomerController {
    private final CustomerService service;

    @PostMapping
    @Operation(summary = "고객 등록")
    public MyResponse<Integer> postCustomer(@RequestBody CustomerPostReq p) {
        int result = service.postCustomer(p);
        return new MyResponse<>("고객이 등록되었습니다.", result);
    }

    @GetMapping
    public MyResponse<List<CustomerGetRes>> getCustomerList(@ParameterObject CustomerGetReq p) {
        List<CustomerGetRes> res = service.getCustomerList(p);

        return new MyResponse<>(p.getPage() + "페이지 데이터", res);
    }

    //RequestParam을 이용한 GetMapping
    @GetMapping("/param")
    @Operation(summary = "고객 리스트2", description = "검색할 내용이 있을 시 검색타입, 검색내용을 모두 보내주어야 한다.")
    public MyResponse<List<CustomerGetRes>> getCustomerList2( @RequestParam int page
            , @RequestParam int size
            , @RequestParam(name="search_type", required = false) String searchType
            , @RequestParam(name="search_text", required = false) String searchText) {
        CustomerGetReq p = new CustomerGetReq();
        p.setPage(page);
        p.setSize(size);
        p.setSearchType(searchType);
        p.setSearchText(searchText);
        log.info("get-req: {}", p);
        List<CustomerGetRes> res = service.getCustomerList(p);
        return new MyResponse<>(p.getPage() + "페이지 데이터", res);
    }



    @PutMapping
    public MyResponse<Integer> updateCustomer(@RequestBody CustomerPutReq p) {
        int result = service.putCustomer(p);
        return new MyResponse<>("고객 수정 완료", result);
    }

    @DeleteMapping
    public MyResponse<Integer> deleteCustomer(@RequestBody CustomerDelete p) {
        log.info("request = {}", p.getCustId());
        return  new MyResponse<>( "고객 삭제", service.deleteCustomer(p));
    }




}
