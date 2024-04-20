package me.practice.concurrency.ch_11.ex_04_completable_usage_sample;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class PriceFinder {

    private final List<Shop> shops = Arrays.asList(
        new Shop("CoolPang"),
        new Shop("HMarket"),
        new Shop("12th Street"),
        new Shop("YouMakePrice"),
        new Shop("FBay"),
        new Shop("Anonymous Store 1"),
        new Shop("Anonymous Store 2"),
        new Shop("Anonymous Store 3"),
        new Shop("Anonymous Store 4"),
        new Shop("Anonymous Store 5"),
        new Shop("Anonymous Store 6"),
        new Shop("Anonymous Store 7"),
        new Shop("Anonymous Store 8"),
        new Shop("Anonymous Store 9"),
        new Shop("Anonymous Store 10"),
        new Shop("Anonymous Store 11"),
        new Shop("Anonymous Store 12"),
        new Shop("Anonymous Store 13"),
        new Shop("Anonymous Store 14"),
        new Shop("Anonymous Store 15"),
        new Shop("Anonymous Store 16"),
        new Shop("Anonymous Store 17"),
        new Shop("Anonymous Store 18"),
        new Shop("Anonymous Store 19"),
        new Shop("Anonymous Store 20"),
        new Shop("Anonymous Store 21"),
        new Shop("Anonymous Store 22"),
        new Shop("Anonymous Store 23"),
        new Shop("Anonymous Store 24"),
        new Shop("Anonymous Store 25"),
        new Shop("Anonymous Store 26"),
        new Shop("Anonymous Store 27"),
        new Shop("Anonymous Store 28"),
        new Shop("Anonymous Store 29"),
        new Shop("Anonymous Store 30"),
        new Shop("Anonymous Store 31")
    );

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public List<String> findPrices(String product) {
        return shops.stream()
            .map(shop -> String.format("%s 가격은 %.2f", shop.getName(), shop.getPrice(product)))
            .collect(Collectors.toList());
    }

    public List<String> findPricesAsParallel(String product) {
        return shops.parallelStream()
            .map(shop -> String.format("%s 가격은 %.2f", shop.getName(), shop.getPrice(product)))
            .collect(Collectors.toList());
    }

    public List<String> findPricesAsCompletable(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() ->
                String.format("%s 가격은 %.2f", shop.getName(), shop.getPrice(product))
            ))
            .toList();

        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    public List<String> findPricesAsCompletableWithExecutor(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() ->
                    String.format("%s 가격은 %.2f", shop.getName(), shop.getPrice(product)),
                executor
            ))
            .toList();

        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    public List<String> findPricesWithComposeOperation(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
            /*
            - 상점의 가격을 가져옴 (딜레이 1초).
            - executor를 주어 모든 작업이 1초에 끝나도록 함.
             */
            .map(shop -> CompletableFuture.supplyAsync(() ->
                    String.format("%s:%.2f:%s", shop.getName(), shop.getPrice(product), Discount.Code.DIAMOND),
                    executor
            ))
            /*
            - thenApply에서 Quote의 parse를 호출.
            - thenApply는 Completablefuture의 동작이 완료가 되어야 적용이 됨 (like synchronous mapping)
             */
            .map(future -> future.thenApply(Quote::parse))
            /*
            - 할인가격을 가져오는 원격 실행 (딜레이 1초). 이것을 연쇄적인 비동기로 실행.
            - thenCompose를 통해 두 번째 CompletableFuture는 첫 번째 CompletableFuture의 결과(List<CompletableFuture>)를 계산의 입력으로 받음.
             */
            .map(future -> future.thenCompose(quote ->
                CompletableFuture.supplyAsync(() ->
                        Discount.applyDiscount(quote),
                        executor
                )
            ))
            .toList();

        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

}
