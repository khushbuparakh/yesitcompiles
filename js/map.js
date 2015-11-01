
function initialize() {
  var mapProp = {
    center:new google.maps.LatLng(29.0000,32.0000),
    zoom:5,
    mapTypeId:google.maps.MapTypeId.ROADMAP
  };
  var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
  //add dropdown over the map
  var controlDiv = document.getElementById('projectsMenu');
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(controlDiv);
}
google.maps.event.addDomListener(window, 'load', initialize);