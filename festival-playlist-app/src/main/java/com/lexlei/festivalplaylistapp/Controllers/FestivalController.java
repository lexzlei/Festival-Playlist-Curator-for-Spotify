package com.lexlei.festivalplaylistapp.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexlei.festivalplaylistapp.Models.Festival;
import com.lexlei.festivalplaylistapp.Service.FestivalService;

@RestController
@RequestMapping(path="/api/festival")
public class FestivalController {
    @Autowired
    private FestivalService festivalService;

    @PostMapping("/search/{festivalName}/{year}")
    public Festival addFestival(@PathVariable String festivalName,
                                @PathVariable Integer year) {
        
        return festivalService.searchFestival(festivalName, year);
    }

    @GetMapping("/festival")
    public List<Festival> getAllFestivals() {
        return festivalService.getAllFestivals();
    }

    @GetMapping("/festival/{id}")
    public Optional<Festival> getFestivalById(@PathVariable Integer id) {
        return festivalService.getFestivalById(id);
    }

    @DeleteMapping("/festival/{id}")
    public void deleteFestival(@PathVariable Integer id) {
        festivalService.deleteFestival(id);
    }
}
