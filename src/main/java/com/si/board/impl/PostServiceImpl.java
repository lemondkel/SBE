package com.si.board.impl;

import com.si.board.dao.PostDao;
import com.si.board.service.PostService;
import com.si.board.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;

	@Override
	public List<PostVo> getPostByBoard(int boardSeq, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardSeq", boardSeq);
		map.put("page", page);

		return postDao.getPostByBoard(map);
	}

	@Override
	public int insertPost(PostVo postVo) {
		return postDao.insertPost(postVo);
	}

	@Override
	public List<PostVo> getPostByCategory(int categorySeq, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categorySeq", categorySeq);
		map.put("page", page);

		return postDao.getPostByCategory(map);
	}

	@Override
	public int deletePost(int postSeq) {
		return postDao.deletePost(postSeq);
	}

	@Override
	public boolean isCorrectlyWriter(int postSeq, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postSeq", postSeq);
		map.put("userId", userId);

		return postDao.isCorrectlyWriter(map);
	}

	@Override
	public int viewPost(int postSeq) {
		return postDao.viewPost(postSeq);
	}

	@Override
	public boolean isExistPost(int postSeq) {
		return postDao.isExistPost(postSeq);
	}

	@Override
	public PostVo getPostDetail(int postSeq) {
		return postDao.getPostDetail(postSeq);
	}
}