package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.bookmarkgroup.BookMarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookMarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookMarkGroup;
import com.api.server.model.bookmarkgroup.SearchBookMarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookMarkGroup.UpdateBookMarkGroups;

@Repository
@Mapper
public interface BookMarkGroupMapper {

	List<BookMarkGroupResponse> selectBookMarkGroups(SearchBookMarkGroupRequest searchBookMarkGroupRequest);

	int createBookMarkGroup(CreateBookMarkGroup createBookMarkGroup);
    
	int updateBookMarkGroup(UpdateBookMarkGroups updateBookMarkGroups);

	int deleteBookMarkGroup(DeleteBookMarkGroup deleteBookMarkGroup);

	int deleteBookMarkGroups();

}