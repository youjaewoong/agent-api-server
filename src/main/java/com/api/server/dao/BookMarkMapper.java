package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.bookmark.BookMarkResponse;
import com.api.server.model.bookmark.CreateBookMark;
import com.api.server.model.bookmark.DeleteBookMark;
import com.api.server.model.bookmark.SearchBookMarkRequest;
import com.api.server.model.bookmark.UpdateBookMark;

@Repository
@Mapper
public interface BookMarkMapper {

	public List<BookMarkResponse> selectBookMarks(SearchBookMarkRequest searchBookMarkRequest);

	public int createBookMark(CreateBookMark createBookMark);
    
	public int updateBookMark(UpdateBookMark updateBookMark);

	public int deleteBookMark(DeleteBookMark deleteBookMark);
    
	public int deleteBookMarks();
}