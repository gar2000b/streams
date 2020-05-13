package com.onlineinteract;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Streams2 {
	public static void main(String[] args) {
		new Streams2().samples();
	}

	private void samples() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		System.out.println("Sample 1 - filter and return first element:\n");
		int result = numbers.stream()
				.filter(number -> number > 5)
				.filter(number -> isEven(number))
				.filter(number -> number < 9)
				.filter(number -> number * 2 > 15)
				.findFirst()
				.get();
		System.out
				.println(result);
		System.out.println();

		System.out.println("Sample 2 - map, reduce adding up all elements:\n");
		Integer result2 = numbers.stream()
				.filter(e -> e % 2 == 0)
				.map(e -> e * 2)
				.peek(System.out::println)
				.reduce(0, (carry, e) -> carry + e);
		System.out.println("\n" + result2 + "\n");

		System.out.println("Sample 3 - map to int, reduce (with sum) adding up all elements:\n");
		Integer result3 = numbers.stream()
				.filter(e -> e % 2 == 0)
				.mapToInt(e -> e * 2)
				.peek(System.out::println)
				.sum();
		System.out.println("\n" + result3 + "\n");

		System.out.println("Sample 4 - map to double, reduce (with sum) adding up all elements:\n");
		Double result4 = numbers.stream()
				.filter(e -> e % 2 == 0)
				.mapToDouble(e -> e * 2.0)
				.peek(System.out::println)
				.sum();
		System.out.println("\n" + result4 + "\n");

		System.out.println("Sample 5 - double the even values and put that into a list:\n");
		List<Integer> doubleOfEven = numbers.stream()
				.filter(e -> e % 2 == 0)
				.map(e -> e * 2)
				.collect(toList());
		System.out.println("\n" + doubleOfEven + "\n");

		System.out.println("Sample 6 -  create a map with name and age as key, and the person as value:\n");
		List<Person> people = createPeople();
		Map<String, Person> personMap = people.stream()
				.collect(toMap(
						person -> person.getName() + person.getAge(),
						person -> person));
		System.out.println("\n" + personMap + "\n");
	}

	private static List<Person> createPeople() {
		return Arrays.asList(
				new Person("Sara", Gender.FEMALE, 20),
				new Person("Sara", Gender.FEMALE, 22),
				new Person("Bob", Gender.MALE, 20),
				new Person("Paula", Gender.FEMALE, 32),
				new Person("Paul", Gender.MALE, 32),
				new Person("Jack", Gender.MALE, 2),
				new Person("Jack", Gender.MALE, 72),
				new Person("Jill", Gender.FEMALE, 12));
	}

	private static boolean isEven(int i) {
		return (i % 2) == 0;
	}

	enum Gender {
		MALE,
		FEMALE
	}

	static class Person {
		String name;
		Gender gender;
		int age;

		public Person(String name, Gender gender, int age) {
			this.name = name;
			this.gender = gender;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Gender getGender() {
			return gender;
		}

		public void setGender(Gender gender) {
			this.gender = gender;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", gender=" + gender + ", age=" + age + "]";
		}
	}
}
