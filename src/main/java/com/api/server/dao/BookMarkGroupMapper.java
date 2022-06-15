package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.bookmarkgroup.BookMarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookMarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookMarkGroup;
import com.api.server.model.bookmarkgroup.SearchBookMarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookMarkGroup;

@Repository
@Mapper
public interface BookMarkGroupMapper {

	List<BookMarkGroupResponse> selectBookMarkGroups(SearchBookMarkGroupRequest searchBookMarkGroupRequest);

	int createBookMarkGroup(CreateBookMarkGroup createBookMarkGroup);
    
	int updateBookMarkGroup(UpdateBookMarkGroup updateBookMarkGroup);

	int deleteBookMarkGroup(DeleteBookMarkGroup deleteBookMarkGroup);

	int deleteBookMarkGroups();
	
	int checkBookMarkGroupByName(String name);

}