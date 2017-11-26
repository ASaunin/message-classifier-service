package com.asaunin.classifier.repository.mssql;

import com.asaunin.classifier.domain.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class RuleMsSqlRepositoryTest extends MsSqlRepositoryTestConfiguration {

    @Autowired private RuleMsSqlRepository ruleRepo;

    private ZonedDateTime updatedAt = ZonedDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());

    @Test
    public void findAll() {
        final Collection<Rule> list = ruleRepo.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    public void findByUpdatedAtAfter() {
        Collection<Rule> list = ruleRepo.findByUpdatedAtAfter(updatedAt);
        assertThat(list).isNotEmpty();
    }

}