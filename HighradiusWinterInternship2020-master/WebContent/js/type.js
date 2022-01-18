
$('input[type="checkbox"]').on('change', function() {
   $('input[type="checkbox"]').not(this).prop('checked', false);
});




$(".checkbox").click(function () {
	  //If the checkbox is checked.
	  if ($(this).is(":checked")) {
	    //Enable the submit button.
	    if(formType==1)
	    {
	      $("#edit").attr("disabled", false);
	      document.getElementById("edit").style.backgroundColor = "#fc7500";
	    }
	    else if(formType==2)
	    {
	      $("#approve").attr("disabled", false);
	        document.getElementById("approve").style.backgroundColor = "#fc7500";
	        $("#reject").attr("disabled", false);
	        document.getElementById("reject").style.backgroundColor = "#fc7500";
	    }
	   
	  } else {
	    //If it is not checked, disable the button.
	    if(formType==1)
	    {
	      $("#edit").attr("disabled", true);
	      document.getElementById("edit").style.backgroundColor = "#cccccc";
	    }
	    else if(formType==2)
	    {
	      $("#approve").attr("disabled", true);
	      document.getElementById("approve").style.backgroundColor = "#cccccc";
	      $("#reject").attr("disabled", true);
	      document.getElementById("reject").style.backgroundColor = "#cccccc";
	    }
	  
	  }
	});



var selectedRow=0;
function highlightRow(data)
{
	let r= document.getElementById("rowN"+data);
	if(selectedRow === 0)
	{
		r.classList.add('hRow');
		selectedRow=data;
	}
	else if(selectedRow === data)
	{
		document.getElementById("rowN"+data).classList.remove('hRow');
		selectedRow=0;
	}
	else
	{
		document.getElementById("rowN"+selectedRow).classList.remove('hRow');
		r.classList.add('hRow');
		selectedRow=data;
	}
}



