# lucee

A Clojure Ring app that can evaluate CFML using Lucee as an embedded engine.

## Installation

Download from https://github.com/seancorfield/clojure-lucee

## Usage

You will need Leiningen installed (and Java, duh!) - http://leiningen.org - and then you can just do:

    $ lein ring server

The first time you run this, it will download a lot of files (the whole of Lucee 4.5.0.042 as a set of dependencies from the http://cfmlprojects.org/artifacts Maven repository).

It will start up a web server on port 3000 and open your web browser to that URL.

You'll see:

    I'm a Clojure Ring app!

and a link to a CFML page. Go on, click it! You'll see:

    I'm some CFML

Yes, that's really a CFML page, running via Lucee embedded in a Clojure application. No install needed. When the application runs the first time, Lucee generates a WEB-INF folder containing the usual engine stuff. **Note: each request gets you a new session, since we're running CFML via Lucee's Command Line Interface!**

## License

Copyright © 2015 Sean Corfield

Distributed under the Eclipse Public License version 1.0.
