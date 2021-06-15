package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Customer;
import com.gd.sakila.vo.CustomerList;

@Mapper
public interface CustomerMapper {
	// 영화 대여
	int insertRental(Map<String, Object> map);
	// 고객 추가
	int insertCustomer(Customer customer);
	// 고객 상세보기
	Map<String, Object> selectCustomerOne(Integer ID);
	// 블랙리스트
	List<Map<String, Object>> selectBlackCustomerList();
	// 고객 리스트
	List<CustomerList> selectCustomerList(Map<String, Object> map);
	// 페이징
	int selectCustomerTotal(String searchWord);
	// 휴면고객
	int updateCustomerActiveByScheduler();
}
