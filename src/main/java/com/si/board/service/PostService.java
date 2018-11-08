package com.si.board.service;

import java.util.List;

import com.si.board.vo.PostVo;

public interface PostService {
	List<PostVo> getPostByBoard(int boardSeq, int page);

	int insertPost(PostVo postVo);

	List<PostVo> getPostByCategory(int categorySeq, int page);
}
