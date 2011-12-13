$(document).ready(function() 
{	
	  $("select#state").change(function(){
		    $.getJSON("jaxrs/PopularRunning/cities/state/"+$(this).val(), function(j){
		      var options = '';
		      for (var i = 0; i < j.length; i++) {
		        options += '<option value="' + j[i].id + '">' + j[i].description + '</option>';
		      }
		      $("select#cities").html(options);
		    });
		  });
});