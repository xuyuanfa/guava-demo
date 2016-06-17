package com.xxx.demo.guava.collections;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.common.collect.TreeMultimap;
import com.google.common.primitives.Ints;

public class CollectionsDemo {

	public static void main(String[] args) {
		Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3),
				Ints.asList(4, 5, 6)); // concatenated包括元素 1, 2, 3, 4, 5, 6
		Set set = Sets.newHashSet("a", "b");
		set.add("c");
		String lastAdded = Iterables.getLast(set);
		System.out.println(lastAdded);
		String theElement = Iterables.getOnlyElement(Sets.newHashSet("a"));// 如果set不是单元素集，就会出错了！

		List countUp = Ints.asList(1, 2, 3, 4, 5);
		List countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
		List<List<?>> parts = Lists.partition(countUp, 2);// {{1,2}, {3,4}, {5}}

		// 集合交集
		Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two",
				"three", "six", "seven", "eight");
		Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");
		SetView<String> intersection = Sets.intersection(primes,
				wordsWithPrimeLength); // intersection包含"two", "three", "seven"
		intersection.immutableCopy();// 可以使用交集，但不可变拷贝的读取效率更高

		// 笛卡尔积
		Set<String> animals = ImmutableSet.of("gerbil", "hamster");
		Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");
		Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
		// {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},
		// {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}
		// 子集
		Set<Set<String>> animalSets = Sets.powerSet(animals);
		// {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}

		Set<String> strings = ImmutableSet.of("a", "aa", "aaa");
		ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(
				strings, new Function<String, Integer>() {
					public Integer apply(String string) {
						return string.length();
					}
				});
		System.out.println(stringsByIndex.get(2));

		Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
		Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
		MapDifference<String, Integer> diff = Maps.difference(left, right);
		System.out.println(diff.entriesInCommon()); // {"b" => 2}
		System.out.println(diff.entriesOnlyOnLeft()); // {"a" => 1}
		System.out.println(diff.entriesOnlyOnRight()); // {"d" => 5}

		Multiset<String> multiset1 = HashMultiset.create();
		multiset1.add("a", 2);
		Multiset<String> multiset2 = HashMultiset.create();
		multiset2.add("a", 5);
		multiset1.containsAll(multiset2); // 返回true；因为包含了所有不重复元素，虽然multiset1实际上包含2个"a"，而multiset2包含5个"a"
		Multisets.containsOccurrences(multiset1, multiset2); // returns false
		multiset2.remove(multiset1); // multiset2 现在包含3个"a"
		multiset2.removeAll(multiset1);// multiset2移除所有"a"，虽然multiset1只有2个"a"
		multiset2.isEmpty(); // returns true

		Multiset<String> multiset = HashMultiset.create();
		multiset.add("a", 3);
		multiset.add("b", 5);
		multiset.add("c", 1);
		ImmutableMultiset highestCountFirst = Multisets
				.copyHighestCountFirst(multiset);
		// highestCountFirst，包括它的entrySet和elementSet，按{"b", "a", "c"}排列元素

		// 字符串按长度分组
		ImmutableSet digits = ImmutableSet.of("zero", "one", "two", "three",
				"four", "five", "six", "seven", "eight", "nine");
		Function<String, Integer> lengthFunction = new Function<String, Integer>() {
			public Integer apply(String string) {
				return string.length();
			}
		};
		ImmutableListMultimap<Integer, String> digitsByLength = Multimaps
				.index(digits, lengthFunction);

		ArrayListMultimap<String, Integer> multimap = ArrayListMultimap
				.create();
		multimap.putAll("b", Ints.asList(2, 4, 6));
		multimap.putAll("a", Ints.asList(4, 2, 1));
		multimap.putAll("c", Ints.asList(2, 5, 3));
		TreeMultimap<Integer, String> inverse = TreeMultimap.create();
		Multimaps.invertFrom(multimap, inverse);
		System.out.println(inverse);

		Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 1, "c", 2);
		SetMultimap<String, Integer> multimap2 = Multimaps.forMap(map);
		System.out.println(multimap2);

		ListMultimap<String, Integer> myMultimap = Multimaps.newListMultimap(
				Maps.<String, Collection<Integer>> newTreeMap(),
				new Supplier<LinkedList<Integer>>() {
					public LinkedList<Integer> get() {
						return Lists.newLinkedList();
					}
				});

		// 使用LinkedHashMaps替代HashMaps
		Table<String, Character, Integer> table = Tables.newCustomTable(
				Maps.<String, Map<Character, Integer>> newLinkedHashMap(),
				new Supplier<Map<Character, Integer>>() {
					public Map<Character, Integer> get() {
						return Maps.newLinkedHashMap();
					}
				});

	}

	private static void newInstance() {
		List<String> list = Lists.newArrayList();
		Map<Object, String> map = Maps.newLinkedHashMap();
		Set<Integer> copySet = Sets.newHashSet(1, 2, 3);
		List<String> theseElements = Lists.newArrayList("alpha", "beta",
				"gamma");

		List<String> exactly100 = Lists.newArrayListWithCapacity(100);
		List<String> approx100 = Lists.newArrayListWithExpectedSize(100);
		Set<String> approx100Set = Sets.newHashSetWithExpectedSize(100);

		Multiset<String> multiset = HashMultiset.create();

	}

}
