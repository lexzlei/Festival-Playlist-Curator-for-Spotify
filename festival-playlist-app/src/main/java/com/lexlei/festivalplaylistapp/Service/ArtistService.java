/**
 * THIS FILE IS CURRENTLY UNUSED UNTIL FURTHER FUNCTIONALITY IMPLEMENTATION.
 */

package com.lexlei.festivalplaylistapp.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexlei.festivalplaylistapp.Models.Artist;
import com.lexlei.festivalplaylistapp.Repositories.ArtistRepository;
import com.lexlei.festivalplaylistapp.Repositories.FestivalRepository;
import com.lexlei.festivalplaylistapp.Repositories.SongRepository;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final FestivalRepository festivalRepository;
    private final SongRepository songRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, FestivalRepository festivalRepository
                        , SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.festivalRepository = festivalRepository;
        this.songRepository = songRepository;
    }

    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public List<Artist> getAllArtists() {
        Iterable<Artist> artists = artistRepository.findAll();
        return StreamSupport.stream(artists.spliterator(),false)
                            .collect(Collectors.toList());
    }
}
