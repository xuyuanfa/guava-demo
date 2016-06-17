package com.xxx.demo.guava;

import java.util.List;

import org.hashids.Hashids;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class TestClass {

	public static void main(String[] args) {
		Hashids hashids = new Hashids("test");
		String encoded = hashids.encode(0x7ffffffffL);
		System.out.println(encoded);
	}
}
