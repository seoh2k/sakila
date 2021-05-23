package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Page;
import com.gd.sakila.vo.StaffListView;

@Mapper
public interface StaffListViewMapper {
	List<StaffListView> selectStaffList(Page page);
}
