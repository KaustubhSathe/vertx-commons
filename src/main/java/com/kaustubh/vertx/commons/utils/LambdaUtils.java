package com.kaustubh.vertx.commons.utils;

import java.util.function.Function;

public class LambdaUtils {
    public static <V, T> Function<V, T> pipe(Function<V, T> f1) {
        return f1;
    }

    public static <V, T, S> Function<V, S> pipe(Function<? super V, ? extends T> f1, Function<? super T, S> f2) {
        return f2.compose(pipe(f1));
    }

    public static <V, T, S, R> Function<V, R> pipe(Function<? super V, ? extends T> f1,
                                                   Function<? super T, ? extends S> f2,
                                                   Function<? super S, R> f3) {
        return f3.compose(pipe(f1, f2));
    }
}
