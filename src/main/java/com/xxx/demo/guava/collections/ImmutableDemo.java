package com.xxx.demo.guava.collections;

import java.awt.Color;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class ImmutableDemo {

	public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
			"red", "orange", "yellow", "green", "blue", "purple");

	class Foo {
		Set<?> bars;

		Foo(Set<?> bars) {
			this.bars = ImmutableSet.copyOf(bars); // defensive copy!
		}
	}

	public static final ImmutableSet<Color> WEBSAFE_COLORS = ImmutableSet.of(
			new Color(0, 191, 255), new Color(0, 191, 255));

	public static final ImmutableSet<Color> GOOGLE_COLORS = ImmutableSet
			.<Color> builder().addAll(WEBSAFE_COLORS)
			.add(new Color(0, 191, 255)).build();

	
	public static void main(String[] args) {
		// 不可变集合
		ImmutableSet<String> foobar = ImmutableSet.of("foo", "bar", "baz");
		// 深度复制
		ImmutableList<String> defensiveCopy = ImmutableList.copyOf(foobar);
		
		
	}

}
