component {
    this.name = "ClojureLucee"
    function onApplicationStart() {
        echo( "<p>Application Starting!</p>" )
    }
    function onSessionStart() {
        echo( "<p>Session Starting!</p>" )
        session.visits = 0
    }
}
