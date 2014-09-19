// this function was run on each of the schedule pages eg https://2014.event.springone2gx.com/schedule/2014-09-09
// to collect session information as JSON into sessionStore.sessions
// the collected data was then pated into springone-data.json
function grabAndAppendSessionInfo() {
  var sessions = sessionStorage.sessions ? JSON.parse(sessionStorage.sessions) : []; 
  $('div.attendease-session-and-instance').not('.breakbar').each(function(i,session){
    var sessionInfo = $(session).find('div.attendease-name h2 a');
    var sessionLink = window.location.origin+$(sessionInfo).attr('href');
    var sessionName = $(sessionInfo).text();
    var presenters = [];
    $(session).find('div.attendease-presenter-name a').each(function(j,presenter){
      var presenterName = $(presenter).text().split(" ");
      var firstName = presenterName[0];
      var lastName = presenterName[1];
      var presenterLink = window.location.origin+$(presenter).attr('href');
      presenters.push({firstName:firstName,lastName:lastName,externalLink:presenterLink});
    }); 
    sessions.push({name:sessionName,externalLink:sessionLink,presenters:presenters});
  });
  sessionStorage.sessions = JSON.stringify(sessions);
}
