# Readme
This is my readme file for this project

# Endpoint Table
| Endpoints                                             | Method | Secured | Description                                      |
|:------------------------------------------------------|:-------|:--------|:-------------------------------------------------|
| api/skilessons                                        | GET    | ❌      | Get all ski lessons                                                |
| api/skilessons/{id}                                   | GET    | ❌      | Get a ski lesson by its ID (includes external instructions)        |
| api/skilessons                                        | POST   | ❌      | Create a new ski lesson (instructor added later)                   |
| api/skilessons/{id}                                   | PUT    | ❌      | Update a ski lesson                                                |
| api/skilessons/{id}                                   | DELETE | ❌      | Delete a ski lesson                                                |
| api/skilessons/{lessonId}/instructors/{instructorId}  | PUT    | ❌      | Add an instructor to a ski lesson                                  |
| api/skilessons/populate                               | POST   | ❌      | Populate the database with ski lessons & instructors               |
| api/skilessons/filter/{level}                         | GET    | ❌      | Get all ski lessons filtered by skill level                        |
| api/skilessons/summary/price                          | GET    | ❌      | Get total price of lessons offered by each instructor              |
| api/skilessons/{id}/instruction-duration              | GET    | ❌      | Get total duration (minutes) of instructions for a ski lesson      |



❌ = Not secured

✅ = User secured

🔒 = Admin secured

# Notes
I didn't completely finnish the implementation of the external API since i still can't get my program to show the data from the endpoint.

The security folder is from my template and i have not changed anything in them to fit the given task.

# Route responses
I am having trouple creating and updating with my endpoints and i am getting errors on those test. i have spend a long time trying to figure it out by i am having no luck. so i am skipping the fixes for now

Testing started at 12:37 ...
HTTP/1.1 200 OK
Date: Mon, 07 Apr 2025 10:37:56 GMT
Content-Type: application/json
Content-Length: 847

Response file saved.
> 2025-04-07T123757.200.json

Response code: 200 (OK); Time: 734ms (734 ms); Content length: 847 bytes (847 B)
HTTP/1.1 200 OK
Date: Mon, 07 Apr 2025 10:37:57 GMT
Content-Type: application/json
Content-Length: 168

Response file saved.
> 2025-04-07T123757-1.200.json

Response code: 200 (OK); Time: 10ms (10 ms); Content length: 168 bytes (168 B)
HTTP/1.1 500 Server Error
Date: Mon, 07 Apr 2025 10:37:57 GMT
Content-Type: application/json
Content-Length: 387

Response file saved.
> 2025-04-07T123757.500.json

Response code: 500 (Server Error); Time: 6ms (6 ms); Content length: 387 bytes (387 B)
HTTP/1.1 500 Server Error
Date: Mon, 07 Apr 2025 10:37:57 GMT
Content-Type: application/json
Content-Length: 387

Response file saved.
> 2025-04-07T123757-1.500.json

Response code: 500 (Server Error); Time: 5ms (5 ms); Content length: 387 bytes (387 B)
HTTP/1.1 204 No Content
Date: Mon, 07 Apr 2025 10:37:57 GMT
Content-Type: application/json

<Response body is empty>Response code: 204 (No Content); Time: 7ms (7 ms); Content length: 0 bytes (0 B)
HTTP/1.1 204 No Content
Date: Mon, 07 Apr 2025 10:37:57 GMT
Content-Type: application/json

<Response body is empty>Response code: 204 (No Content); Time: 11ms (11 ms); Content length: 0 bytes (0 B)
HTTP/1.1 201 Created
Date: Mon, 07 Apr 2025 10:37:57 GMT
Content-Type: application/json
Content-Length: 9

Response file saved.
> 2025-04-07T123758.201.json

Response code: 201 (Created); Time: 37ms (37 ms); Content length: 9 bytes (9 B)

# 3.3.5
we are using a put method because we are updating allready existing data. if we were to use post then we would create new data, risking doublicates in the database
