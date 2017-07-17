//package cn.baisee.utils.vo;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 分页工具类
// * @author Administrator
// *
// */
///**
// * 分页查询工具类
// * 
// * @author Administrator
// *
// */
//public class PageVo{
//
//	// 当前第几页
//	private Integer page = 1;
//	// 显示数据
//	private List<? extends Object> rows;
//	// 一共多少条数据
//	private Integer total;
//	// 每页多少条数据
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
//	// 获取查询当前位置
//	public Integer getStarIndex() {
//		int starIndex = (page - 1) * pageSize;
//		return starIndex;
//	}
//	
//	
//	
//	//计算总页数
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
