##Pangea - Video Broadcasting Application

*By Nasser Eledroos & Mike Charpentier*

###Problem Description

In our world’s hyper populated social media craze, it’s hard to find a medium that will guarantee an audience and consistently deliver one to the user. Our idea then, is to build a system that would essentially guarantee an audience to the user and consistently allow audience members to see something new each time the application is opened.

The catch then, is that it’s intended to be built as a social experiment. In the sense that the user who is chosen to broadcast, is chosen completely at random by the system from the complete user base. This sense of random anonymity really heightens the idea that you have no idea who your next broadcaster will be, or from where. 

###Analysis

Our research involved looking at the UX and technologies that existing video sharing platforms work – and how they deliver content to their user base. To research designs, a really helpful resource is: http://www.mobile-patterns.com/ - where the latest splash screens and UI components used in all of today’s apps are available for viewing.

###Design

Prototyping began on paper, with ideas taken from other apps. The initial idea was that an app drawer would hold the majority of the navigation of the application – as that is a very current design trend that allows for simplification of navigation. There would be 3 main “Activities”, each that held a significant portion of the app.

The “Video” Activity would hold the video that the broadcaster has decided to send over the source of the 24 hours that they were allowed to broadcast. The Information page would hold the user’s bio, and a very brief information about where the video is coming from (Location). It would not be very specific as to give away the users identity, but instead to educate the user base about the location. For example, if I were to broadcast from Boston – the video information page would show the State of Massachusetts, and have information about the state in general. 

The “Video” Activity would not be viewable by just everyone, however. The way we had prototyped it, the user that has become the broadcaster would see that option appear in their Navigation Drawer. This is different from how we actually implemented it, but we’ll get to that later. There would be a login & sign-up screen which would allow the user to create an account (or login through Social Media).

###Implementation

If you have used the app by now, you’ll have seen the fact that we didn’t actually implement a navigation drawer. By looking at design concepts, I found that the Android “Fragments” were a much simpler way of 
handling the design and UX of our app.

The Fragments are essentially Activities that are nested within a parent Activity. In our case, that was MainActivity.java. So this means that Style and design would be handled by the parent class, and then we can work on our individual Fragments that would work together to provide the core of the application.

The backend was done with Parse. We found that working with video is extraordinarily hard (must be why developers working at video startups get paid top dollar). You have to learn to handle compression, since the limit of the video in size is 10mb. This was limiting to our project because the proposition is that user has up to 1 minute of video to broadcast. Obviously with the Parse limitation we could not do that. So our limitation is set to 10 seconds, similar to that of SnapChat. Sending and receiving of the video is done through the ParseObject, which handles all multimedia files as a series of bytecode. 

Our 3rd Fragment, the broadcast page – was intended to be invisible to the user. Instead what we opted for (for technical logistics, to get the app working) was to leave the BroadcastFragment completely viewable, but password protect it. So what this means for our implementation is that we would build a server side application that, every day, would change the password once the previous day’s user is finished. It would then query the database and pick a new user from the list of existing users that have not yet been picked. Taking that user ID, it would send them via email, the new password to access their broadcasting page. The password will also be set through a push notification. The server side application has not yet been implemented as it is outside the scope of this class. The push notification was not implemented for the sake of time.

So currently, the way we have it is that the User can record a video (Password: 1234) and send it to Parse. When they return to the VideoFragment they’ll find that the video they just uploaded plays and loops. The idea is that if we had more funding and time, and were able to implement the server side application, then the looping video would actually be a series of videos uploaded over the course of the day – looping indefinitely as long as the application is open.