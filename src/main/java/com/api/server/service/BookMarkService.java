package com.api.server.service;

import java.util.List;

import com.api.server.model.bookmark.BookMarkResponse;
import com.api.server.model.bookmark.CreateBookMark;
import com.api.server.model.bookmark.SearchBookMarkRequest;
import com.api.server.model.bookmark.UpdateBookMark;

public interface BookMarkService {
	
	public List<BookMarkResponse> selectBookMarks(SearchBookMarkRequest searchBookMarkRequest);

	public void createBookMark(CreateBookMark createBookMark);

	public void updateBookMark(UpdateBookMark updateBookMark);
	
	public void deleteBookMark(String id);

	public void deleteBookMarks();

}