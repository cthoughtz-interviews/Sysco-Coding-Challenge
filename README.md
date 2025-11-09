
Approach:

The approach that I took when developing this app was to focus on using clean architecture. Clean architecture helps to ensure that the code is clean, readable and easy to maintain. One of the major trade offs to using clean architecture with dagger hilt is that it takes a lot of time to setup but after it is set up correctly is is very easy to add features or update code. I happen to use some version of clean architecture in every one of my personal projects. I typically don’t run into many issues setting it up. I also implemented pagination which helps in loading large chunks of data. My initial approach was not to use pagination because there are only 60 items but the endpoint is setup to show 10 items per query (page) and the best way to show all of the items was to use pagination. Another good thing about using pagination is that it uses its on version of MVI (View Model Intent) by default. This means that I do not have to create a sealed class and manage the state in the app (States: Success: Loading, Failure etc…). I also decided to call the picsumphoto endpoint directly in the view (imageView.load(“picsum photo image”). One of the major tradeoffs is that there is a possibility that it takes longer to load the image. It doesn’t often happen but I was able to get around that issue by having a secondary place holder that says loading (just incase the image take a little longer to load). If this was a production level app I would ask the backend engineer to handle that business logic on the backend. It is not a good practice to handle this stuff on the front end.

Instructions:

Pull the repo in and load it in android studio.

I have included a video, with a YouTube link for any non tech people that want to see the app. Thanks for your time.

Please click on the Image below to see a small demo (This demo is for the non tech people - Thanks)
[![ALT TEXT](https://img.youtube.com/vi/EtFmGWuqR3Q/0.jpg)](https://www.youtube.com/watch?v=EtFmGWuqR3Q)