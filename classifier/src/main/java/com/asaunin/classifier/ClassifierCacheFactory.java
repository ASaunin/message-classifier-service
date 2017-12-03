package com.asaunin.classifier;

import com.asaunin.cache.LoadableItemCache;
import com.asaunin.classifier.repository.LoadableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ClassifierCacheFactory extends CacheFactory<LoadableItemCache, LoadableRepository> {

    public ClassifierCacheFactory(@Autowired Consumer<Builder<LoadableItemCache, LoadableRepository>> builder) {
        super(builder);
    }

}
