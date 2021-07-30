package com.amber.mycommunity.mapper;

import com.amber.mycommunity.model.Comment;
import com.amber.mycommunity.model.CommentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}