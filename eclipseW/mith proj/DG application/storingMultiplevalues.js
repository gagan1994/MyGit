var region=[];
	var hday=[];
      $(document).ready(function(){
	      var Subscribers,cells,CEMOD,Region,subscribersInPercentage,Timezone,Day;
	  $("#add").click(function(){
	      Region=$("#n4").val();
		  subscribersInPercentage=$("#n5").val();
          Timezone=$("#n6").val();
	    region=[{a:Region,b:subscribersInPercentage,c:Timezone}];
		   for(var i in region){
			 console.log("printing...."+region[i].a);
			 console.log(region[i].b);
			 console.log(region[i].c);}
	  $("#add1").click(function(){
         Day=$("#n7").val();
		 hday=[{d:Day}];
		 for(var i in hday){
			 console.log("printing...."+hday[i].d);}
      $("#submit").click(function(){
		  Subscribers=$("#n1").val();
		  cells=$("#n2").val();
		  CEMOD=$("#n3").val();
		 console.log(Subscribers);
      $.post("http://localhost:3000",{n1:Subscribers,n2:cells,n3:CEMOD,n4:Region,n5:subscribersInPercentage,n6:Timezone,n7:Day}, function(data){
            if(data==='done')
              {
                alert("login success");
              }
          });
		  });
		  });
		  });
       
	  });