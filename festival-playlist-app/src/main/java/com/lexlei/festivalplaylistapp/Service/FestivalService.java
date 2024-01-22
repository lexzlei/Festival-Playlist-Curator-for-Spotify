package com.lexlei.festivalplaylistapp.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexlei.festivalplaylistapp.Models.Festival;
//import com.lexlei.festivalplaylistapp.Repositories.ArtistRepository;
import com.lexlei.festivalplaylistapp.Repositories.FestivalRepository;

/**
 * Service class for handling Festival entity.
 * This class includes methods for creating, updating, deleting,
 * and fetching Festival details and their playlists.
 */
@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;
    //private final ArtistRepository artistRepository;

    @Autowired
    public FestivalService(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    //@Autowired
    //public ArtistService(ArtistRepository artistRepository) {
        //this.artistRepository = artistRepository;
    //}

    /**
     * Adds a new festival.
     * @param festival the festival to create.
     * @return the created festival.
     */
    public Festival addFestival(Festival festival) {
        return festivalRepository.save(festival);
    }

    /**
     * Retrieves all Festivals.
     * @return a list of all Festivals.
     */
    public List<Festival> getAllFestivals() {
        Iterable<Festival> festivals = festivalRepository.findAll();
        return StreamSupport.stream(festivals.spliterator(),false)
                            .collect(Collectors.toList());
    }

    /**
     * Retrieves a Festival by ID.
     * @param id the ID of the Festival to retrieve.
     * @return an Optional containing the Festival if found.
     */
    public Optional<Festival> getFestivalById(Integer id) {
        return festivalRepository.findById(id);
    }
    
    /**
     * Deletes a Festival by ID.
     * @param id the ID of the Festival to delete.
     */
    public void deleteFestival(Integer id) {
        festivalRepository.deleteById(id);
    }
}
