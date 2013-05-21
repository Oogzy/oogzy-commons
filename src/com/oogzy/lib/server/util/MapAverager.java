package com.oogzy.lib.server.util;

import java.util.HashMap;
import java.util.Map;

public class MapAverager<K>
{
	private Map<K, Float> values = new HashMap<K, Float>();
	private MapCounter<K> counter = new MapCounter<K>();

	public void add(K key, float x)
	{
		add(key, 1, x);
	}

	public void add(K key, int w, float x)
	{
		Float i = values.get(key);
		if (i == null)
			i = x;
		else
			i += x;
		values.put(key, i);
		counter.add(key, w);
	}

	public void sub(K key, int w)
	{
		add(key, w, get(key) * -w);
	}

	public float get(K key)
	{
		Float i = values.get(key);
		if (i == null)
			return 0;
		else
			return i.floatValue() / counter.get(key);
	}

	public int getSize(K key)
	{
		return counter.get(key);
	}

	public void clear()
	{
		counter.clear();
		values.clear();
	}
}