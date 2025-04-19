# Nowruz_Project
📌Project title :
Lyrics App


📝 Description
This project is a Java-based music platform that supports multiple user roles and interactive features. It allows users to register and log in as different account types—User, Artist or Admin—each with specific functionalities.

Users can explore songs, follow artists,search song or artist  , ask questions, like/dislike musics and comments , visit their last searche (songs name),suggestion music , top 5 music in the App ,Request Lyrics Edit ,New Music add by followed artists ,View all songs in the app  , and leave comments.

Artists can upload or remove songs/Album, write lyrics or change lyrics by themslves or Accept request by user about change lyrics  , add collabratore , and answer questions submitted by users about their songs.

Admins can approve or reject requests from users to become Artists or Admins, ensuring control over content creators and platform integrity and Accept change lyrics request by user whyen Main artist diactive .

The application also includes:

A Question & Answer system for songs

A like/dislike system and commenting for Song  and like/dislike comments

Password validation and email checks(Regex) during registration for better security

Approval workflows for sensitive roles like artists and administrators

This project is built with an object-oriented design approach, encouraging modularity, extensibility, and clarity in code structure.

⚙️ Features
List the key features of your application, like:

Account registration and login system(use regex)

Accounts (Admin, Artist , user) they are extands account class 

Artist and new Admin approval by admin(we have main admin when the program start by defult)
if Admins Approve new admin , new admin can approve new admin and artist

Account dashboards for different roles (User, Artist, Admin)

Music management (songs, albums, lyrics)
(Add lyrics , like/dislike musics , get comments , viwes , information about songs , systeam for search by title of the song or artist name)

comments Manager for like/dislike

colored output and use unicode for dashboards menu and main menu

🏗️ Architecture

org.NowruzProject
├── Accounts (User, Artist, Admin , (enum)Account type)
├── Answer & Question(Answer , Question , QuestionManager)
├── Music(Songs, Albums, (enum) genre , MusicManager )
├── Dashboards (Artist/User/Admin Dashboard)
├── Comments



📚 Java Library Usage
java.util.*
Purpose: Provides access to essential utility classes like Map, HashMap, Scanner, and Collections.

Usage in Project:

Managing user accounts via Map<String, Account>.

Handling lists of songs, comments with List, ArrayList.

Getting user input from the console with Scanner.

java.util.regex.*
Purpose: Offers classes for pattern matching using regular expressions.

Usage in Project:

Validating user inputs like email formats and password strength during registration using Pattern and Matcher.

java.time.LocalDateTime
Purpose: Provides date and time handling functionality.

Usage in Project:

Capturing timestamps for events such as comment creation .

java.util.ArrayList
Purpose: A resizable array implementation of the List interface.

Usage in Project:

Storing collections like songs, comments and pending account requests dynamically.

java.util.List
Purpose: An interface for ordered collections (lists).

Usage in Project:

Defining flexible and general-purpose lists that can be implemented by ArrayList or other list types.

java.util.Objects
Purpose: Utility class for operations on objects.

Usage in Project:

Safely checking for null values and comparing object references using methods like Objects.equals() or Objects.requireNonNull().


Bonus Tasks

like / dislike comment
Answer and Question about songs
Show the last 5 song searches
recommendation System based on followed artists
Show top 5 music in the App



## Main Menu  
![Main menu](images/Screenshot-2025-04-16-203053.png)
<img width="172" alt="Screenshot 2025-04-16 203053" src="https://github.com/user-attachments/assets/9aa75aa0-f7bc-43c7-aade-39092ca46726" />

## User Dashboard  

<img width="249" alt="Screenshot 2025-04-16 203434" src="https://github.com/user-attachments/assets/b580addf-e7cd-4683-b781-416de137a666" />
<img width="236" alt="Screenshot 2025-04-16 203455" src="https://github.com/user-attachments/assets/9df83d4f-a3ac-43ee-92e8-c3a93832da60" />

## Artist Dashboard  

<img width="257" alt="Screenshot 2025-04-16 203249" src="https://github.com/user-attachments/assets/0afdcff5-e33d-48e9-a7a4-251ed41df319" />


## Admin Dashboard  

<img width="277" alt="Screenshot 2025-04-16 203205" src="https://github.com/user-attachments/assets/00b629fd-3fb6-465f-a6e8-e6a39c9a45dc" />


🚀 Getting Started
1) Step-by-step instructions to get your project up and running:

2) Clone the repo
 https://github.com/PartowRoshani/NowruzMusic.git

3) Open in your favorite IDE (e.g., IntelliJ, Eclipse)

🔐 Security

Email and password validation on registration

🧑‍💻 Authors
Author Name: [Partow Roshani]
Email: [roshanipartow@gmail.com]
GitHub: [https://github.com/PartowRoshani]









