//$(function() {
//	$( ".buttons button:first" ).button({
//        icons: {
//            primary: "ui-icon-pencil"
//        }
//    }).next().button({
//        icons: {
//            primary: "ui-icon-trash"
//        }
//    });
//});
$(document).ready(function(){	
	jQuery("#frmac").jqGrid({ 
		url:'/jaxrs/PopularRunning/runningevents', 
		datatype: "json", 
		colNames:[' ', 'Date', 'Short Name', 'Picture URL', 'Race Distance','Location','Enrollment URL','Map URL','Elevation URL','Description'], 
		colModel:[ 
			{name: 'myac', width:80, fixed:true, sortable:false, resize:false, formatter:'actions',
				formatoptions:{keys:true}}, 
			{name:'date', key : true, index:'date', width:95}, 
			{name:'shortName',index:'shortName', width:120}, 
			{name:'picture', index:'picture', width:100}, 
			{name:'distance',index:'distance', width:130, align:"right", editable:true}, 
			{name:'location',index:'location', width:80, align:"right", editable:true}, 
			{name:'enrollment',index:'enrollment', width:150,align:"right", editable:true}, 
			{name:'map',index:'map', width:150, sortable:false, editable:true}, 
			{name:'elevation',index:'elevation', width:150, sortable:false, editable:true}, 
			{name:'description',index:'description', width:150, sortable:false, editable:true} 
		], 
		rowNum:10, 
		width: '80%', 
		rowList:[10,20,30], 
		pager: '#pfrmac', 
		sortname: 'date', 
		viewrecords: true, 
		sortorder: "desc", 
		jsonReader: { repeatitems : false }, 
		caption: "Running Events", 
		height: '100%', 
		editurl : '/' 
	}); 
	jQuery("#frmac").jqGrid('navGrid','#pfrmac',{edit:false,add:false,del:false});
});