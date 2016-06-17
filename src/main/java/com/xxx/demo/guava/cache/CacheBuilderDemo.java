package com.xxx.demo.guava.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

public class CacheBuilderDemo {

	public static void main(String[] args) {
		LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
				.maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES)
				.removalListener(null)
				.build(new CacheLoader<Integer, String>() {
					public String load(Integer key) {
						return "";
					}
				});

		try {
			cache.get(0);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

	public String get(Integer key, LoadingCache<Integer, String> cache)
			throws Exception {
		try {
			// If the key wasn't in the "easy to compute" group, we need to
			// do things the hard way.
			return cache.get(key, new Callable<String>() {
				@Override
				public String call() throws Exception {
					return "";
				}
			});
		} catch (ExecutionException e) {
			throw new Exception(e.getCause());
		}
	}

}
