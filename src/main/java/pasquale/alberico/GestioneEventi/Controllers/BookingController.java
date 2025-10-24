package pasquale.alberico.GestioneEventi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pasquale.alberico.GestioneEventi.DTO.NewBookingDTO;
import pasquale.alberico.GestioneEventi.Entities.Booking;
import pasquale.alberico.GestioneEventi.Services.BookingService;

import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired private BookingService bookingService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@RequestBody @Validated NewBookingDTO dto){
        return bookingService.create(dto);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable UUID id){
        bookingService.cancel(id);
    }
}
