<h1>I'm some CFML</h1>
<p><cfscript>echo( expandPath(".") )</cfscript></p>
<ul>
    <cfscript>
        for ( x in url ) echo( "<li>#x# = #url[x]#</li>" )
    </cfscript>
</ul>
<p>See the extra URL parameters added by Clojure to tie the session to Ring?</p>
<p>You have visited this page <cfscript>echo( ++session.visits )</cfscript> time(s).</p>
<p><a href="/">Go back to Clojure</a>!</p>
<cfdump var="#cookie#" label="cookie"/>
<cfdump var="#session#" label="session"/>
