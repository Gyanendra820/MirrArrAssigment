# Nasa Picture of the Day
https://apod.nasa.gov/apod/astropix.html

## NASA APIs ##
This app generates requests based on NASA's  service:
https://api.nasa.gov/

### *Sign up for your API key at:* ###
https://api.nasa.gov/index.html#apply-for-an-api-key

### *An example query:* ###
https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY

The DEMO_KEY should be changed at **line 64** of **MainActivity.java** *(mUrlRequestDefaultKey)*



#### App Features: ####
- Allow users to swipe down to refresh the list and update the list.
- Displays date, explanation, title and the image of the day.


#### Extras: ####
- Video player library
- Handling for different screen sizes and orientations.


## Screenshots ##
#### *Select any date and fetch it's corresponding picture information.* ####


#### *Support for orientation:* ####

![](images/MainPageLandscape.jpg)


#### Dependencies ####

- *[Retrofit](Retrofit/).* An HTTP library that makes networking for Android apps easier.
- *[Glide](https://square.github.io/glide/).* A powerful image downloading and caching library for Android.
- *[Gson](https://github.com/google/gson).* Convert Java Objects into their JSON representation.

*Note: This app uses HTTP requests, and sometimes it may return a timeout. "View" again to make a request for metadata.*
