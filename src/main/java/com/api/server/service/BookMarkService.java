package com.api.server.service;

import java.util.List;

import com.api.server.model.bookmark.BookMarkByGroupResponse;
import com.api.server.model.bookmark.CreateBookMark;
import com.api.server.model.bookmark.DeleteBookMark;
import com.api.server.model.bookmark.SearchBookMarkRequest;
import com.api.server.model.bookmark.UpdateBookMark;

public interface BookMarkService {
	
	public List<BookMarkByGroupResponse> selectBookMarks(SearchBookMarkRequest searchBookMarkRequest);

	public void createBookMark(CreateBookMark createBookMark) throws Exception;

	public void updateBookMark(UpdateBookMark updateBookMark);
	
	public void deleteBookMark(String id);
	
	public void deleteBookMarks(DeleteBookMark deleteBookMark);

	public void deleteBookMarkByGroups(DeleteBookMark deleteBookMark);

}