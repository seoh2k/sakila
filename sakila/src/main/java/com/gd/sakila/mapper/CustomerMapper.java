package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.CustomerList;

@Mapper
public interface CustomerMapper {
	List<Map<String, Object>> selectBlackCustomerList();
	List<CustomerList> selectCustomerList(Map<String, Object> map);
	int selectCustomerTotal(String searchWord);
	int updateCustomerActiveByScheduler();
}
