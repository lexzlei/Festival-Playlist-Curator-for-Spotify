package com.lexlei.festivalplaylistapp.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexlei.festivalplaylistapp.Models.Festival;
import com.lexlei.festivalplaylistapp.Service.FestivalService;

/**
 * FestivalController Class
 * 
 * Controller class which handles the HTTP requests relating to music festivals.
 * Includes enpoints for searching, retrieving, and managing festival data.
 */
@RestController
@RequestMapping(path="/api/festival")
public class FestivalController {
    @Autowired
    private FestivalService festivalService;

    /**
     * Endpoint for searching a music festival by it's name name and year.
     * 
     * @param festivalName The name of the festival.
     * @param year The year of the festival.
     * @return Festival - A Festival object.
     */
    @PostMapping("/search/{festivalName}/{year}")
    public Festival addFestival(@PathVariable String festivalName,
                                @PathVariable Integer year) {
        
        return festivalService.searchFestival(festivalName, year);
    }

    /**
     * Endpoint for retrieving all festivals stored in the database.
     * 
     * @return List<Festival> - A list of all festivals.
     */
    @GetMapping("")
    public List<Festival> getAllFestivals() {
        return festivalService.getAllFestivals();
    }

    /**
     * Endpoint for retrieving a festival by ID.
     * 
     * @param id The ID of the festival.
     * @return Optional<Festival> - The Festival Object if found.
     */
    @GetMapping("/festival/{id}")
    public Optional<Festival> getFestivalById(@PathVariable Integer id) {
        return festivalService.getFestivalById(id);
    }

    /**
     * Enpoint for deleting a festival by its ID.
     * 
     * @param id The ID of the festival.
     */
    @DeleteMapping("/festival/{id}")
    public void deleteFestival(@PathVariable Integer id) {
        festivalService.deleteFestival(id);
    }
}
