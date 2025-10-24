package pasquale.alberico.GestioneEventi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pasquale.alberico.GestioneEventi.DTO.NewEventDTO;
import pasquale.alberico.GestioneEventi.Entities.Event;
import pasquale.alberico.GestioneEventi.Services.EventService;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public Page<Event> all(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "date") String sortBy) {
        return eventService.findAll(page, size, sortBy);
    }
    @GetMapping("/{id}")
    public Event get (@PathVariable UUID id) {
        return eventService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@RequestBody @Validated NewEventDTO dto){
        return eventService.create(dto);
    }
    @PutMapping("/{id}")
    public Event update(@PathVariable UUID id, @RequestBody NewEventDTO dto) {
        return eventService.update(id, dto);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){ eventService.delete(id);
    }
}

