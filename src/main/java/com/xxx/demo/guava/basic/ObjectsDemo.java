package com.xxx.demo.guava.basic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * 对象常见方法示例
 * 
 * @author xu
 * @link http://ifeve.com/google-guava-commonobjectutilities/
 */
public class ObjectsDemo {

	public static void main(String[] args) {
		System.out.println(Objects.equal("a", "a")); // returns true
		System.out.println(Objects.equal(null, "a")); // returns false
		System.out.println(Objects.equal("a", null)); // returns false
		System.out.println(Objects.equal(null, null)); // returns true

		System.out.println(Objects.hashCode(1, 2, 3));

		toStringHelper();

		System.out.println(MoreObjects.firstNonNull(null, 1));

	}

	private static void toStringHelper() {
		System.out.println(MoreObjects.toStringHelper(ObjectsDemo.class)
				.add("x", 1).toString());// Returns "ClassName{x=1}"
		System.out.println(MoreObjects.toStringHelper("MyObject").add("x", 1)
				.toString());// Returns "MyObject{x=1}"
	}

	/**
	 * 比较示例
	 */
	class Person implements Comparable<Person> {
		private String lastName;
		private String firstName;
		private int zipCode;

		public int compareTo(Person other) {
			int cmp = lastName.compareTo(other.lastName);
			if (cmp != 0) {
				return cmp;
			}
			cmp = firstName.compareTo(other.firstName);
			if (cmp != 0) {
				return cmp;
			}
			return Integer.compare(zipCode, other.zipCode);
		}

		public int compareToGuava(Person that) {
			return ComparisonChain
					.start()
					.compare(this.lastName, that.lastName)
					.compare(this.firstName, that.firstName)
					.compare(this.zipCode, that.zipCode,
							Ordering.natural().nullsLast()).result();
		}
	}

}
