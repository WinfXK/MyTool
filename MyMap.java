package cn.winfxk.acaterina.tool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义Map<其实就只是增加了一些没卵用的功能>
 * 
 * @Createdate 2020/05/22 19:37:29
 * @author Winfxk
 * @param <K>
 * @param <V>
 */
public class MyMap<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = -8324429888407081347L;

	/**
	 * 根据Key位置返回Map值
	 * 
	 * @param index
	 * @return
	 */
	public V getVOfIndex(int index) {
		return get(new ArrayList<>(keySet()).get(index));
	}

	/**
	 * 根据Map的值获取Key
	 * 
	 * @param v
	 * @return
	 */
	public List<K> getKey(V v) {
		List<K> list = new ArrayList<>();
		for (Map.Entry<K, V> entry : entrySet())
			if (entry.getValue().equals(v))
				list.add(entry.getKey());
		return list;
	}

	/**
	 * 删除一个Map值
	 * 
	 * @param v
	 * @return
	 */
	public MyMap<K, V> removeValues(V v) {
		if (containsValue(v)) {
			List<K> list = getKey(v);
			for (K k : list)
				remove(k);
		}
		return this;
	}

	/**
	 * 返回第一个Key出现的位置
	 * 
	 * @param k
	 * @return
	 */
	public int indexOf(K k) {
		return new ArrayList<>(keySet()).indexOf(k);
	}

	/**
	 * 返回第一个Key出现的位置
	 * 
	 * @param k
	 * @param strIndex 开始检索的位置
	 * @return
	 */
	public int indexOf(K k, int strIndex) {
		List<K> list = new ArrayList<>(keySet());
		for (int i = strIndex; i < list.size(); i++)
			if (list.get(i).equals(k))
				return i;
		return -1;
	}

	/**
	 * 从后往前返回第一个出现的位置
	 * 
	 * @param k
	 * @return
	 */
	public int laterIndexOf(K k) {
		return new ArrayList<>(keySet()).lastIndexOf(k);
	}

	/**
	 * 从后往前返回第一个出现的位置
	 * 
	 * @param k
	 * @param endIndex 开始检索的位置
	 * @return
	 */
	public int laterIndexOf(K k, int endIndex) {
		List<K> list = new ArrayList<>(keySet());
		for (int i = endIndex; i > 0; i--)
			if (list.get(i).equals(k))
				return i;
		return -1;
	}
}
