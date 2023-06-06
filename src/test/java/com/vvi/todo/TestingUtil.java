package com.vvi.todo;

import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class TestingUtil {
	public static <T> List<T> mockList(final Class<T> clazz, final int amount) {
		return IntStream
			.range(0, amount)
			.boxed()
			.map(it -> Mockito.mock(clazz))
			.toList();
	}
}
