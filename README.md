# server
Server:<br>
Should be a proper app server handling card game data using fcm and rest/soap. Should store the states of the ACTIVE games in cache. All other information in DB. Should handle all the serialization of states to db.
the authenticated clients should be able to make calls to request game state data.
<br>
Soap RPC:<br>
get userList<br>
login/register<br>

functionality added so far:<br>
-communication of errors via a base msg<br>
-user login<br>
-user registration<br>
-active user list<br>
    -getAllUsers<br>
    -isUserActive<br>
    -updateActiveUser<br>
+cli means to use FCM<br>
    -user registration and interaction resets FCM key for user<br>
    -can send msg/notification to user using username directly
<br><br>
TODO:<br>
soap->rest (NO MORE XML!)<br>
set up hibernate to get rid of current horrid orm<br>
-activeLobbies (gamers, viewers - flag using<br>
-activeGames<br>


