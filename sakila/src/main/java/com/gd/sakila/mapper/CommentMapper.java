package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.Comment;

@Mapper
public interface CommentMapper {
	int deleteCommentByBoardId(int boardId);
	List<Comment> selectCommentListByBoard(int boardId);
	int insertComment(Comment comment);
	int deleteCommentByCommentId(int commentId);
}
