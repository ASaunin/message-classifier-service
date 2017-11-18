package com.asaunin.classifier;

import com.asaunin.cache.Cacheable;
import com.asaunin.classifier.repository.LoadableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ClassifierCacheFactory extends CacheFactory<Cacheable, LoadableRepository> {

    public ClassifierCacheFactory(@Autowired Consumer<Builder<Cacheable, LoadableRepository>> builder) {
        super(builder);
    }

}
