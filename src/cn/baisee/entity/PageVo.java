package cn.baisee.entity;

import java.util.List;
import java.util.Map;


public class PageVo {

	private Integer currentPage = 1;// ��ǰҳ
	private Integer pageSize = 10;// ÿҳ������

	private Integer totalCount;// һ��������

	private List<?> result;
	
	private Map<String, String> params;

	// ��ҳ��
	public Integer getTotalPage() {
		Integer totalPage = (totalCount / pageSize)
				+ (totalCount % pageSize == 0 ? 0 : 1);
		return totalPage;
	}

	
	/**
	 * @return ���ط�ҳ��ѯ�ĵ�һ��ֵ
	 */
	public Integer getStartIndex() {
		Integer startIndex = (currentPage - 1) * pageSize;
		return startIndex;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}


	

	
}
