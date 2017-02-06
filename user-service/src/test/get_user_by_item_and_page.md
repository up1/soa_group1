# Get user information by ID

This can be run with `silk -silk.url="http://localhost:9001"`

## `GET users?page=5&item_per_page=9`

Perform a find user information from id `37` to `45`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
[{"id":37,"firstname":"Mullen","lastname":"Charissa"},{"id":38,"firstname":"Huff","lastname":"Colorado"},{"id":39,"firstname":"Gordon","lastname":"Sigourney"},{"id":40,"firstname":"Guthrie","lastname":"Rhoda"},{"id":41,"firstname":"Mcguire","lastname":"Amethyst"},{"id":42,"firstname":"Nunez","lastname":"Claudia"},{"id":43,"firstname":"Cole","lastname":"Raven"},{"id":44,"firstname":"Cunningham","lastname":"Giselle"},{"id":45,"firstname":"Wong","lastname":"Cooper"}]
```