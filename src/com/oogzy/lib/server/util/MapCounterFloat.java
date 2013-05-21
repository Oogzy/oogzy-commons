package com.oogzy.lib.server.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapCounterFloat<K>
{
	private Map<K, Float> map = new HashMap<K, Float>();

	public void add(K key, float x)
	{
		Float i = map.get(key);
		if (i == null)
			i = x;
		else
			i += x;
		map.put(key, i);
	}

	public float get(K key)
	{
		Float i = map.get(key);
		if (i == null)
			return 0;
		else
			return i.floatValue();
	}

	public Set<K> getKeys()
	{
		return map.keySet();
	}

	public float limitSub(K key, float sub, float min)
	{
		float cur = get(key);
		if (cur >= sub)
		{
			add(key, -sub);
			return sub;
		}
		else
		{
			map.put(key, 0f);
			return cur;
		}
	}

	public void clear()
	{
		map.clear();
	}
}