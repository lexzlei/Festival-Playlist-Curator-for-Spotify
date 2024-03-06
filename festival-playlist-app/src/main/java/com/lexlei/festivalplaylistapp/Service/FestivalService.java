package com.lexlei.festivalplaylistapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexlei.festivalplaylistapp.Models.Festival;
import com.lexlei.festivalplaylistapp.Repositories.ArtistRepository;
import com.lexlei.festivalplaylistapp.Repositories.FestivalRepository;

/**
 * Service class for handling Festival entity.
 * This class includes methods for creating, updating, deleting,
 * and fetching Festival details and their playlists.
 */
@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public FestivalService(FestivalRepository festivalRepository, ArtistRepository artistRepository) {
        this.festivalRepository = festivalRepository;
        this.artistRepository = artistRepository;
    }

    /**
     * Handles the festival search functionality of the web app.
     * Scraped the existing website "Music Festival Wizard" for festival data
     * such as name, location, and artist lineup. 
     * 
     * @param festivalName The name of the desired festival to search.
     * @param year The year of the desired festival.
     * @return Festival - The configured Festival object.
     */
    public Festival searchFestival(String festivalName, Integer year) {
        Festival festival = new Festival(); // Instantiate new Festival object
        festival.setYear(year);

        // The URL based on user input into the musicfestivalwizard website
        String searchUrl = "https://www.musicfestivalwizard.com/?s=" + 
            festivalName + "+" + String.valueOf(year);
        //System.out.println(searchUrl);

        try {
            // parses the webpage into HTML
            // Need to use a userAgent because the webpage blocks scraper bots
            Document doc = Jsoup.connect(searchUrl)
                               .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)") 
                               //.referrer("http://www.google.com")
                               .get();

            // Select the HTML link element using the CSS query which will give the correct URL to display the lineup
            Element linkElement = doc.select("#artist-lineup-container > div > header > div > h2 > a").first();

            String festivalPageUrl = ""; // Instantiate specific festival page url

            // Check if the element was found
            if (linkElement != null) {
                // Extracting the href indicating the URL destination of the hyperlink element
                festivalPageUrl = linkElement.attr("href");
                System.out.println(festivalPageUrl);
            } else {
                System.out.println("No link found");
                festival.setIsFound(false);
                return festival;
            }

            Document festivalPage = Jsoup.connect(festivalPageUrl)
                                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)") 
                                        .get();

            // Select the list elements containing festival info (artists, location, name)
            Elements artistElements = festivalPage.select("#content > div > div > div.grids > div > div:nth-child(3) > div:nth-child(2) > div.lineupblock > div > ul > li"); 
            Elements locationElement = festivalPage.select("#content > div > div > div.top-block > div.hubheadline > div > p:nth-child(3)");
            Elements nameElement = festivalPage.select("#content > div > div > div.top-block > div.hubheadline > div > h1 > a");
            festival.setLocation(locationElement.first().text()); // Set location of the festival
            festival.setFestivalName(nameElement.first().text()); // Set name of the festival
            List<String> artists = new ArrayList<>(); // Instantiate list of artists
            
            // Check if artist elements was found
            if (artistElements != null) {
                // Iterate through all list items in the element (artists)
                for (Element artistElement : artistElements) {
                    // Check if list item contains an <a> tag
                    if (artistElement.select("a").size() > 0) {
                        artists.add(artistElement.select("a").first().text());
                    } else {
                        artists.add(artistElement.text());
                    }
                }
            } else {
                System.out.println("No artists found");
            }
            festival.setArtists(artists);
            //for (String artistName : artists) {
                //System.out.println(artistName);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return festival; 
    }

    /**
     * Retrieves all Festivals.
     * 
     * @return List<Festival> - a list of all Festivals.
     */
    public List<Festival> getAllFestivals() {
        Iterable<Festival> festivals = festivalRepository.findAll();
        return StreamSupport.stream(festivals.spliterator(),false)
                            .collect(Collectors.toList());
    }

    /**
     * Retrieves a Festival by ID.
     * 
     * @param id the ID of the Festival to retrieve.
     * @return Optional<Festival> - an Optional containing the Festival if found.
     */
    public Optional<Festival> getFestivalById(Integer id) {
        return festivalRepository.findById(id);
    }
    
    /**
     * Deletes a Festival by ID.
     * 
     * @param id the ID of the Festival to delete.
     */
    public void deleteFestival(Integer id) {
        festivalRepository.deleteById(id);
    }

}
