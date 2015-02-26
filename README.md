# om-my

The focus of this collaboration starting-project is on:

1. REPL interaction with the 'app-data atom in 'om-my.core
1. Experimenting by building out the dom and trying out Om components.

This small project does NOT (at this point) demonstrate excellent use of Om.

This project has a small back-end server to proxy queries to Rotten
Tomatoes; this gets us around issues with [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing).

## Usage

1. Register at http://developer.rottentomatoes.com/ for an API key.
1. ```export RT_API_KEY=<your key>```
1. Clone this repo.
1. In one terminal (your REPL terminal):
 1. ```cd``` into this repo
 1. ```rlwrap lein figwheel```
1. Open ```http://<hostname>:<port#>``` in your browser (preferably Chrome for source-mapping), and open the developer console.
 1. Back in your REPL terminal, you should now have a prompt. Try this: ```=> (om-my.core/get-movie "The Sting")```
1. Edit ```src/cljs/om_my/core.cljs``` in your favorite editor.

