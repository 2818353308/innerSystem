//package cn.baisee.utils.vo;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * ��ҳ������
// * @author Administrator
// *
// */
///**
// * ��ҳ��ѯ������
// * 
// * @author Administrator
// *
// */
//public class PageVo{
//
//	// ��ǰ�ڼ�ҳ
//	private Integer page = 1;
//	// ��ʾ����
//	private List<? extends Object> rows;
//	// һ������������
//	private Integer total;
//	// ÿҳ����������
//	private Integer pageSize = 1;
//	
//	private Map<String, String> params;
//	
//	
//	public Integer getPage() {
//		return page;
//	}
//
//	public void setPage(Integer page) {
//		this.page = page;
//	}
//
//	public void setList(List<? extends Object> result) {
//		this.rows = result;
//	}
//
//	public List<? extends Object> getRows() {
//		return rows;
//	}
//
//	public void setRows(Integer rows) {
//		this.pageSize = rows;
//		System.out.println(pageSize);
//	}
//
//	public Integer getTotal() {
//		return total;
//	}
//
//	public void setTotal(Integer total) {
//		this.total = total;
//	}
//
//	// ��ȡ��ѯ��ǰλ��
//	public Integer getStarIndex() {
//		int starIndex = (page - 1) * pageSize;
//		return starIndex;
//	}
//	
//	
//	
//	//������ҳ��
//	public Integer getTotalPage(){
//		Integer totalPage=(total/page)+(total%page==0? 0 : 1);
//		return totalPage;
//	}
//	
//	public Integer getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(Integer pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public Map<String, String> getParams() {
//		return params;
//	}
//
//	public void setParams(Map<String, String> params) {
//		this.params = params;
//	}
//	
//	
//	
//}
