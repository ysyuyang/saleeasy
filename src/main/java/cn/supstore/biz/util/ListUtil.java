package cn.supstore.biz.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class ListUtil {

	public static boolean isEmpty(Collection<?> c) {
		return CollectionUtils.isEmpty(c);
	}
	
	/**
	 * 将list集合进行分组
	 * @param list
	 * @param num
	 * @return
	 */
	public static<T> List<List<T>> group(List<T> list, int num) {
		if(isEmpty(list)) return null;
		
		List<List<T>> retList = new ArrayList<>();
		int len = list.size();
		if(len > num) {
			
			int arrSize = len%num==0 ? len/num : len/num+1;
			for(int i=0; i<arrSize; i++) {
				int start = num*i;
				int end = num*(i+1) > len ? len : num*(i+1);
				List<T> subList = list.subList(start, end);
				retList.add(subList);
			}
			
		} else {
			retList.add(list);
		}
		return retList;
	}
}
