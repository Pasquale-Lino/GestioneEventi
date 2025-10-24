package pasquale.alberico.GestioneEventi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pasquale.alberico.GestioneEventi.DTO.NewEventDTO;
import pasquale.alberico.GestioneEventi.Entities.Event;
import pasquale.alberico.GestioneEventi.Exceptions.NotFoundException;
import pasquale.alberico.GestioneEventi.Repositories.EventRepository;

import java.util.UUID;

@Service
public class EventService {
    @Autowired private EventRepository eventRepository;
    public Page<Event> findAll(int page, int size, String sortBy){
        Pageable p= PageRequest.of(page,size, Sort.by(sortBy));
        return eventRepository.findAll(p);
    }
    public Event findById(UUID id){
        return eventRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public Event create(NewEventDTO dto){
        Event e=new Event();
        e.setTitle(dto.title());
        e.setDescription(dto.description());
        e.setDate(dto.date());
        e.setLocation(dto.location());
        e.setSeatsAvailable(dto.seatsAvailable());
        e.setOrganizerId(dto.organizerId());
        return eventRepository.save(e);
    }
    public Event update(UUID id, NewEventDTO dto){
        Event e = findById(id);
        e.setTitle(dto.title());
        e.setDescription(dto.description());
        e.setDate(dto.date());
        e.setLocation(dto.location());
        e.setSeatsAvailable(dto.seatsAvailable());
        return eventRepository.save(e);
    }
    public void delete(UUID id){
        eventRepository.deleteById(id);
    }
}
