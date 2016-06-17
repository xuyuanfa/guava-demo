package com.xxx.demo.guava.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.ForwardingList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;

public class CollectionsExtend {
	public static void main(String[] args) {
		Iterator<Integer> powersOfTwo = new AbstractSequentialIterator<Integer>(1) {
			protected Integer computeNext(Integer previous) {
				return (previous == 1 << 30) ? null : previous * 2;
			}
		};
	}

	/**
	 * AbstractIterator
	 * 
	 * 包装一个iterator以跳过 空值
	 * 
	 * @param in
	 * @return
	 */
	public static Iterator<String> skipNulls(final Iterator<String> in) {
		return new AbstractIterator<String>() {
			protected String computeNext() {
				while (in.hasNext()) {
					String s = in.next();
					if (s != null) {
						return s;
					}
				}
				return endOfData();
			}
		};
	}

	/**
	 * PeekingIterator
	 * 
	 * 窥视迭代器
	 * 
	 * @param source
	 * @return
	 */
	public <E> List<E> skipDuplicate(List<E> source) {
		List<E> result = Lists.newArrayList();
		PeekingIterator<E> iter = Iterators.peekingIterator(source.iterator());
		while (iter.hasNext()) {
			E current = iter.next();
			while (iter.hasNext() && iter.peek().equals(current)) {
				// 跳过重复的元素
				iter.next();
			}
			result.add(current);
		}
		return result;
	}

	/**
	 * Forwarding*示例
	 * 
	 * 简化装饰者模式
	 */
	class AddLoggingList<E> extends ForwardingList<E> {
		final List<E> delegate = Lists.newArrayList(); // backing list

		@Override
		protected List<E> delegate() {
			return delegate;
		}

		@Override
		public void add(int index, E elem) {
			log(index, elem);
			super.add(index, elem);
		}

		@Override
		public boolean add(E elem) {
			return standardAdd(elem); // 用add(int, E)实现
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			return standardAddAll(c); // 用add实现
		}

		private void log(int index, E elem) {
			//
		}
	}
}
