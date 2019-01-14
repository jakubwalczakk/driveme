package pl.jakub.walczak.driveme.repos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.repos.city.DrivingCityRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class DrivingCityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DrivingCityRepository drivingCityRepository;

    @Test
    public void findDrivingCityByNameThenReturnDrivingCity() {
        // given
        final String name = "Katowice";
        final String description = "Stolica województwa śląskiego. Jazda na rondzie nieopodal Spodka sprawi," +
                " że poczujesz się jak w innej galaktyce!";

        DrivingCity drivingCity = DrivingCity.builder().name(name)
                .description(description).active(true).build();
        entityManager.persistAndFlush(drivingCity);

        // when
        final DrivingCity drivingCityFromDB =
                drivingCityRepository.findByName(name).orElse(null);

        // then
        assertThat(drivingCityFromDB)
                .isEqualTo(drivingCity);
    }
}
