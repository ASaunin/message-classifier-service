package com.asaunin.classifier.service;

import java.time.ZonedDateTime;

public interface Loadable {

    void loadData() throws Exception;

    void updateAfter(ZonedDateTime dateTime) throws Exception;

}