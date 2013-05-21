package com.oogzy.lib.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapList<K>
{
	private Map<String, List<K>> map = new HashMap<String, List<K>>();

	public void add(String key, K value)
	{
		get(key).add(value);
	}

	public List<K> get(String key)
	{
		List<K> list = map.get(key);
		if (list == null)
		{
			list = new ArrayList<K>();
			map.put(key, list);
		}
		return list;
	}

	public void clear()
	{
		map.clear();
	}
}