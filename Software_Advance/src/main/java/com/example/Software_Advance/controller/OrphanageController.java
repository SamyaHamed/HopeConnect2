package com.example.Software_Advance.controller;
import com.example.Software_Advance.models.Tables.Orphanage;
import com.example.Software_Advance.services.OrphanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Software_Advance.dto.OrphanageDto;
import java.util.List;

@RestController
@RequestMapping("/api/orphanages")
public class OrphanageController {

    @Autowired
    private OrphanageService orphanageService;

    @GetMapping("/verified/{status}")
    public List<Orphanage> getByVerified(@PathVariable boolean status) {
        return orphanageService.getOrphanagesByVerified(status);
    }

    @GetMapping("/capacity-greater-than/{value}")
    public List<Orphanage> getByCapacityGreaterThan(@PathVariable int value) {
        return orphanageService.getOrphanagesByCapacityGreaterThan(value);
    }

    @PutMapping("/{id}")
    public Orphanage updateOrphanage(@PathVariable Long id, @RequestBody Orphanage orphanage) {
        return orphanageService.updateOrphanage(id, orphanage);
    }

    @PutMapping("/{id}/capacity")
    public Orphanage updateOrphanageCapacity(@PathVariable Long id, @RequestBody OrphanageDto request) {
        return orphanageService.updateOrphanageCapacity(id, request.getCapacity());
    }
    @PutMapping("/{id}/orphan-count")
    public Orphanage updateOrphanCount(@PathVariable Long id, @RequestBody OrphanageDto request) {
        return orphanageService.updateOrphanCount(id, request.getOrphanCount());
    }


}