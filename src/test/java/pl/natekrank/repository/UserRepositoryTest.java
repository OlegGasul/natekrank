package pl.natekrank.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.natekrank.model.User;
import pl.natekrank.model.builder.ModelBuilderFactory;
import pl.natekrank.model.builder.UserBuilder;

import java.util.Arrays;
import java.util.List;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static pl.natekrank.model.builder.ModelBuilderFactory.getBuilder;

/**
 * Created by ievgenii on 22.09.16.
 */
public class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    UserRepository repository;


    @Test
    @DatabaseSetup("users.xml")
    public void shouldFindAllRoles() throws Exception{
        //given
        List<User> expected = Arrays.asList(getBuilder(UserBuilder.class)
                .withId(0)
                .withEmail("user@user.com")
                .withPassword("Guest")
                .withPasswordConfirm("Guest")
                .build());

        //when
        List<User> actual = repository.findAll();

        assertReflectionEquals(expected, actual);

    }
}
