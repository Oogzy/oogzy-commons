package com.oogzy.lib.server.util;

public interface Callback<T>
{
	public void run(T result);
}