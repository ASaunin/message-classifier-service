package com.asaunin.cache;

import java.util.function.Function;

public class SimpleLoadableCache<K, V> extends LoadableCache<K, V, V> {

    public SimpleLoadableCache(Function<V, K> keyMapper) {
        super(keyMapper, Function.identity());
    }

}
