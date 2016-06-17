package com.xxx.demo.guava.basic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.common.base.Throwables;

public class ThrowablesDemo {
	public static void main(String[] args) throws Exception {
		try {
			System.out.println();
		} catch (Throwable t) {
			// Throwable类型为X才抛出
			Throwables.propagateIfInstanceOf(t, IOException.class);
			Throwables.propagateIfInstanceOf(t, SQLException.class);

			// Throwable类型为Error或RuntimeException才抛出
			Throwables.propagateIfPossible(t, Error.class);
			Throwables.propagateIfPossible(t, RuntimeException.class);
			Throwables.propagateIfPossible(t);

			// 如果Throwable是Error或RuntimeException，直接抛出；否则把Throwable包装成RuntimeException抛出。
			throw Throwables.propagate(t);
		}

		try {
			System.out.println();
		} catch (Throwable t) {
			System.out.println(Throwables.getRootCause(t));
			List<Throwable> throwableList = Throwables.getCausalChain(t);
			System.out.println(Throwables.getStackTraceAsString(t));
		}

	}
}
