$(document).ready(function(){	
	$("select#state").change(function(){
		$("select#location").attr('class','linked_selects_waiting');
		$.getJSON("jaxrs/PopularRunning/cities/state/"+$(this).val(), function(j){
			var options = '';
			for (var i = 0; i < j.length; i++) {
				options += '<option value="' + j[i].id + '">' + j[i].description + '</option>';
			}
			$("select#location").html(options);
			if (options != '') {
				$("<option value='' selected='selected'>Please select City...</option>").prependTo("select#location");
			}
			$("select#location").attr('class','linked_selects');
		});
	});

	// Initial list values TODO I18n
	$("<option value='' selected='selected'>Please select Race Distance...</option>").prependTo("select#distance");
	$("<option value='' selected='selected'>Please select State...</option>").prependTo("select#state");

	// Calendars
	$("input#date").datepicker();
});