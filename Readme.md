# Documentation - Web Scraping Weather Project

## Introduction
Our project revolves around web scraping, specifically scraping weather data from the website: [Weather.com](https://weather.com/da-DK/weather/tenday/l/0f6aae8ce85a3260606af04be65d1465950d18e7003a8ae18d7282ac31e68bb0).

In order to gather data for our Data Transfer Object, we encountered the complication of scraping data from a webpage that was partially built with JavaScript and HTML. Portions of the information on the site are rendered in HTML while others in JavaScript. JSoup cannot retrieve data from JavaScript, hence we were obliged to only collect data presented in the HTML code.

Consequently, we revised our DTO class to utilize the data retrieved from HTML, instead of the data loaded through JavaScript.

## Edge Cases and Error Handling

### API
We utilize an API to enrich the data we scrape from our chosen website. A possible edge case might arise, for example, if the API temporarily goes down or is removed entirely one day. This would complicate matters as we would no longer be able to create instances of our ApiWeatherDTO to fetch necessary data to create entities, for instance, currentWeatherData which is also used in the weatherDTO.

To handle an edge case, we should structure the logic in such a way that the user is informed as soon as there are issues with communicating or retrieving data from the API. At the same time, it should try to re-establish the connection at a later time if the API becomes functional again after a potential outage.

We could also face the situation where the API gets altered, and the data we receive from an external source does not align with our integration of it. Problems would arise if other parameters or units are added different from what we have set the program to. It would pose a challenge as we cannot persist new data since we can't instantiate entities, and DTOs the same way.

To avoid this, we must validate the data we receive every time from the external source against our model, and develop some catch and exception cases that can handle receiving incorrect data types or Null Pointer Exceptions. The location we search on may return errors if the API does not recognize it, hence the user should be informed that it's a location we currently cannot provide forecasts for.

### Database
If the database goes down or we are unable to establish a connection, it implies that we cannot persist and find the entities that need to be used/created.

## ERD Diagram


## Pending Tasks
- Implementation of DAO interface.
- Testing of our DAO and service/mapper.

## Web Pages Used
- Scraping: [The Weather Channel - Weather.com](https://weather.com/da-DK/weather/tenday/l/3db22edf37b8d9f9697634cdfeb7851a07cbf48f6242e88af15d5aea121fc62e)
- API Documentation: [Vejr.eu API](https://vejr.eu/pages/api-documentation)
- [Vejr.eu](https://vejr.eu/)