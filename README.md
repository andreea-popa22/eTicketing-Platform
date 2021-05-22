# eTicketing Platform



​	This is a project for Advanced Object-Oriented Programming course using Java. It simulates an e-Ticketing Platform containing information about entities.tickets, entities.events, entities.locations and clients.

​	I implemented the following classes:

+ **Event** - abstract class

  | Abstract Method       | Description                                                  |
  | --------------------- | ------------------------------------------------------------ |
  | *calcMaxProfit()*     | returns a float representing the maximum profit of the event |
  | *calcEventDuration()* | returns a float representing the number of hours of the event |
  | *calcEventProfit()*   | returns a float representing the real profit of the event (subtracting the maximum profit and the costs of the event such as location fees, singers price, etc) |

  Subclasses:

  + **Concert**
    + **Singer** : aggregation
  + **Play**
    + **Actor** : aggregation
  + **Conference**

+ **Location** - abstract class
  + **Arena**
  + **Outdoor**
  + **Theatre**
+ **Client** - abstract class
  + **Adult**
  + **Child**
+ **Organizer**
+ **Ticket**  -> includes method calcPrice() that applies 50% discount to children entities.tickets (the method assures that the discount cannot be applied multiple times)

All the classes have constructors, setters, getters and equals, hashCode, toString methods overridden.

Auxiliary classes:

+ **Phone** - phone number class designed using regex pattern validation
+ **Hour** - class having Integers for hour and minutes

+ **TicketsBought** - singleton class that stores a HashMap<Event, Integer> for keeping track of the number of entities.tickets bought for every event
+ **Service** - singleton class containing different methods called in the menu to demonstrate the use of classes and the project features.
+ **Main** - class that simulates the user menu

​    In the Main class, there are several objects declared at the beginning so that I can display the project features in the menu (listing the existing entities.locations for example). 

​	As a **client**, you can: 

1. *buy a ticket* for an event (the ticket class instance is done automatically based on the information provided by the user)
2. *see available entities.events* (from a list that is updated in the classes constructors).

​    As an **organizer**, you can:

1. *add a concert*
2. *add a play*
3. *add a conference*
4. *see the entities.tickets bought until now*

**Service** methods:

| Method                                                    | Description                                                  |
| --------------------------------------------------------- | ------------------------------------------------------------ |
| ***stringToHour(String s)***                              | returns an Hour class object given a string as a parameter; it is used for processing the user input |
| ***addPhone()***                                          | creates a Phone class object                                 |
| ***addSinger()***                                         | creates a Singer class object; includes processing a string into Enum type |
| ***addActor()***                                          | creates an Actor class object                                |
| ***addLocation(String s)***                               | creates and returns an/a Arena/Outdoor/Theatre class object casted to Event type |
| ***listAllSingers()***                                    | lists all singers from the Service list that is updated in the Singer class constructor |
| ***listAllActors()***                                     | lists all actors from the Service list that is updated in the Actor class constructor |
| ***sortSingersByName(List <Singer> l)***                  | returns an alphabetical ordered list of singers (sorted collection) |
| ***sortActorsByName(List<Actor> l)***                     | returns an alphabetical ordered list of actors (sorted collection) |
| ***listSortedLocations(List<Location> l)***               | returns a list of sorted entities.locations in ascending order based on capacity; it uses compareTo overridden method from Comparable interface |
| ***addConcert(Organizer org)***                           | returns a new instance of Concert class based on the input data; it also calls another methods such as addLocation if the user wants to add a new location for the event, or listLocations if the user wants to assign an existing location to the event |
| ***addPlay(Organizer org)***                              | returns a new instance of Play class based on the input data; it also calls another methods such as addLocation if the user wants to add a new location for the event, or listLocations if the user wants to assign an existing location to the event |
| ***addConference(Organizer org)***                        | returns a new instance of Conference class based on the input data; it also calls another methods such as addLocation if the user wants to add a new location for the event, or listLocations if the user wants to assign an existing location to the event |
| ***addClient(String s)***                                 | returns an/a Adult/Child class instance casted to Client class |
| ***addOrganizer()***                                      | return a new Organizer class instance based on the input data |
| ***listEvents()***                                        | lists all the existing entities.events                                |
| ***readFromCSV(String option, String path)***             | generic method for reading objects information from CSV file; it returns an array of CSV corresponding class objects (singers / actors / arenas / outdoors / theatres); it is called by other methods such as addConcert, addPlay |
| ***writeToCSV(E object, String path)***                   | generic method for writing objects information in CSV file; String 'path' argument is used to create the FileWriter object for the prerequisite class |
| ***auditWrite(String ts, String object, String action)*** | writes in audit.csv file the action name, the object class which readToCSV / writeToCSV method was called for and also the timestamp; it is called in the previous mentioned methods using suitable arguments |

- For the first stage: All the addClass methods call the corresponding parameterized constructor so that the Service all_class lists are up to date. 
- For the second stage: Some of the all_class lists are not used anymore due to the CSV reading method

Java collections used:

1. List (in Service class, used for storing objects on categories)
2. HashMap (in TicketsBought class, used for keeping track of the number of entities.tickets bought for every event)