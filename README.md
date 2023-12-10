# MyKitchen

## Overview
MyKitchen is a java-based application that allows for users to have a one-stop shop for working with their current ingredients at home or planning ahead for products that may be needed at the store. The user has the opportunity to add, edit, and remove pantries (that represent items currently in their posession) and shopping carts (items that will be bought). Within each of these list structures, the user has the opportunity to add, edit, and delete items as needed. The items in each of these lists can be sorted by name, quantity, or the time and date the item was added, with pantries having the exclusive option to also sort items by expiration date. Items within respective pantries also have the opportunity to add a minimum quantity threshold, which will alert the user of items that reach a specific quantity, and these items can also have an expiration date, which will flag the user when the item has gone past this date. Pantries and shopping carts also come with the ability to search for items, making it easier to locate items in a large list.

This application also allows for users to add recipes to a cookbook located on the application. The recipes currently come from an API called MealDB, and the user can query recipes by name, by main ingredient, or by requesting a random recipe. After querying a recipe, the user can choose to save that recipe to their cookbook, which can then be retrieved locally. Within the list of recipes, the user has the option to view the recipes saved, remove saved recipes from their cookbook, sort recipes by name, and search for saved recipes in the cookbook. 

The final main functionality avaiable in this application is that the user has the ability to create notifications. The user can input a message to be saved, as well as the exact time and date that the notification should trigger. Once notifications trigger, there will be a message on the main interaction page where the user will be informed of new notifications. Notifications can also be edited and removed after their creation, along with the option to remove all notifications that have triggered and may no longer be needed.

We hope that MyKitchen provides you a great tool to help better manage your Kitchen!

## Program Files

Here is an overview of the program files used in MyKitchen:
- `API.java`: An abstract class used for easy expansion to add additional APIs to the software.
- `Connenction.java`: Provides an Abstract class to allow storage connection files to implement it for use by the system.
- `Controller.java`: Captures all user interaction directly with the Kitchen and Notification system, and is the center of the application.
- `Cookbook.java`: Captures all recipes and is modified through the Controller.
- `CookbookService.java`: Captures all the functionalities needed for the Cookbook, which includes Save, Search, and Sort.
- `Item.java`: Captures the ability to have one central class to create each Product or Ingredient in, which can then be stored in a Pantry or Cookbook.
- `JsonConnection.java`: Provides a class that implements the Connection abstract class to provide a center for saving the application data to a Json file.
- `JsonData.java`: Provides a class that alters the data inside the Json file with various functionalities.
- `Kitchen.java`: Captures the interactions between PantryService and CookbookService while storing each Pantry and Cookbook.
- `MealDB.java`: Captures every interaction with the MealDB API.
- `Navigation.java`: Captures the user input which can then be mapped to functionalities through the Controller.
- `Notification.java`: An abstract class of user-generated notifications that only contains the notification ID and Date.
- `NotificationFactory.java`: Creates Notification objects for storage in the list in NotificationService.
- `NotificationService.java`: Handles the creation, deletion, and editing requests of Notification objects as called in the Controller class.
- `Pantry.java`: Captures the ability to save multiple Item objects as either a Kitchen Inventory or a Shopping Cart.
- `PantryService.java`: Captures all the functionalities needed for a Pantry, which includes Creation, Save, and Sort.
- `Recipe.java`: Captures each recipe object that is aggregated into Cookbook.
- `SaveAppData.java`: An abstract class that provides common attributes and functionalities for saving app data to a file.
- `Search.java`: Captures the functionality to search for a particular item in the accompanying list.
- `Sort.java`: An interface that can be implemented to sort Pantry and Cookbook list objects.
- `SortByCreationDate.java`: Implements Sort interface and performs a sort based on the creation date of Item objects.
- `SortByExpirationDate.java`: Implements Sort interface and performs a sort on Item objects in a Kitchen Inventory based on Expiration Date.
- `SortByName.java`: Implements Sort interface and performs a sort based on the name attribute of Item objects.
- `SortByQuantity.java`: Implements Sort interface and performs a sort based on the quantity of Item objects.
- `ViewNotification.java`: An implementation of Notification that gives print messages to alert with notifications.

> There are also various Test files included that test the above program files
> A json file `MyKitchenData.json` will also be created upon saving data to the application, which will save user data between executions

## Running MyKitchen

To run MyKitchen:
- Simply download the zip file from this repository or clone the repository locally
- Open up the project in an IDE (preferably VSCode)
- Run the project
- Have fun!
