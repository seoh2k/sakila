package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Actor;
import com.gd.sakila.vo.Page;

@Mapper
public interface ActorMapper {
	int insertFilmActorByFilmOne(int filmId, int actorId);
	int deleteFilmActorByFilmOne(int filmId);
	int insertActor(Actor actor);
	List<Actor> selectActorInfoList(Page page);
	int selectActorTotal(String searchWord);
}
