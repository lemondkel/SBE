/**
 * 게시물 작성 페이지로 이동합니다.
 * 
 * @author l2jong
 * @since 2018-11-08
 * @returns
 */
function goBoardWrite() {
	var boardSeq = document.getElementById('boardSeq').value;
	window.location.href = getContextPath() + '/board/write/' + boardSeq;
}