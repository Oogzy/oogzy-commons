package com.oogzy.lib.server.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapCounter<K>
{
	private Map<K, Integer> map = new HashMap<K, Integer>();

	public void add(K key)
	{
		add(key, 1);
	}

	public void add(K key, int x)
	{
		Integer i = map.get(key);
		if (i == null)
			i = x;
		else
			i = i + x;
		map.put(key, i);
	}

	public int get(K key)
	{
		Integer i = map.get(key);
		if (i == null)
			return 0;
		else
			return i.intValue();
	}

	public Set<K> getKeys()
	{
		return map.keySet();
	}

	@Override
	public String toString()
	{
		return map.toString();
	}

	public void clear()
	{
		map.clear();
	}

	public int size()
	{
		return map.size();
	}
}