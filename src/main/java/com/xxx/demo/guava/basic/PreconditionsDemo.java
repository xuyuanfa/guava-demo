package com.xxx.demo.guava.basic;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.google.common.base.Preconditions.checkPositionIndexes;
import static com.google.common.base.Preconditions.checkState;

/**
 * 断言示例
 * 
 * @author xu
 * @link http://ifeve.com/google-guava-preconditions/
 */
public class PreconditionsDemo {

	public static void main(String[] args) {
		checkArgument(true); // 检查boolean是否为true，用来检查传递给方法的参数。 IllegalArgumentException
		checkNotNull(1);// 检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。  NullPointerException
		checkState(true);// 用来检查对象的某些状态。 IllegalStateException
		checkElementIndex(1, 2);// 检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size * IndexOutOfBoundsException
		checkPositionIndex(1, 2);// 检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size * IndexOutOfBoundsException
		checkPositionIndexes(1, 2, 3);// 检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效* IndexOutOfBoundsException
	}

}
