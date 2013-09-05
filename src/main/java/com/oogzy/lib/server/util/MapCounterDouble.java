package com.oogzy.lib.server.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapCounterDouble<K>
{
	private Map<K, Double> map = new HashMap<K, Double>();

	public void add(K key, double x)
	{
		Double i = map.get(key);
		if (i == null)
			i = x;
		else
			i += x;
		map.put(key, i);
	}

	public void set(K key, double x)
	{
		map.put(key, x);
	}

	public double get(K key)
	{
		Double i = map.get(key);
		if (i == null)
			return 0;
		else
			return i.doubleValue();
	}

	public Set<K> getKeys()
	{
		return map.keySet();
	}

	public double limitSub(K key, double sub, double min)
	{
		double cur = get(key);
		if (cur >= sub)
		{
			add(key, -sub);
			return sub;
		}
		else
		{
			map.put(key, 0.0);
			return cur;
		}
	}

	public void clear()
	{
		map.clear();
	}
}