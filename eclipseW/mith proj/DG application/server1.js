var express = require('express');
var app = express();
var path = require('path')
var bodyParser = require('body-parser');
var urlencodedParser = bodyParser.urlencoded({ extended: false })
var xmlcreate = require("xmlcreate");
var path = require('path');
var fs = require('fs');
var xml2js = require('xml2js');
var builder = new xml2js.Builder();
var ent = require('ent');
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.static('public'));
app.get('/',function(req,res){
res.sendfile("PsDataMobility.html");
});
app.post('/',function(req,res){
var xmlJS = require("xml-js");
var jsonData = {}; 
jsonData.elements=[];
jsonData.elements.push(getApplicationConfigurationNode());

function getApplicationConfigurationNode() {
	//Root Node
	var applicationConfigurationNode=createElement("application-configuration");
	applicationConfigurationNode.elements = [];

	//General ConfigurationNode
	applicationConfigurationNode.elements.push(getGeneralConfigurationNode());

	//Regions Node
	applicationConfigurationNode.elements.push(getRegionsConfigurationNode());
	
	//Dates Node
	applicationConfigurationNode.elements.push(getDatesConfigurationNode());

	return applicationConfigurationNode;
}
//FOR general configuration
function getGeneralConfigurationNode() {
	//TODO SAMPLE
	var generalConfiguration=createElement("generalConfiguration");
	generalConfiguration.elements = [];
	generalConfiguration.elements.push(getGeneralNode(req.body.Subscribers,req.body.NoOfCells,req.body.CEMOD,req.body.NoOfURL,req.body.Cells));
	generalConfiguration.elements.push(getOttspecificNode(req.body.phoneused,req.body.segmentused,req.body.ProtocolIDs,req.body.Categories,req.body.NodeIDs,req.body.FNGIDs,req.body.DomainVisits,req.body.Applications,req.body.ApplicationsUsed,req.body.EventsPerDay));
	return generalConfiguration;
}

function getGeneralNode(subscribers,NoOfCells,cemod,NoofUrl,Cells) {
	var gerneralNode = createElement("generalconfiguration");
	gerneralNode.attributes = {};
	gerneralNode.attributes.totalNoOfSubscribers =subscribers;
	gerneralNode.attributes.noOfCells = NoOfCells;
	gerneralNode.attributes.CEMoD_Version=cemod;
	gerneralNode.attributes.noOfURLPerSubscriber=NoofUrl;
	gerneralNode.attributes.cellsPerSubscriber=Cells;
	return gerneralNode;
}
function getOttspecificNode(phoneused,segmentused,ProtocolIDs,Categories,NodeIDs,FNGIDs,DomainVisits,Applications,ApplicationsUsed,EventsPerDay) {
	var ottspecificNode = createElement("ottspecific");
	ottspecificNode.attributes = {};
	ottspecificNode.attributes.numberOfSmartPhonesUsed =phoneused;
	ottspecificNode.attributes.numberOfSegmentsUsed =segmentused;
	ottspecificNode.attributes.numberOfProtocolIDs=ProtocolIDs;
	ottspecificNode.attributes.numberOfApplicationCategories=Categories;
	ottspecificNode.attributes.numberOfNodeIDs=NodeIDs;
	ottspecificNode.attributes.numberOfFNGIDs= FNGIDs;
	ottspecificNode.attributes.numberOfDistinctDomainVisits=DomainVisits;
	ottspecificNode.attributes.numberOfApplications=Applications;
	ottspecificNode.attributes.numberOfApplicationsUsed= ApplicationsUsed;
	ottspecificNode.attributes.domainEventsPerDay= EventsPerDay;
	return ottspecificNode;
}
//for region

function getRegionsConfigurationNode(){
	var regions = createElement("regions");
	regions.elements = [];
	//Add n region for example
	regions.elements.push(getRegionNode(req.body.Region,req.body.subscribersInPercentage,));
	return regions;
}
function getRegionNode(region,subscribersInPercentage,timezone,technologyDistribution) {
	var regionNode = createElement("region");
	regionNode.attributes={};
	regionNode.attributes.name=region;
	regionNode.attributes.subscribersInPercentage=subscribersInPercentage;
	regionNode.attributes.timezone=timezone;
	regionNode.attributes.technologyDistribution=technologyDistribution;
	return regionNode;
}

//for dates
function getDatesConfigurationNode() {
	var dates = createElement("Dates");
	dates.elements =[];
	dates.elements.push(getHoursNode());
	dates.elements.push(getDaysNode());
	dates.elements.push(getWeekNode());
	dates.elements.push(getMonthNode());
	return dates;
}
//hour element
function getHoursNode() {
	var hours=createElement("hours");
	hours.attributes={};
	hours.attributes.name="hour";
	//VALUES iteration
	hours.attributes.value="";
	hours.elements =[];
	hours.elements.push(getdayNode());
	return hours;
}
//hour day
function getdayNode(){
	var day=createElement("day");
	day.elements =[];
	return day;
}
//DAYS element
function getDaysNode() {
	var days=createElement("days");
	days.elements = [];
	days.elements.push(getdaysNode());
	return days;
}
//Days day
function getdaysNode() {
	var day = createElement("day");
	day.elements = [];
	return day;
}
//week element
function getWeekNode() {
	var week=createElement("week");
	week.elements = [];
	week.elements.push(getweekNode());
	return week;
}
//week day
function getweekNode() {
var day = createElement("day");
return day;
}
//month element
function getMonthNode() {
	var month=createElement("month");
	month.elements = [];
	month.elements.push(getmonthNode());
	return month;
}
//month day
function getmonthNode() {
	var day=createElement("day");
	//day.createText="ss";
	return day;
}
function createElement(name) {
	var element = {};
	element.type ="element";
	element.name = name;
	return element;
}
console.log(xmlJS.json2xml(jsonData));
});
app.listen(3000,function(){
  console.log("Started on PORT 3000");
})