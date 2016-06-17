package com.xxx.demo.guava.primitives;

import java.util.List;

import com.google.common.primitives.Ints;

public class PrimitivesUtil {

	public static void main(String[] args) {
		/*
		 * byte Bytes, SignedBytes, UnsignedBytes 
		 * short Shorts 
		 * int Ints, UnsignedInteger, UnsignedInts 
		 * long Longs, UnsignedLong, UnsignedLongs
		 * float Floats 
		 * double Doubles 
		 * char Chars 
		 * boolean Booleans
		 */

		List<Integer> intList = null;
		int[] intArray = null;
		
		intList = Ints.asList(1, 2, 3, 2);
		intArray = Ints.toArray(intList);
		System.out.println(Ints.concat(intArray,intArray)[5]);
		System.out.println(Ints.contains(intArray, 2));
		System.out.println(Ints.indexOf(intArray, 2));
		System.out.println(Ints.lastIndexOf(intArray, 2));
		System.out.println(Ints.min(intArray));
		System.out.println(Ints.max(intArray));
		System.out.println(Ints.join(",", intArray));
		System.out.println(Ints.lexicographicalComparator());

		System.out.println(Ints.compare(1, 2));
	}

}
