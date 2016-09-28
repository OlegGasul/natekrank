package pl.natekrank.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Created by ievgenii on 22.09.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:appconfig-data.xml", "classpath:test-context.xml"})

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public abstract class BaseRepositoryTest {


}
