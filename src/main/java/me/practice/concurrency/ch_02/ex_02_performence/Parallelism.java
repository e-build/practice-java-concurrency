package me.practice.concurrency.ch_02.ex_02_performence;

import java.util.ArrayList;
import java.util.List;

public class Parallelism {
	public static void main(String[] args) {

		int cpuCores = Runtime.getRuntime().availableProcessors();
		System.out.println("CPU core 개수: " + cpuCores);

		// CPU 개수를 초과하는 데이터를 병렬로 처리
		long startTime1 = System.currentTimeMillis();
		final long sum1 = calculateParallel(cpuCores);
		long endTime1 = System.currentTimeMillis();

		System.out.println("CPU core 개수의 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime1 - startTime1) + "ms");

		// CPU 개수를 초과하는 데이터를 병렬로 처리
		long startTime2 = System.currentTimeMillis();
		final long sum2 = calculateParallel(cpuCores * 2);
		long endTime2 = System.currentTimeMillis();

		System.out.println("CPU core 2배 개수의 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime2 - startTime2) + "ms");
	}

	private static long calculateParallel(int cpuCores) {
		List<Integer> data = new ArrayList<>();
		for (int i = 0; i < cpuCores; i++) {
			data.add(i);
		}
		return data.parallelStream()
			.mapToLong(i -> {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				return 0;
			})
			.sum();
	}
}
