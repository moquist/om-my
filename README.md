# om-my

## Usage

1. Register at http://developer.rottentomatoes.com/ for an API key.
1. ```export RT_API_KEY=<your key>```
1. ```export LEIN_FIGWHEEL_PORT=<port#>```
1. Clone this repo.
1. In one terminal:
 1. ```cd``` into this repo
 1. ```lein figwheel```
 1. ```(get-movie "The Sting")```
1. Edit ```src/cljs/om_my/core.cljs``` in your favorite editor.
1. Open http://localhost:<port#> in your browser (preferably Chrome for source-mapping), and open the developer console.
