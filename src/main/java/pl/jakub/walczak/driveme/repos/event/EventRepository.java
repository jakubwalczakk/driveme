package pl.jakub.walczak.driveme.repos.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.event.Event;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {
}