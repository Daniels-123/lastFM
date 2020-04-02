# LastFm-API-SQLite-Retrofit-customAdapterExample-Java-Android
In this repo you will find a mix of various android technologies!

![alt text](https://github.com/dancar97/LastFm-API-SQLite-Retrofit-customAdapterExample-Java-Android/blob/master/app/debug/WhatsApp%20Image%202020-04-02%20at%201.03.46%20AM.jpeg)

![alt text](https://github.com/dancar97/LastFm-API-SQLite-Retrofit-customAdapterExample-Java-Android/blob/master/app/debug/WhatsApp%20Image%202020-04-02%20at%201.03.46%20AM%20(2).jpeg)


---

# Basic workflow:

---

***
Retrofit and SQLite
***

I retrieved the information using Retrofit, the way that the data is stored starts in retroFitCalls class. wich is in the root of the package.

With retroFitCalls I get the info from the Retrofit library. There you will find two methods. The first one will bring Artists information, the second one will bring Tracks info. In both methods you can see a DB implementation using SQLite (Using a DBHandler class).

Given the scope of the project I decided to only have 'add' and 'read' functions on the database.

So: In retroFitCalls I get the info and there I store the data in an Artist table and in a Tracks table.

***
UI and flow of information
***

I didn't wanted to use too much fragments or memory, so I based the app on the default android tabbed activity. So the main part of the code will be found in PlaceHolderFragment class.

There I use a simple parameter to differ between two states, depending on the state I'll show one ListView or another.

Every listview has its own Custom Adapter.

Unfortunately, Lastfm wont let me use images anymore (Something to do with bad use from previous users). nevertheless I still used Picasso to bring the images from the internet (Wich right now is just a simple star for every object.)
