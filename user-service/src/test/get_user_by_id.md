# Get user information by ID

This can be run with `silk -silk.url="http://localhost:9001"`

## `GET user?id=1`

Perform a find user information with id `1`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
{"id":1,"firstname":"King","lastname":"Charissa"}
```

## `GET user?id=2`

Perform a find user information with id `1`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
{"id":2,"firstname":"Fitzpatrick","lastname":"Dara"}
```