package com.xxx.demo.guava.basic;

import com.google.common.base.Optional;

/**
 * 非空对象示例
 * 
 * @author xu
 * @link http://ifeve.com/google-guava-using-and-avoiding-null/
 */
public class OptionalDemo {

	public static void main(String[] args) {
		Optional<Integer> possible = Optional.of(5);
		System.out.println(possible.isPresent());// 如果Optional包含非null的引用（引用存在），返回true
		System.out.println(possible.get());// 返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
		System.out.println(possible.or(5));// 返回Optional所包含的引用，若引用缺失，返回指定的值
		System.out.println(possible.orNull());// 返回Optional所包含的引用，若引用缺失，返回null
		System.out.println(possible.asSet());// 返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。

		System.out.println(Optional.fromNullable(null).or(2));
	}

	public <T> Optional<T> newOptional(T obj) {
		Optional.of(obj); // 创建指定引用的Optional实例，若引用为null则快速失败
		Optional.absent(); // 创建引用缺失的Optional实例
		Optional.fromNullable(obj); // 创建指定引用的Optional实例，若引用为null则表示缺失
		return null;
	}

}
