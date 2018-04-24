$(document).ready(function(){
var Subscribers,cells,CEMOD, phoneused,segmentused,ProtocolIDs,Categories,NodeIDs,FNGIDs,DomainVisits,Applications,ApplicationsUsed,EventsPerDay,Region,subscribersInPercentage,Timezone,Day,Value,Dday;	
//adding multiple regions
var regionsarr=[];
var hourDays=[];
var value=[];
var daysDays=[];
var weekDays=[];
var monthDays=[];
	$("#add").click(function(){
	    Region=$("#Regionid").val();
		subscribersInPercentage=$("#subscribersInPercentageid").val();
        Timezone=$("#Timezoneid").val();
        Twog=$("#2gid").val();
        Threeg=$("#3gid").val();
        Fourg=$("#4gid").val();
		regionsarr.push({Region,subscribersInPercentage,Timezone,Twog,Threeg,Fourg});
	});
	//adding multiple hour day
	$("#add1").click(function(){
         Day=$("#hdayid").val();
		 hourDays.push(Day); 
	});	
			
	//adding multiple values
	$("#add2").click(function(){
		 Value=$("#valid").val();
		 value.push(Value);	
	});
	
	//adding multiple Days day
	$("#add3").click(function(){
		 Dday=$("#daysid").val();
		 daysDays.push(Dday);
	});
	
	//adding multiple week day
	$("#add2").click(function(){
		 Wday=$("#weekid").val();
		 weekDays.push( Wday);	
	});
	
	//adding multiple month day
	$("#add2").click(function(){
		 Mday=$("#monthid").val();
		 monthDays.push( Mday);	
	});
	
// posting the data
$("#submit").click(function(){
		 subscribers=$("#Subscribersid").val();
		 NoOfCells=$("#NoOfCellsid").val();
		 Cemod=$("#CEMODid").val();
		 NoOfURL=$("#NoOfURLid").val();
		 Cells=$("#Cellsid").val();
		 phoneused=$("#phoneusedid").val();
		 segmentused=$("#segmentusedid").val();
		 ProtocolIDs=$("#ProtocolIDsid").val();
		 Categories=$("#Categoriesid").val();
		 NodeIDs=$("#NodeIDsid").val();	
		 FNGIDs=$("#FNGIDsid").val();
		 DomainVisits=$("#DomainVisitsid").val();
		 Applications=$("#Applicationsid").val();
		 ApplicationsUsed=$("#ApplicationsUsedid").val();
		 EventsPerDay=$("#EventsPerDayid").val();	
		 
$.post("http://localhost:3000",{Subscribers:subscribers,NoOfCells:NoOfCells,CEMOD:Cemod,NoOfURL:NoOfURL,Cells:Cells,phoneused:phoneused,segmentused:segmentused,ProtocolIDs:ProtocolIDs,Categories:Categories,NodeIDs:NodeIDs,FNGIDs:FNGIDs,DomainVisits:DomainVisits,Applications:Applications, ApplicationsUsed: ApplicationsUsed, EventsPerDay: EventsPerDay,Regions:regionsarr,Day:hourDays,values:value,Dday:daysDays,Wday:weekDays,Mday:monthDays}, function(data){
            if(data==='done')
              {
                alert("login success");
              }
          });
	});
	});
