Server:<br>
Should be a proper app server handling card game data using fcm and rest/soap. Should store the states of the ACTIVE games in cache. All other information in DB. Should handle all the serialization of states to db.
the authenticated clients should be able to make calls to request game state data.
<br>
Soap RPC:
get userList
login/register

functionality added so far:
-communication of errors via a base msg
-user login
-user registration
-active user list
    -getAllUsers
    -isUserActive
    -updateActiveUser
+cli means to use FCM
    -user registration and interaction resets FCM key for user
    can send msg/notification to user using username directly

TODO:
soap->rest (NO MORE XML!)
set up hibernate to get rid of current horrid orm
-activeLobbies (gamers, viewers - flag using
-activeGames


