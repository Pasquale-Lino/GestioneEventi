package pasquale.alberico.GestioneEventi.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import pasquale.alberico.GestioneEventi.Entities.Event;

import java.awt.print.Pageable;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    Page<Event> findAll(Pageable pageble);
}
