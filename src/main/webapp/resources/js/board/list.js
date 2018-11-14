documentReady(readyFunc);

/**
 * document의 상태가 ready되면 수행되는 함수
 * 
 * @author l2jong
 * @since 2018-11-14
 * @returns
 */
function readyFunc() {
	var postSum = parseInt(document.getElementById('postSum').value);
	var page = parseInt(document.getElementById('currentPage').value);
	var countList = 10;
	var countPage = 10;
	var totalPage = parseInt(postSum / countList);
	
	if (totalPage === 0) {
		// 페이지가 하나밖에 없을 경우 페이징을 그리지 않습니다.
		return false;
	}

	if (postSum !== 0) {
		if (postSum % countList > 0) {
		    totalPage++;
		}
	
		if (totalPage < page) {
		    page = totalPage;
		}
	
		var startPage = parseInt((page - 1) / 10) * 10 + 1;
		var endPage = startPage + countPage - 1;
	
		if (endPage > totalPage) {
		    endPage = totalPage;
		}
		
		var pagination = document.getElementById('pagingList');
	
		if (page > 1) {
			pagination.innerHTML += '<li class="page-item"><a class="page-link" href="javascript:goPage(' + (page - 1) + ')" tabindex="-1">이전</a></li>';
		}
	
		for (var iCount = startPage; iCount <= endPage; iCount++) {
		    if (iCount == page) {
		    	pagination.innerHTML += '<li class="page-item"><a class="page-link" href="javascript:goPage(' + iCount + ')"><b>' + iCount + '</b></a></li>';
		    } else {
		    	pagination.innerHTML += '<li class="page-item"><a class="page-link" href="javascript:goPage(' + iCount + ')">' + iCount + '</a></li>';
		    }
		}
	
		if (page < totalPage) {
			pagination.innerHTML += '<li class="page-item"><a class="page-link" href="javascript:goPage(' + (page + 1) + ')">다음</a></li>';
		}
	}
}

/**
 * 페이지를 이동합니다.
 * 
 * @author l2jong
 * @since 2018-11-14
 * @param pageNum
 * @returns
 */
function goPage(pageNum) {
	var boardSeq = document.getElementById('boardSeq').value;
	var categorySeq = document.getElementById('categorySeq').value;

	window.location.href = getContextPath() + '/board/' + boardSeq + '/' + categorySeq + '/' + pageNum;
}

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