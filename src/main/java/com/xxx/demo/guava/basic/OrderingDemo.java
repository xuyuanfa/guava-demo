package com.xxx.demo.guava.basic;

import java.util.ArrayList;
import java.util.Comparator;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.sun.istack.internal.Nullable;

/**
 * 
 * @author xu
 * @link http://ifeve.com/google-guava-ordering/
 */
public class OrderingDemo {

	public static void main(String[] args) {
		Ordering.natural(); // 对可排序类型做自然排序，如数字按大小，日期按先后排序
		Ordering.usingToString(); // 按对象的字符串形式做字典排序[lexicographical ordering]
		
		// 自定义排序器
		Ordering<String> byLengthOrdering = new Ordering<String>() {
			public int compare(String left, String right) {
				return Ints.compare(left.length(), right.length());
			}
		};

		// null排在前面
		Ordering<Foo> ordering = Ordering.natural().nullsFirst()
				.onResultOf(new Function<Foo, String>() {
					public String apply(Foo foo) {
						return foo.sortedBy;
					}
				});
		
		// 排序处理
		Ordering.natural().reverse();//	获取语义相反的排序器
		Ordering.natural().nullsFirst();//	使用当前排序器，但额外把null值排到最前面。
		Ordering.natural().nullsLast();//	使用当前排序器，但额外把null值排到最后面。
		Ordering.natural().compound(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});//	合成另一个比较器，以处理当前排序器中的相等情况。
		Ordering.natural().lexicographical();//	基于处理类型T的排序器，返回该类型的可迭代对象Iterable<T>的排序器。
		Ordering.natural().onResultOf(new Function<Foo, String>() {
			public String apply(Foo foo) {
				return foo.sortedBy;
			}});//	对集合中元素调用Function，再按返回值用当前排序器排序。

		// 集合处理
		Ordering.natural().greatestOf(Lists.newArrayList(1,2), 1);//	获取可迭代对象中最大的k个元素。
		Ordering.natural().isOrdered(Lists.newArrayList(1,2));//	判断可迭代对象是否已按排序器排序：允许有排序值相等的元素。
		Ordering.natural().sortedCopy(Lists.newArrayList(1,2));//	判断可迭代对象是否已严格按排序器排序：不允许排序值相等的元素。	
		Ordering.natural().min(1, 2);//	返回两个参数中最小的那个。如果相等，则返回第一个参数。
		Ordering.natural().min(1, 2, 3, 2);//	返回多个参数中最小的那个。如果有超过一个参数都最小，则返回第一个最小的参数。
		Ordering.natural().min(Lists.newArrayList(1,2));//	返回迭代器中最小的元素。如果可迭代对象中没有元素，则抛出NoSuchElementException。
	}

	class Foo {
		@Nullable
		String sortedBy;
		int notSortedBy;
	}

}
