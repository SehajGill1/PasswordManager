#Sehaj Gill, May 2025

# **JavaFX Password Manager**

A simple desktop Password Manager application built with **JavaFX** and **SQLite**. This project allows users to securely store, retrieve, and delete login credentials locally using an intuitive graphical interface.

# **Features**

- JavaFX-powered graphical user interface
- Encrypted password storage using custom encryption logic
- SQLite database integration for persistent local storage
- Add, view, and remove credentials with a clean UI

# **Requirements**

- Java **JDK 22** or higher
- [JavaFX SDK 24+](https://gluonhq.com/products/javafx/)
- SQLite JDBC driver (already included in `lib/`)

# **Setup Instructions (Windows CMD)**

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/PasswordManager.git
   cd PasswordManager

2. Set JavaFX SDK path

   set PATH_TO_FX=C:\path\to\javafx-sdk-24.0.1\lib
   cd src

3. Compile the code
	
   javac --module-path %PATH_TO_FX% --add-modules javafx.controls -cp "..\lib\sqlite-jdbc-3.50.1.0.jar" *.java

4. Run the application

   java --module-path %PATH_TO_FX% --add-modules javafx.controls -cp ".;..\lib\sqlite-jdbc-3.50.1.0.jar" Main

# **HOW TO USE**

Launch the app.
   Enter:
	Site
	Username
	Password (stored encrypted)
   Click:
	"Add Credential" to save
	"View All Credentials" to list entries
	"Remove Selected" to delete an entry (select from list)

# **Preview**


Sehaj Gill
Bachelor of Science in Software Engineering
The University of Texas at Dallas (2025)
https://www.linkedin.com/in/sehajsgill/
