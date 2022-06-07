package com.api.server.v1.service;

import com.api.server.v1.model.news.CreateNews;
import com.api.server.v1.model.news.DeleteNews;
import com.api.server.v1.model.news.SearchNewsReq;
import com.api.server.v1.model.news.SearchNewsRes;
import com.api.server.v1.model.news.UpdatNews;

public interface NewsService {
	
	public SearchNewsRes selectNews(SearchNewsReq searchNewsReqo);

	public void updateNews(UpdatNews UpdatNews);
	
	public void createNews(CreateNews createNews);
	
	public void deleteNews(DeleteNews deleteNews);
	
}