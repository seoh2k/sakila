package com.gd.sakila.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Staff;

/*
 * @Component, @Repository, @Service, @Controller --> Bean --> spring.getBean(클래스 타입), @AutoWired <-- Dependency Injection
 * @Mapper: myBatis의 애노테이션 --> @Repository의 역할을 한다 --> 매퍼 + 인터페이스 --> 컴파일(?) 시 구현클래스 자동으로 생성
 */
@Mapper // mapper.xml 을 찾아서 매서드명과 mapper의 id 명의 같으면 합쳐서 메서드 구현클래스 생성 시 오버라이딩 한다.
public interface StaffMapper {
	Staff selectStaffByLogin(Staff staff);
}
