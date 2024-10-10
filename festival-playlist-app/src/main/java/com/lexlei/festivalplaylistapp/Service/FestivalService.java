package com.lexlei.festivalplaylistapp.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexlei.festivalplaylistapp.Models.Festival;
import com.lexlei.festivalplaylistapp.Repositories.FestivalRepository;

/**
 * Service class for handling Festival entity.
 * This class includes methods for creating, updating, deleting,
 * and fetching Festival details and their playlists.
 */
@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;

    @Autowired
    public FestivalService(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
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
        String searchUrl = "";
            //"https://www.musicfestivalwizard.com/?s=" + 
            //festivalName + "+" + String.valueOf(year);
        //System.out.println(searchUrl);

        String driverPath = getClass().getClassLoader().getResource("chromedriver").getPath().replace("%20", " ");
        
        // Setting up Selenium WebDriver
        System.setProperty("webdriver.chrome.driver", driverPath);
        
        // Set up ChromeOptions to enable headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Enable headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        WebDriver driver = new ChromeDriver(options);

        try {

            // Navigate to the search page
            driver.get("https://www.musicfestivalwizard.com/?s=Search");

            // Find the search input field and enter the festival name
            WebElement searchBox = driver.findElement(By.id("s"));
            searchBox.sendKeys(festivalName);

            // Wait for the dropdown to populate
            Thread.sleep(500);

            // Locate the first suggestion in the dropdown
            List<WebElement> suggestions = driver.findElements(By.cssSelector(".suggestion-item"));
            suggestions.get(0).click(); // Click the first suggestion

            // Get the current URL after clicking the suggestion
            searchUrl = driver.getCurrentUrl();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit(); // Close the browser session
        }

        try {
            // parses the webpage into HTML
            // Need to use a userAgent because the webpage blocks scraper bots
            if (!searchUrl.isEmpty()){
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
                Set<String> artistSet = new LinkedHashSet<>(); // Instantiate set of artists so no duplicates are added
                
                // Check if artist elements was found
                if (artistElements != null) {
                    // Iterate through all list items in the element (artists)
                    for (Element artistElement : artistElements) {
                        // Check if list item contains an <a> tag
                        if (artistElement.select("a").size() > 0) {
                            artistSet.add(artistElement.select("a").first().text());
                        } else {
                            artistSet.add(artistElement.text());
                        }
                    }
                } else {
                    System.out.println("No artists found");
                }
                List<String> artists = new ArrayList<>(artistSet); // Conver set of artists into list
                festival.setArtists(artists);
            }
            else {
                System.out.println("No valid festival page URL found");
                festival.setIsFound(false);
            }
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
