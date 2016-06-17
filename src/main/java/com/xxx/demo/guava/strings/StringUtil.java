package com.xxx.demo.guava.strings;

import java.util.Arrays;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public class StringUtil {
	public static void main(String[] args) {
		String string = "abcd";
		String string2 = "abdd";
		Strings.emptyToNull(string);
		Strings.nullToEmpty(string);
		Strings.isNullOrEmpty(string);
		System.out.println(Strings.repeat("a", 10));
		System.out.println(Strings.commonPrefix(string, string2));
		System.out.println(Strings.commonSuffix(string, string2));
		System.out.println(Strings.padEnd(string, 10, '!'));
		System.out.println(Strings.padStart(string, 10, '!'));

		Joiner joiner = Joiner.on("; ").skipNulls();
		System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));
		System.out.println(Joiner.on(',').join(Arrays.asList(1, 5, 7)));

		System.out.println(Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux"));
		
		string.getBytes(Charsets.UTF_8);
		
		System.out.println(CharMatcher.inRange('a', 'z').matchesAllOf("bsdsd2bd"));
		System.out.println(CharMatcher.is('a').matchesAllOf("bsdsd2bd"));
		System.out.println(CharMatcher.anyOf("fs").matchesAllOf("bsdsd2bd"));
		
		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"));
		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, "CONSTANT_NAME"));
		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, "CONSTANT_NAME"));
		System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "constant_name"));
		System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, "constant_name"));
	}
}
