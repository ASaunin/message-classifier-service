package com.asaunin.classifier.repository;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@FunctionalInterface
public interface ParamSource<T> {

    SqlParameterSource mapParams(T entity);

}
