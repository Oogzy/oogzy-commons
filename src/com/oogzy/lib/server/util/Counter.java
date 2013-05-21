package com.oogzy.lib.server.util;

import java.util.HashMap;
import java.util.Map;

public class Counter<K>
{
	private Map<K, Double> map = new HashMap<K, Double>();

	public void add(K key)
	{
		add(key, 1);
	}

	public void add(K key, double x)
	{
		Double i = map.get(key);
		if (i == null)
			i = x;
		else
			i = i + x;
		map.put(key, i);
	}

	public double get(K key)
	{
		Double i = map.get(key);
		if (i == null)
			return 0;
		else
			return i.doubleValue();
	}
}