package com.si.board.service;

import com.si.board.vo.PostVo;

import java.util.List;

public interface PostService {
	List<PostVo> getPostByBoard(int boardSeq, int page);

	int insertPost(PostVo postVo);

	List<PostVo> getPostByCategory(int categorySeq, int page);

	int deletePost(int postSeq);

	boolean isCorrectlyWriter(int postSeq, String userId);

	int viewPost(int postSeq);

	boolean isExistPost(int postSeq);

	PostVo getPostDetail(int postSeq);

	int getPostByBoardSum(int boardSeq);

	int getPostByCategorySum(int categorySeq);
}
