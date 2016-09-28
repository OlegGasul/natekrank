package pl.natekrank.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import pl.natekrank.model.Role;
import pl.natekrank.model.User;
import pl.natekrank.model.builder.ModelBuilderFactory;
import pl.natekrank.model.builder.RoleBuilder;
import pl.natekrank.model.builder.UserBuilder;

import java.util.Arrays;
import java.util.List;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static pl.natekrank.model.builder.ModelBuilderFactory.getBuilder;

/**
 * Created by ievgenii on 22.09.16.
 */
public class RoleRepositoryTest extends BaseRepositoryTest {

    @Autowired
    RoleRepository repository;

    @Test
    @DatabaseSetup(value = "classpath:data/roles.xml", type = DatabaseOperation.CLEAN_INSERT)
//    @ExpectedDatabase
    public void shouldFindAllRoles() throws Exception {

        //given
        User user = getBuilder(UserBuilder.class).withId(0).build();
        List<Role> expected = Arrays.asList(getBuilder(RoleBuilder.class).withUserId(0).withRole("Guest").build());

        //when
        List<Role> actual = repository.findAll();
        //then
        assertReflectionEquals(expected, actual, ReflectionComparatorMode.IGNORE_DEFAULTS);

    }
}
